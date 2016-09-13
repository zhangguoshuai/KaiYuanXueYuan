/*
 * This is the source code of DMPLayer for Android v. 1.0.0.
 * You should have received a copy of the license in this archive (see LICENSE).
 * Copyright @Dibakar_Mistry, 2015.
 */
package com.dmplayer.childfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dmplayer.ApplicationDMPlayer;
import com.dmplayer.R;

import java.util.ArrayList;

public class MyMusicFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MyMusicFragment";
    private RecyclerView recyclerView;
    private LinearLayout local,singer,like,dir,mv,album,download;
    public static MyMusicFragment newInstance(){
        MyMusicFragment fragment = new MyMusicFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_music, null);
        ApplicationDMPlayer.getInstances(getActivity());
        initView(view);
        return view;
    }

    public void initView(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.my_music_list);
        local = (LinearLayout) view.findViewById(R.id.music_local);
        singer = (LinearLayout) view.findViewById(R.id.music_singer);
        like = (LinearLayout) view.findViewById(R.id.music_like);
        dir = (LinearLayout) view.findViewById(R.id.music_dir);
        mv = (LinearLayout) view.findViewById(R.id.music_mv);
        album = (LinearLayout) view.findViewById(R.id.music_album);
        download = (LinearLayout) view.findViewById(R.id.music_download);

        local.setOnClickListener(this);
        singer.setOnClickListener(this);
        like.setOnClickListener(this);
        dir.setOnClickListener(this);
        mv.setOnClickListener(this);
        album.setOnClickListener(this);
        download.setOnClickListener(this);

        initRecyclerview(recyclerView);
    }

    public void initRecyclerview(RecyclerView recyclerView){
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        MyMusicAdapter myMusicAdapter = new MyMusicAdapter(getActivity(),getIcon(),getTitle());

        recyclerView.setAdapter(myMusicAdapter);

        myMusicAdapter.setOnItemClickLitener(new MyMusicAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = new Intent(getActivity(),MyMusicActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<Integer> getIcon(){
        ArrayList<Integer> messageInfos = new ArrayList<Integer>();
        messageInfos.clear();
        messageInfos.add(R.mipmap.music);
        messageInfos.add(R.mipmap.favorite);
        messageInfos.add(R.mipmap.music);
        messageInfos.add(R.mipmap.favorite);
        messageInfos.add(R.mipmap.music);
        messageInfos.add(R.mipmap.favorite);
        messageInfos.add(R.mipmap.music);

        return messageInfos;
    }

    public ArrayList<String> getTitle(){
        ArrayList<String> messageInfos = new ArrayList<String>();
        messageInfos.clear();
        messageInfos.add("本地歌曲");
        messageInfos.add("我喜欢的");
        messageInfos.add("歌手分类");
        messageInfos.add("专辑分类");
        messageInfos.add("MV视频");
        messageInfos.add("下载管理");
        messageInfos.add("新增目录");
        return messageInfos;
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();
        Intent intent = new Intent(getActivity(),MyMusicActivity.class);

        if (i == R.id.music_local) {
            intent.putExtra("position",0);
        }

        if (i == R.id.music_like) {
            intent.putExtra("position",1);
        }

        if (i == R.id.music_singer) {
            intent.putExtra("position",2);
        }

        if (i == R.id.music_album) {
            intent.putExtra("position",3);
        }

        if (i == R.id.music_mv) {
            intent.putExtra("position",4);
        }

        if (i == R.id.music_download) {
            intent.putExtra("position",5);
        }

        if (i == R.id.music_dir) {
            intent.putExtra("position",6);
        }

        startActivity(intent);
    }
}

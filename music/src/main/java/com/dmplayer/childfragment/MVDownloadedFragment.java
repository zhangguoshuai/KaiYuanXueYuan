/*
 * This is the source code of DMPLayer for Android v. 1.0.0.
 * You should have received a copy of the license in this archive (see LICENSE).
 * Copyright @Dibakar_Mistry, 2015.
 */
package com.dmplayer.childfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dmplayer.R;

import java.util.ArrayList;

public class MVDownloadedFragment extends Fragment {

    private static final String TAG = "MyMusicFragment";
    private static FragmentActivity myActivity;
    private RecyclerView recyclerView;

    public static MVDownloadedFragment newInstance(int position, FragmentActivity activity) {
        MVDownloadedFragment f = new MVDownloadedFragment();
        myActivity = activity;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_music, null);
        initView(view);
        return view;
    }

    public void initView(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.my_music_list);
        initRecyclerview(recyclerView);
    }

    public void initRecyclerview(RecyclerView recyclerView){
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(myActivity);
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        MyMusicAdapter myMusicAdapter = new MyMusicAdapter(myActivity,getIcon(),getTitle());

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
        messageInfos.add(R.mipmap.musicthree);
        messageInfos.add(R.mipmap.musicone);
        messageInfos.add(R.mipmap.musicfour);
        messageInfos.add(R.mipmap.musicone);
        messageInfos.add(R.mipmap.musicfive);
        messageInfos.add(R.mipmap.musicsix);
        messageInfos.add(R.mipmap.musictwo);

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

}

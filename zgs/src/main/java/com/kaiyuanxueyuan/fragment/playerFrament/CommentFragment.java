package com.kaiyuanxueyuan.fragment.playerFrament;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.CommentListAdapter;
import com.kaiyuanxueyuan.entity.CommentInfo;
import com.kaiyuanxueyuan.fragment.mainFragment.MyFragment;
import com.kaiyuanxueyuan.utils.ImageTools;

import java.util.ArrayList;


/**
 * 评论
 * Created by Administrator on 2016/7/4.
 */
public class CommentFragment extends MyFragment implements View.OnClickListener{

    public static final String BUNDLE_TITLE = "title";
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_conment, null);
        initView(view);
        return view;
    }

    public static CommentFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(bundle);
        return fragment;

    }
    public static CommentFragment newInstance() {
        CommentFragment fragment = new CommentFragment();
        return fragment;
    }

    public void initView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.comment_list);
        initRecyclerview(recyclerView);

    }

    public void initRecyclerview(RecyclerView recyclerView){
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        CommentListAdapter commentListAdapter = new CommentListAdapter(getActivity(),getCommentInfo());

        recyclerView.setAdapter(commentListAdapter);
    }

    public ArrayList<CommentInfo> getCommentInfo(){
        ArrayList<CommentInfo> commentInfos = new ArrayList<CommentInfo>();
        commentInfos.clear();
        for (int i = 0;i < 20; i++){
            CommentInfo commentInfo = new CommentInfo();
            Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(),R.mipmap.one);
			Bitmap new_bitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / 5, bitmap.getHeight() / 5);
			bitmap.recycle();
            commentInfo.setBitmap(new_bitmap);
            commentInfo.setUser("我是用户一");
            commentInfo.setData(i + "小时前");
            commentInfo.setLiked(i);
            commentInfo.setContent("有些地方，总是报错，要是有源码就好了" + i);
            commentInfos.add(i,commentInfo);
        }

        return commentInfos;
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}

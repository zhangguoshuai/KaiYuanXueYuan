package com.kaiyuanxueyuan.fragment.universityFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.UniversityVideoAdapter;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.fragment.mainFragment.MyFragment;
import com.kaiyuanxueyuan.player.VideoActivity;
import com.kaiyuanxueyuan.utils.ShowInfo;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 教育视频中心
 * Created by 张国帅 on 2016/7/11.
 */
public class VideoFragment extends  MyFragment implements View.OnClickListener {

    private PullLoadMoreRecyclerView recyclerView;
    private ImageView screen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.university_video, null);
        initView(view);
        return view;
    }

    public static VideoFragment newInstance(){
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    private void initView(View view){

        recyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.university_video_list);
        screen = (ImageView) view.findViewById(R.id.university_video_screen);

        // 创建数据集
        SimulatedData simulatedData = new SimulatedData();
        initRecycleView(recyclerView,simulatedData);
        screen.setOnClickListener(this);
    }



    public void initRecycleView(PullLoadMoreRecyclerView recyclerView,SimulatedData simulatedData){

        recyclerView.setLinearLayout();
        // 创建Adapter，并指定数据集
        UniversityVideoAdapter myListAdapter = new UniversityVideoAdapter(getActivity(), simulatedData.getImageList(),
                simulatedData.getTitleList(), simulatedData.getPersonNumberList(),
                simulatedData.getStatusList());

        myListAdapter.setOnItemClickLitener(new UniversityVideoAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                startActivity(intent);
                ShowInfo.Toast(getActivity(),"点击的位置：" + position);

            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        // 设置Adapter
        recyclerView.setAdapter(myListAdapter);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

        @Override
        public void onRefresh() {
            Toast.makeText(getActivity(), "onRefresh", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setPullLoadMoreCompleted();
                }
            }, 1000);
        }

        @Override
        public void onLoadMore() {
            Toast.makeText(getActivity(), "onLoadMore", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setPullLoadMoreCompleted();
                }
            }, 1000);
        }
    }

    @Override
    public void onClick(View v) {

    }
}

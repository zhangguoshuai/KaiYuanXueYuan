package com.kaiyuanxueyuan.fragment.liveFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.TVStationAdapter;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.fragment.mainFragment.MyFragment;
import com.kaiyuanxueyuan.utils.ShowInfo;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 所有和直播有关的模块
 * Created by 张国帅 on 2016/7/11.
 */
public class LiveFragment extends  MyFragment implements View.OnClickListener {

    private PullLoadMoreRecyclerView recyclerView;
    private ArrayList<String> dir = new ArrayList<String>();
    private String VIDEOURL = "TV_URL";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_live, null);
        initView(view);
        return view;
    }

    public static LiveFragment newInstance(){
        LiveFragment fragment = new LiveFragment();
        return fragment;
    }

    private void initView(View view){

        recyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.live_video_list);
        // 创建数据集
        SimulatedData simulatedData = new SimulatedData();
        dir = simulatedData.getVideoUrl();
        initRecycleView(recyclerView,simulatedData);
    }

    public void initRecycleView(PullLoadMoreRecyclerView recyclerView,SimulatedData simulatedData){

        // 设置布局管理器
        recyclerView.setLinearLayout();
        // 创建Adapter，并指定数据集
        TVStationAdapter myListAdapter = new TVStationAdapter(getActivity(), simulatedData.getTVDir());

        myListAdapter.setOnItemClickLitener(new TVStationAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(getActivity(), LiveTVActivity.class);
                        break;
                    case 1:
                        intent = new Intent(getActivity(), LiveAnchorActivity.class);
                        break;
                    case 2:
                        intent = new Intent(getActivity(), LiveWebTvActivity.class);
                        intent.putExtra(VIDEOURL,dir.get(0));
                        break;
                    case 3:
                        intent = new Intent(getActivity(), LiveWebTvActivity.class);
                        intent.putExtra(VIDEOURL,dir.get(1));
                        break;
                    case 4:
                        intent = new Intent(getActivity(), LiveWebTvActivity.class);
                        intent.putExtra(VIDEOURL,dir.get(2));
                        break;
                    case 5:
                        intent = new Intent(getActivity(), LiveWebTvActivity.class);
                        intent.putExtra(VIDEOURL,dir.get(3));
                        break;
                    case 6:
                        intent = new Intent(getActivity(), LiveWebTvActivity.class);
                        intent.putExtra(VIDEOURL,dir.get(4));
                        break;
                }
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                ShowInfo.Toast(getActivity()," long click");
            }
        });
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        // 设置Adapter
        recyclerView.setAdapter(myListAdapter);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

        @Override
        public void onRefresh() {
            ShowInfo.Toast(getActivity()," onRefresh");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setPullLoadMoreCompleted();
                }
            }, 1000);
        }

        @Override
        public void onLoadMore() {
            ShowInfo.Toast(getActivity()," onLoadMore");
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

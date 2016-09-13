package com.kaiyuanxueyuan.fragment.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.StudyAdapter;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.fragment.universityFragment.StudyActivity;
import com.kaiyuanxueyuan.utils.ShowInfo;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 学府
 * Created by 张国帅 on 2016/7/11.
 */
public class StudyFragment extends  MyFragment implements View.OnClickListener {

    private PullLoadMoreRecyclerView recyclerView;
    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.main_study, null);
        initView(view);
        return view;
    }

    public static StudyFragment newInstance(){
        StudyFragment fragment = new StudyFragment();
        return fragment;
    }


    private void initView(View view){

        recyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.study_list);
        SimulatedData simulatedData = new SimulatedData();
        initRecycleView(recyclerView,simulatedData);
    }

    public void initRecycleView(final PullLoadMoreRecyclerView recyclerView, SimulatedData simulatedData){

        recyclerView.setGridLayout(4,10);

        StudyAdapter adapter = new StudyAdapter(getActivity(), simulatedData.getStudyName(),simulatedData.getStudyIcon());

        adapter.setOnItemClickLitener(new StudyAdapter.OnItemClickLitener() {

            @Override
            public void onItemClick(View view, int position) {
                if (intent == null){
                    intent = new Intent(getActivity(), StudyActivity.class);
                    startActivity(intent);
                }else{
                    startActivity(intent);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                ShowInfo.Toast(getActivity(),"onItemLongClick：" + position);
            }
        });
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        // 设置Adapter
        recyclerView.setAdapter(adapter);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

        @Override
        public void onRefresh() {
            ShowInfo.Toast(getActivity(),"onRefresh");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setPullLoadMoreCompleted();
                }
            }, 1000);
        }

        @Override
        public void onLoadMore() {
            ShowInfo.Toast(getActivity(),"onLoadMore");
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

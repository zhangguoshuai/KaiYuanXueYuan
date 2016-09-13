package com.kaiyuanxueyuan.fragment.playerFrament;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.MainListAdapter;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.fragment.mainFragment.MyFragment;

/**
 * 详情
 * Created by Administrator on 2016/7/4.
 */
public class DetailsFragment extends MyFragment implements View.OnClickListener{

    public static final String BUNDLE_TITLE = "title";
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_details, null);
        initView(view);
        return view;
    }

    public static DetailsFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        return fragment;

    }
    public static DetailsFragment newInstance() {
        DetailsFragment fragment = new DetailsFragment();
        return fragment;
    }

    public void initView(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.details_list);
        // 创建数据集
        SimulatedData simulatedData = new SimulatedData();
        initRecycleView(recyclerView,simulatedData);
    }

    public void initRecycleView(RecyclerView recyclerView, SimulatedData simulatedData){

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        // 创建Adapter，并指定数据集
        MainListAdapter myListAdapter = new MainListAdapter(getActivity(), simulatedData.getImageList(),
                simulatedData.getTitleList(), simulatedData.getPersonNumberList(),
                simulatedData.getStatusList());

        myListAdapter.setOnItemClickLitener(new MainListAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " click", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
        // 设置Adapter
        recyclerView.setAdapter(myListAdapter);
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}

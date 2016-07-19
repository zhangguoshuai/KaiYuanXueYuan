package com.kaiyuanxueyuan.fragment.liveFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.UniversityVideoAdapter;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.fragment.mainFragment.MyFragment;
import com.kaiyuanxueyuan.utils.ShowInfo;

/**
 * 电台或电视直播
 * Created by 张国帅 on 2016/7/11.
 */
public class LiveRadioFragment extends  MyFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ImageView screen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.live_radio, null);
        initView(view);
        return view;
    }

    public static LiveRadioFragment newInstance(){
        LiveRadioFragment fragment = new LiveRadioFragment();
        return fragment;
    }

    private void initView(View view){

        recyclerView = (RecyclerView) view.findViewById(R.id.university_video_list);
        screen = (ImageView) view.findViewById(R.id.university_video_screen);
        // 创建数据集
        SimulatedData simulatedData = new SimulatedData();
        initRecycleView(recyclerView,simulatedData);
        screen.setOnClickListener(this);
    }

    public void initRecycleView(RecyclerView recyclerView,SimulatedData simulatedData){

        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        // 创建Adapter，并指定数据集
        UniversityVideoAdapter myListAdapter = new UniversityVideoAdapter(getActivity(), simulatedData.getImageList(),
                simulatedData.getTitleList(), simulatedData.getPersonNumberList(),
                simulatedData.getStatusList());

        myListAdapter.setOnItemClickLitener(new UniversityVideoAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
//                Intent intent = new Intent(getActivity(), VideoActivity.class);
//                startActivity(intent);
                ShowInfo.Toast(getActivity(),"点击的位置：" + position);

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

    }
}

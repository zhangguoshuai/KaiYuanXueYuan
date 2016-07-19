package com.kaiyuanxueyuan.fragment.mainFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.MyFragmentPager;
import com.kaiyuanxueyuan.fragment.managerFragment.NewsInstace;
import com.kaiyuanxueyuan.view.MyViewPager;
import com.kaiyuanxueyuan.view.ViewPagerIndicator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/8.
 */
public class LiveFragment extends MyFragment{

    private MyViewPager myViewPager;
    private ViewPagerIndicator mIndicator;
    private ArrayList<String> mTitles;
    private ArrayList<Fragment> mContents;
    private MyFragmentPager mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_live, container, false);
        initDate();
        initView(view);
        return view;
    }

    public static LiveFragment newInstance(){
        LiveFragment fragment = new LiveFragment();
        return fragment;
    }

    public void initDate(){

        mTitles = NewsInstace.getLiveTitle();
        mContents = NewsInstace.getLiveFragment();
    }

    private void initView(View view){

        myViewPager = (MyViewPager) view.findViewById(R.id.live_anchor_viewPager);
        mIndicator = (ViewPagerIndicator) view.findViewById(R.id.live_anchor_indicator);

        mAdapter = new MyFragmentPager(getChildFragmentManager(),mContents);

        myViewPager.setAdapter(mAdapter);
        mIndicator.setVisibaleTabCount(mContents.size());
        mIndicator.setTabItemTitles(mTitles);
        mIndicator.setTriangle(true);
        mIndicator.setViewPager(myViewPager, 0);
    }

}

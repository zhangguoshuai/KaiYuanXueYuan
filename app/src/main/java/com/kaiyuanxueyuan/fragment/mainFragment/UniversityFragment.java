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
 * 学府
 * Created by 张国帅 on 2016/7/11.
 */
public class UniversityFragment extends  MyFragment implements View.OnClickListener {

    private MyViewPager myViewPager;
    private ViewPagerIndicator mIndicator;
    private ArrayList<String> mTitles;
    private ArrayList<Fragment> mContents;
    private MyFragmentPager mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.main_university, null);
        initDate();
        initView(view);
        return view;
    }

    public static UniversityFragment newInstance(){
        UniversityFragment fragment = new UniversityFragment();
        return fragment;
    }

    public void initDate(){

        mTitles = NewsInstace.getUniversityTitle();
        mContents = NewsInstace.getUniversityFragment();
    }

    private void initView(View view){

        myViewPager = (MyViewPager) view.findViewById(R.id.university_viewPager);
        mIndicator = (ViewPagerIndicator) view.findViewById(R.id.university_indicator);

        mAdapter = new MyFragmentPager(getChildFragmentManager(),mContents);

        myViewPager.setAdapter(mAdapter);
        mIndicator.setVisibaleTabCount(mContents.size());
        mIndicator.setTabItemTitles(mTitles);
        mIndicator.setTriangle(true);
        mIndicator.setViewPager(myViewPager, 0);
    }


    @Override
    public void onClick(View v) {

    }
}

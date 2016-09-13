package com.kaiyuanxueyuan.fragment.universityFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.fragment.mainFragment.MyFragment;

/**
 * 学府博客中心
 * Created by 张国帅 on 2016/7/11.
 */
public class BlogFragment extends  MyFragment implements View.OnClickListener {

    public static final String BUNDLE_TITLE = "title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.university_blog, null);
        initView(view);
        return view;
    }

    public static BlogFragment newInstance(){
        BlogFragment fragment = new BlogFragment();
        return fragment;
    }

    private void initView(View view){

    }

    @Override
    public void onClick(View v) {

    }
}

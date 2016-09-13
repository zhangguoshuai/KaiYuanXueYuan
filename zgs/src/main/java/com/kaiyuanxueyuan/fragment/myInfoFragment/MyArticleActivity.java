package com.kaiyuanxueyuan.fragment.myInfoFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.MyFragmentPager;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.view.MyViewPager;
import com.kaiyuanxueyuan.view.ViewPagerIndicator;

import java.util.ArrayList;

/**
 *  我的文章 我的笔记  我的猿问 三合一
 * Created by 张国帅 on 2016/6/23.
 *
 */
public class MyArticleActivity extends FragmentActivity implements View.OnClickListener {
	
	private MyViewPager myViewPager;
	private ViewPagerIndicator mIndicator;
    // 此处可以传入 title、Fragment，也可以传入指令 自己定义去获取title
	private ArrayList<String> mTitles;
	private ArrayList<Fragment> mContents;
	private MyFragmentPager mAdapter;
	private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.my_article);
        getMyIntent();
        initViews();
        initDatas();        
      
    }

    private void getMyIntent(){
        Intent intent = this.getIntent();
        String flage  = intent.getStringExtra("flage");
        doAction(flage);

    }

    private void doAction(String flage){
        int who = Integer.parseInt(flage);
        switch (who){

            case 0:
                mTitles = SimulatedData.MyArticleTitle();
                mContents = SimulatedData.MyArticleFragement();
                break;
            case 1:
                mTitles = SimulatedData.MyNotesTitle();
                mContents = SimulatedData.MyArticleFragement();
                break;
            case 2:
                mTitles = SimulatedData.MyMonkeyTitle();
                mContents = SimulatedData.MyMonkeyFragement();
                break;
            default:
                break;
        }

    }



    private void initDatas() {

		mAdapter = new MyFragmentPager(getSupportFragmentManager(),mContents);
		
        myViewPager.setAdapter(mAdapter);       
        mIndicator.setVisibaleTabCount(mContents.size());
        mIndicator.setTabItemTitles(mTitles);
        mIndicator.setTriangle(true);
        mIndicator.setViewPager(myViewPager, 0);			
	}

	private void initViews() {

		myViewPager = (MyViewPager) findViewById(R.id.myArticle_viewPage);
		mIndicator = (ViewPagerIndicator) findViewById(R.id.myArticle_Indicator);
        back = (ImageView) findViewById(R.id.myArticle_back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

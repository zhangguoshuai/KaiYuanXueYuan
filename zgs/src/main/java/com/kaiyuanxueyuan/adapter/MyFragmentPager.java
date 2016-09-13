package com.kaiyuanxueyuan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 碎片适配器
 * @author Administrator
 *
 */
public class MyFragmentPager extends FragmentPagerAdapter {
	
	ArrayList<Fragment> mContents;

	public MyFragmentPager(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
	public MyFragmentPager(FragmentManager fm, ArrayList<Fragment> mContents) {
		super(fm);
		this.mContents = mContents;
	}

	public  void  setmContents(ArrayList<Fragment> mContents){
		this.mContents = mContents;

	}
	
	public void setList(ArrayList<Fragment> mContents){
		this.mContents = mContents;
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub	
		if (position > mContents.size()) {
			mContents.remove(position);
			return null;
		}
		
		return mContents.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mContents.size();
	}

}

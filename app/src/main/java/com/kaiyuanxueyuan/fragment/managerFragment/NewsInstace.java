package com.kaiyuanxueyuan.fragment.managerFragment;

import android.support.v4.app.Fragment;

import com.kaiyuanxueyuan.fragment.liveFragment.LiveAnchorFragment;
import com.kaiyuanxueyuan.fragment.liveFragment.LiveRadioFragment;
import com.kaiyuanxueyuan.fragment.universityFragment.BlogFragment;
import com.kaiyuanxueyuan.fragment.universityFragment.VideoFragment;

import java.util.ArrayList;

/**
 * 所有的碎片实例化都在此处
 * Created by Administrator on 2016/7/13.
 */
public class NewsInstace {

    /**University Fragment Manager*/
    public static ArrayList<String> getUniversityTitle(){

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(0, "视频中心");
        arrayList.add(1, "博客中心");
        return arrayList;
    }

    public static ArrayList<Fragment> getUniversityFragment() {

        ArrayList<Fragment> fragement = new ArrayList<Fragment>();
        ArrayList<String> title = getUniversityTitle();
        BlogFragment blogFragment = BlogFragment.newInstance();
        VideoFragment videoFragment = VideoFragment.newInstance();

        fragement.add(0, videoFragment);
        fragement.add(1, blogFragment);
        return fragement;
    }

    /**Live Fragment Manager*/
    public static ArrayList<String> getLiveTitle(){

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(0, "电台直播");
        arrayList.add(1, "网络主播");
        return arrayList;
    }

    public static ArrayList<Fragment> getLiveFragment() {

        ArrayList<Fragment> fragement = new ArrayList<Fragment>();
        ArrayList<String> title = getLiveTitle();
        LiveAnchorFragment liveAnchorFragment = LiveAnchorFragment.newInstance();
        LiveRadioFragment liveRadioFragment = LiveRadioFragment.newInstance();

        fragement.add(0, liveRadioFragment);
        fragement.add(1, liveAnchorFragment);
        return fragement;
    }

}

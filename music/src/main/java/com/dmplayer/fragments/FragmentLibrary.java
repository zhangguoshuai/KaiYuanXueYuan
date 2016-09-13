/*
 * This is the source code of DMPLayer for Android v. 1.0.0.
 * You should have received a copy of the license in this archive (see LICENSE).
 * Copyright @Dibakar_Mistry, 2015.
 */
package com.dmplayer.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmplayer.ApplicationDMPlayer;
import com.dmplayer.R;
import com.dmplayer.childfragment.ChildFragmentAlbum;
import com.dmplayer.childfragment.ChildFragmentAllSongs;
import com.dmplayer.childfragment.ChildFragmentArtists;
import com.dmplayer.childfragment.ChildFragmentFevorite;
import com.dmplayer.childfragment.ChildFragmentMostPlay;
import com.dmplayer.childfragment.MyMusicFragment;
import com.dmplayer.tablayout.SlidingTabLayout;

/***
 * 此类已被淘汰 优化时在处理
 */
public class FragmentLibrary extends Fragment {

    private final String[] TITLES = {"我的","在线"};
    private TypedValue typedValueToolbarHeight = new TypedValue();
    private ChildFragmentAllSongs childFragmentAllSongs;
    private ChildFragmentFevorite childFragmentFevorite;
    private ChildFragmentArtists childFragmentArtists;
    private ChildFragmentAlbum childFragmentAlbum;
    private ChildFragmentMostPlay childFragmentMostplay;


    private MyMusicFragment myMusicFragment;

    private MyPagerAdapter pagerAdapter;
    private ViewPager pager;
    private SlidingTabLayout tabs;
    private int tabsPaddingTop;

    public static FragmentLibrary newInstance(){
        FragmentLibrary fragment = new FragmentLibrary();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_library, null);
        ApplicationDMPlayer.getInstances(getActivity());
        setupView(v);
        return v;
    }

    private void setupView(View view) {
        pager = (ViewPager) view.findViewById(R.id.pager);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(pagerAdapter);
        tabs = (SlidingTabLayout) view.findViewById(R.id.tabs);
        tabs.setCustomTabView(R.layout.tablayout_item, R.id.tabText);
        tabs.setDistributeEvenly(false);
        // Tab indicator color
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.md_white_1000);
            }
        });
        tabs.setViewPager(pager);
    }

    public class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:

//                    myMusicFragment = MyMusicFragment.newInstance(position, getActivity());

//                    childFragmentAllSongs = ChildFragmentAllSongs.newInstance(position, getActivity());
                    return myMusicFragment;
                case 1:
                    childFragmentAlbum = ChildFragmentAlbum.newInstance(position);
                    return childFragmentAlbum;
//                case 2:
//                    childFragmentArtists = ChildFragmentArtists.newInstance(position, getActivity());
//                    return childFragmentArtists;
//                case 3:
//                    childFragmentFevorite = ChildFragmentFevorite.newInstance(position, getActivity());
//                    return childFragmentFevorite;
//                case 4:
//                    childFragmentMostplay = ChildFragmentMostPlay.newInstance(position, getActivity());
//                    return childFragmentMostplay;
            }
            return null;
        }
    }

    public int convertToPx(int dp) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (dp * scale + 0.5f);
    }
}

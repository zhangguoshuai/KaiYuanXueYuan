/*
 * This is the source code of DMPLayer for Android v. 1.0.0.
 * You should have received a copy of the license in this archive (see LICENSE).
 * Copyright @Dibakar_Mistry, 2015.
 */
package com.dmplayer.childfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmplayer.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 歌曲歌手分类
 */
public class ChildFragmentSinger extends Fragment {

    public interface OnSuccessListener {
        void back(String singer);
    }
    private static OnSuccessListener onSuccessListener;
    private ListView recycler_songslist;
    private AllSongsListAdapter mAllSongsListAdapter;
    private HashMap<String,ArrayList<String>> singerList = new HashMap<>();
    private ArrayList<String> musicName = new ArrayList<>();
    private ArrayList<Integer> musicNumber = new ArrayList<>();

    public static ChildFragmentSinger newInstance(OnSuccessListener listener) {
        ChildFragmentSinger f = new ChildFragmentSinger();
        onSuccessListener = listener;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_allsongs, null);
        loadAllSongs();
        setupInitialViews(v);
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupInitialViews(View inflatreView) {

        recycler_songslist = (ListView) inflatreView.findViewById(R.id.recycler_allSongs);
        mAllSongsListAdapter = new AllSongsListAdapter(getActivity(),musicName,musicNumber);
        recycler_songslist.setAdapter(mAllSongsListAdapter);

    }

    private void loadAllSongs(){

        MusicInfo musicInfo = new MusicInfo(getActivity());
        singerList = musicInfo.getSingerList();
        musicName = musicInfo.getMusicName();
        musicNumber = musicInfo.getMusicNumber();
    }



    public class AllSongsListAdapter extends BaseAdapter {

        private ArrayList<String> musicName = new ArrayList<>();
        private ArrayList<Integer> musicNumber = new ArrayList<>();
        private FragmentActivity activity = null;
        private LayoutInflater layoutInflater;

        public AllSongsListAdapter(FragmentActivity activity,ArrayList<String> musicName,ArrayList<Integer> musicNumber) {
            this.activity = activity;
            this.layoutInflater = LayoutInflater.from(activity);
            this.musicName = musicName;
            this.musicNumber = musicNumber;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder mViewHolder;
            if (convertView == null) {
                mViewHolder = new ViewHolder();
                convertView = layoutInflater.inflate(R.layout.inflate_singer_item, null);
                mViewHolder.singerLayout = (LinearLayout) convertView.findViewById(R.id.singerLayout);
                mViewHolder.singerName = (TextView) convertView.findViewById(R.id.singer_name);
                mViewHolder.singerNumber = (TextView) convertView.findViewById(R.id.singer_number);
                mViewHolder.singerIcon = (ImageView) convertView.findViewById(R.id.singer_icon);

                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }

            mViewHolder.singerName.setText(musicName.get(position));
            mViewHolder.singerNumber.setText(musicNumber.get(position ) + "首歌曲");
            mViewHolder.singerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSuccessListener.back(musicName.get(position));
                }
            });

            return convertView;
        }

        @Override
        public int getCount() {
            return (musicName != null) ? musicName.size() : 0;
        }

        class ViewHolder {
            TextView singerName;
            ImageView singerIcon;
            TextView singerNumber;
            LinearLayout singerLayout;
        }
    }

}

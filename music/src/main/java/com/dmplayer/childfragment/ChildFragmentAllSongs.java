/*
 * This is the source code of DMPLayer for Android v. 1.0.0.
 * You should have received a copy of the license in this archive (see LICENSE).
 * Copyright @Dibakar_Mistry, 2015.
 */
package com.dmplayer.childfragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dmplayer.R;
import com.dmplayer.manager.MediaController;
import com.dmplayer.models.SongDetail;
import com.dmplayer.phonemidea.PhoneMediaControl;
import com.dmplayer.phonemidea.PhoneMediaControl.PhoneMediaControlINterface;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * 本地所有歌曲
 */

public class ChildFragmentAllSongs extends Fragment {

    private ListView recycler_songslist;
    private AllSongsListAdapter mAllSongsListAdapter;
    private ArrayList<SongDetail> songList = new ArrayList<SongDetail>();
    private ArrayList<String> albumList = new ArrayList<>();
    private ArrayList<Bitmap> singerArtist = new ArrayList<>();
    public static ChildFragmentAllSongs newInstance(int position) {
        ChildFragmentAllSongs f = new ChildFragmentAllSongs();
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
        mAllSongsListAdapter = new AllSongsListAdapter(getActivity());
        recycler_songslist.setAdapter(mAllSongsListAdapter);
    }

    private void loadAllSongs() {

        MusicInfo musicInfo = new MusicInfo(getActivity());
        albumList = musicInfo.getSingerAlbum();
        PhoneMediaControl mPhoneMediaControl = PhoneMediaControl.getInstance();
        mPhoneMediaControl.setPhonemediacontrolinterface(new PhoneMediaControlINterface() {

            @Override
            public void loadSongsComplete(ArrayList<SongDetail> songsList_) {
                songList = songsList_;
                mAllSongsListAdapter.notifyDataSetChanged();
            }
        });
        mPhoneMediaControl.loadMusicList(getActivity(), -1, PhoneMediaControl.SonLoadFor.All, "");
    }



    public class AllSongsListAdapter extends BaseAdapter {
        private FragmentActivity activity = null;
        private LayoutInflater layoutInflater;
        private DisplayImageOptions options;
        private ImageLoader imageLoader = ImageLoader.getInstance();

        public AllSongsListAdapter(FragmentActivity activity) {
            this.activity = activity;
            this.layoutInflater = LayoutInflater.from(activity);
            this.options = new DisplayImageOptions.Builder().showImageOnLoading(R.mipmap.bg_default_album_art)
                    .showImageForEmptyUri(R.mipmap.bg_default_album_art).showImageOnFail(R.mipmap.bg_default_album_art).cacheInMemory(true)
                    .cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
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
                convertView = layoutInflater.inflate(R.layout.inflate_allsongsitem, null);
                mViewHolder.song_row = (LinearLayout) convertView.findViewById(R.id.inflate_allsong_row);
                mViewHolder.textViewSongName = (TextView) convertView.findViewById(R.id.inflate_allsong_textsongname);
                mViewHolder.textViewSongArtisNameAndDuration = (TextView) convertView.findViewById(R.id.inflate_allsong_textsongArtisName_duration);
                mViewHolder.imageSongThm = (ImageView) convertView.findViewById(R.id.inflate_allsong_imgSongThumb);
                mViewHolder.imagemore = (ImageView) convertView.findViewById(R.id.img_moreicon);
                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }
            SongDetail mDetail = songList.get(position);

//            mViewHolder.textViewSongArtisNameAndDuration.setText((audioDuration.isEmpty() ? "" : audioDuration + " | ")
//                    + (mDetail.getArtist().contains("???")?"未知": mDetail.getArtist()));
            mViewHolder.textViewSongArtisNameAndDuration.setText(mDetail.getArtist() + " | " + albumList.get(position));
            mViewHolder.textViewSongName.setText(mDetail.getTitle().contains("???")?mDetail.getDisplay_name():mDetail.getTitle());

            String url = "http://pic17.nipic.com/20111013/8115374_115441161000_2.jpg";
            String contentURI = "content://media/external/audio/media/" + mDetail.getId() + "/albumart";
            imageLoader.displayImage(contentURI, mViewHolder.imageSongThm, options);
            Log.i("music info", "queryAllImage --- all column name --- " + mDetail.getId() +
                     "   " + mDetail.getArtist() + "  " + mDetail.getTitle() + "  "+  mDetail.getPath());
            convertView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    SongDetail mDetail = songList.get(position);
                    ((MyMusicActivity) activity).loadSongsDetails(mDetail);

                    if (mDetail != null) {
                        if (MediaController.getInstance().isPlayingAudio(mDetail) && !MediaController.getInstance().isAudioPaused()) {
                            MediaController.getInstance().pauseAudio(mDetail);
                        } else {
                            MediaController.getInstance().setPlaylist(songList, mDetail, PhoneMediaControl.SonLoadFor.All.ordinal(), -1);
                        }
                    }

                }
            });

            mViewHolder.imagemore.setColorFilter(Color.DKGRAY);
            if (Build.VERSION.SDK_INT > 15) {
                mViewHolder.imagemore.setImageAlpha(255);
            } else {
                mViewHolder.imagemore.setAlpha(255);
            }

            mViewHolder.imagemore.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    MusicAction musicAction = new MusicAction(activity);
                    musicAction.show(v);
                }
            });

            return convertView;
        }

        @Override
        public int getCount() {
            return (songList != null) ? songList.size() : 0;
        }

        class ViewHolder {
            TextView textViewSongName;
            ImageView imageSongThm, imagemore;
            TextView textViewSongArtisNameAndDuration;
            LinearLayout song_row;
        }
    }

}

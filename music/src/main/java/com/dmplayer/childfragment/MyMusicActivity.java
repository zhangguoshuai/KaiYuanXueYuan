package com.dmplayer.childfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmplayer.R;
import com.dmplayer.fragments.FragmentEqualizer;
import com.dmplayer.fragments.FragmentSettings;
import com.dmplayer.manager.MediaController;
import com.dmplayer.manager.MusicPreferance;
import com.dmplayer.manager.NotificationManager;
import com.dmplayer.models.SongDetail;
import com.dmplayer.phonemidea.DMPlayerUtility;
import com.dmplayer.slidinguppanelhelper.SlidingUpPanelLayout;
import com.dmplayer.uicomponent.PlayPauseView;
import com.dmplayer.uicomponent.Slider;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class MyMusicActivity extends AppCompatActivity implements View.OnClickListener,
        Slider.OnValueChangedListener, NotificationManager.NotificationCenterDelegate{

    private static final String TAG = "MyMusicActivity";
    private SharedPreferences sharedPreferences;
    private TextView title;
    private ImageView back;
    private SlidingUpPanelLayout mLayout;
    private RelativeLayout slidepanelchildtwo_topviewone;
    private RelativeLayout slidepanelchildtwo_topviewtwo;
    private ImageView songAlbumbg;
    private ImageView img_bottom_slideone;
    private ImageView img_bottom_slidetwo;
    private TextView txt_playesongname;
    private TextView txt_songartistname;
    private TextView txt_playesongname_slidetoptwo;
    private TextView txt_songartistname_slidetoptwo;
    private TextView txt_timeprogress;
    private TextView txt_timetotal;
    private ImageView imgbtn_backward;
    private ImageView imgbtn_forward;
    private ImageView imgbtn_toggle;
    private ImageView imgbtn_suffel;
    private ImageView img_Favorite;
    private PlayPauseView btn_playpause;
    private PlayPauseView btn_playpausePanel;
    private Slider audio_progress;
    private boolean isExpand = false;
    private DisplayImageOptions options;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    private boolean isDragingStart = false;
    private int TAG_Observer;
    private String singerName = "";
    private int onfragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.include_music);
        title = (TextView) findViewById(R.id.music_activity_title);
        back = (ImageView) findViewById(R.id.music_activity_back);
        getMyIntent();
        initiSlidingUpPanel();

    }

    @Override
    protected void onResume() {
        super.onResume();
        addObserver();
        loadAlreadyPlayng();
    }

    @Override
    protected void onPause() {
        super.onPause();
        removeObserver();
    }

    @Override
    protected void onDestroy() {
        removeObserver();
        if (MediaController.getInstance().isAudioPaused()) {
            MediaController.getInstance().cleanupPlayer(this, true, true);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (isExpand) {
            mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
            overridePendingTransition(0, 0);
            finish();
        }
    }


    public void getMyIntent(){

        Intent intent = this.getIntent();
        int position = intent.getIntExtra("position",0);
        setFragment(position);

    }

    private void initiSlidingUpPanel() {

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        songAlbumbg = (ImageView) findViewById(R.id.image_songAlbumbg_mid);
        img_bottom_slideone = (ImageView) findViewById(R.id.img_bottom_slideone);
        img_bottom_slidetwo = (ImageView) findViewById(R.id.img_bottom_slidetwo);

        txt_timeprogress = (TextView) findViewById(R.id.slidepanel_time_progress);
        txt_timetotal = (TextView) findViewById(R.id.slidepanel_time_total);
        imgbtn_backward = (ImageView) findViewById(R.id.btn_backward);
        imgbtn_forward = (ImageView) findViewById(R.id.btn_forward);
        imgbtn_toggle = (ImageView) findViewById(R.id.btn_toggle);
        imgbtn_suffel = (ImageView) findViewById(R.id.btn_suffel);
        btn_playpause = (PlayPauseView) findViewById(R.id.btn_play);
        audio_progress = (Slider) findViewById(R.id.audio_progress_control);
        btn_playpausePanel = (PlayPauseView) findViewById(R.id.bottombar_play);
        img_Favorite = (ImageView) findViewById(R.id.bottombar_img_Favorite);

        TypedValue typedvaluecoloraccent = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorAccent, typedvaluecoloraccent, true);
        final int coloraccent = typedvaluecoloraccent.data;
        audio_progress.setBackgroundColor(coloraccent);
        audio_progress.setValue(0);

        audio_progress.setOnValueChangedListener(this);
        imgbtn_backward.setOnClickListener(this);
        imgbtn_forward.setOnClickListener(this);
        imgbtn_toggle.setOnClickListener(this);
        imgbtn_suffel.setOnClickListener(this);
        img_Favorite.setOnClickListener(this);
        back.setOnClickListener(this);
        btn_playpausePanel.Pause();
        btn_playpause.Pause();

        txt_playesongname = (TextView) findViewById(R.id.txt_playesongname);
        txt_songartistname = (TextView) findViewById(R.id.txt_songartistname);
        txt_playesongname_slidetoptwo = (TextView) findViewById(R.id.txt_playesongname_slidetoptwo);
        txt_songartistname_slidetoptwo = (TextView) findViewById(R.id.txt_songartistname_slidetoptwo);

        slidepanelchildtwo_topviewone = (RelativeLayout) findViewById(R.id.slidepanelchildtwo_topviewone);
        slidepanelchildtwo_topviewtwo = (RelativeLayout) findViewById(R.id.slidepanelchildtwo_topviewtwo);

        slidepanelchildtwo_topviewone.setVisibility(View.VISIBLE);
        slidepanelchildtwo_topviewtwo.setVisibility(View.INVISIBLE);

        slidepanelchildtwo_topviewone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

            }
        });

        slidepanelchildtwo_topviewtwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

            }
        });

        findViewById(R.id.bottombar_play).setOnClickListener(this);
        findViewById(R.id.btn_play).setOnClickListener(this);

        imgbtn_toggle.setSelected((MusicPreferance.getRepeat(this) == 1) ? true : false);
        MediaController.getInstance().shuffleMusic = imgbtn_toggle.isSelected() ? true : false;
        DMPlayerUtility.changeColorSet(this, imgbtn_toggle, imgbtn_toggle.isSelected());

        imgbtn_suffel.setSelected(MusicPreferance.getShuffel(this) ? true : false);
        MediaController.getInstance().repeatMode = imgbtn_suffel.isSelected() ? 1 : 0;
        DMPlayerUtility.changeColorSet(this, imgbtn_suffel, imgbtn_suffel.isSelected());

        MediaController.getInstance().shuffleList(MusicPreferance.playlist);

        mLayout.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);

                if (slideOffset == 0.0f) {
                    isExpand = false;
                    slidepanelchildtwo_topviewone.setVisibility(View.VISIBLE);
                    slidepanelchildtwo_topviewtwo.setVisibility(View.INVISIBLE);
                } else if (slideOffset > 0.0f && slideOffset < 1.0f) {
                    // if (isExpand) {
                    // slidepanelchildtwo_topviewone.setAlpha(1.0f);
                    // slidepanelchildtwo_topviewtwo.setAlpha(1.0f -
                    // slideOffset);
                    // } else {
                    // slidepanelchildtwo_topviewone.setAlpha(1.0f -
                    // slideOffset);
                    // slidepanelchildtwo_topviewtwo.setAlpha(1.0f);
                    // }

                } else {
                    isExpand = true;
                    slidepanelchildtwo_topviewone.setVisibility(View.INVISIBLE);
                    slidepanelchildtwo_topviewtwo.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPanelExpanded(View panel) {
                Log.i(TAG, "onPanelExpanded");
                isExpand = true;
            }

            @Override
            public void onPanelCollapsed(View panel) {
                Log.i(TAG, "onPanelCollapsed");
                isExpand = false;
            }

            @Override
            public void onPanelAnchored(View panel) {
                Log.i(TAG, "onPanelAnchored");
            }

            @Override
            public void onPanelHidden(View panel) {
                Log.i(TAG, "onPanelHidden");
            }
        });

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if(id == R.id.music_activity_back){
            if (onfragment == 6){
                setFragment(5);
            }else{
                finish();
            }
        }else if(id == R.id.bottombar_play){
            if (MediaController.getInstance().getPlayingSongDetail() != null)
                PlayPauseEvent(v);
        }else if(id == R.id.btn_play){
            if (MediaController.getInstance().getPlayingSongDetail() != null)
                PlayPauseEvent(v);
        }else if(id == R.id.btn_forward){
            if (MediaController.getInstance().getPlayingSongDetail() != null)
                MediaController.getInstance().playNextSong();
        }else if(id == R.id.btn_backward){
            if (MediaController.getInstance().getPlayingSongDetail() != null)
                MediaController.getInstance().playPreviousSong();
        }else if(id == R.id.btn_suffel){
            v.setSelected(v.isSelected() ? false : true);
            MediaController.getInstance().shuffleMusic = v.isSelected() ? true : false;
            MusicPreferance.setShuffel(this, (v.isSelected() ? true : false));
            MediaController.getInstance().shuffleList(MusicPreferance.playlist);
            DMPlayerUtility.changeColorSet(this, (ImageView) v, v.isSelected());
        }else if(id == R.id.btn_toggle){
            v.setSelected(v.isSelected() ? false : true);
            MediaController.getInstance().repeatMode = v.isSelected() ? 1 : 0;
            MusicPreferance.setRepeat(this, (v.isSelected() ? 1 : 0));
            DMPlayerUtility.changeColorSet(this, (ImageView) v, v.isSelected());
        }else if(id == R.id.bottombar_img_Favorite){
            if (MediaController.getInstance().getPlayingSongDetail() != null) {
                MediaController.getInstance().storeFavoritePlay(this, MediaController.getInstance().getPlayingSongDetail(), v.isSelected() ? 0 : 1);
                v.setSelected(v.isSelected() ? false : true);
                DMPlayerUtility.animateHeartButton(v);
                findViewById(R.id.ivLike).setSelected(v.isSelected() ? true : false);
                DMPlayerUtility.animatePhotoLike(findViewById(R.id.vBgLike), findViewById(R.id.ivLike));
            }
        }
    }

    public void setFragment(int position) {
        onfragment = position;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (position) {
            case 0:

                ChildFragmentAllSongs childFragmentAllSongs = ChildFragmentAllSongs.newInstance(position);
                fragmentTransaction.replace(R.id.music_activity_fragment, childFragmentAllSongs);
                fragmentTransaction.commit();
                title.setText("本地歌曲");
                break;

            case 1:

                ChildFragmentFevorite childFragmentFevorite = ChildFragmentFevorite.newInstance(position);
                fragmentTransaction.replace(R.id.music_activity_fragment, childFragmentFevorite);
                fragmentTransaction.commit();
                title.setText("我喜欢的");
                break;

            case 2:

                ChildFragmentSinger childFragmentSinger = ChildFragmentSinger.newInstance(new ChildFragmentSinger.OnSuccessListener() {
                    @Override
                    public void back(String singer) {
                        singerName = singer;
                        setFragment(7);
                    }
                });
                fragmentTransaction.replace(R.id.music_activity_fragment, childFragmentSinger);
                fragmentTransaction.commit();
                title.setText("歌手分类");
                break;

            case 3:

                ChildFragmentAlbum childFragmentAlbum = ChildFragmentAlbum.newInstance(position);
                fragmentTransaction.replace(R.id.music_activity_fragment, childFragmentAlbum);
                fragmentTransaction.commit();
                title.setText("专辑分类");
                break;

            case 4:

                FragmentSettings fragmentsettings = new FragmentSettings();
                fragmentTransaction.replace(R.id.music_activity_fragment, fragmentsettings);
                fragmentTransaction.commit();
                title.setText("MV视频");
                break;

            case 5:

                FragmentEqualizer fragmentequalizer = new FragmentEqualizer();
                fragmentTransaction.replace(R.id.music_activity_fragment, fragmentequalizer);
                fragmentTransaction.commit();
                title.setText("下载管理");
                break;

            case 6:

                title.setText("新增目录");
                break;

            case 7:

                ChildFragmentSingerAllSongs childFragmentSingerAllSongs = ChildFragmentSingerAllSongs.newInstance(singerName,this);
                fragmentTransaction.replace(R.id.music_activity_fragment, childFragmentSingerAllSongs);
                fragmentTransaction.commit();
                title.setText(singerName);
                break;
        }
    }

    private void PlayPauseEvent(View v) {
        if (MediaController.getInstance().isAudioPaused()) {
            MediaController.getInstance().playAudio(MediaController.getInstance().getPlayingSongDetail());
            ((PlayPauseView) v).Play();
        } else {
            MediaController.getInstance().pauseAudio(MediaController.getInstance().getPlayingSongDetail());
            ((PlayPauseView) v).Pause();
        }
    }

    @Override
    public void onValueChanged(int value) {
        MediaController.getInstance().seekToProgress(MediaController.getInstance().getPlayingSongDetail(), (float) value / 100);
    }

    public void loadSongsDetails(SongDetail mDetail) {
        String contentURI = "content://media/external/audio/media/" + mDetail.getId() + "/albumart";
        imageLoader.displayImage(contentURI, songAlbumbg, options, animateFirstListener);
        imageLoader.displayImage(contentURI, img_bottom_slideone, options, animateFirstListener);
        imageLoader.displayImage(contentURI, img_bottom_slidetwo, options, animateFirstListener);

        txt_playesongname.setText(mDetail.getTitle().contains("???")?mDetail.getDisplay_name():mDetail.getTitle());
        txt_songartistname.setText(mDetail.getArtist().contains("???")?"未知": mDetail.getArtist());
        txt_playesongname_slidetoptwo.setText(mDetail.getTitle().contains("???")?mDetail.getDisplay_name():mDetail.getTitle());
        txt_songartistname_slidetoptwo.setText(mDetail.getArtist().contains("???")?"未知": mDetail.getArtist());

        if (txt_timetotal != null) {
            long duration = Long.valueOf(mDetail.getDuration());
            txt_timetotal.setText(mDetail.time);
        }
        updateProgress(mDetail);
    }

    @Override
    public void didReceivedNotification(int id, Object... args) {
        if (id == NotificationManager.audioDidStarted || id == NotificationManager.audioPlayStateChanged || id == NotificationManager.audioDidReset) {
            updateTitle(id == NotificationManager.audioDidReset && (Boolean) args[1]);
        } else if (id == NotificationManager.audioProgressDidChanged) {
            SongDetail mSongDetail = MediaController.getInstance().getPlayingSongDetail();
            updateProgress(mSongDetail);
        }
    }

    @Override
    public void newSongLoaded(Object... args) {
        MediaController.getInstance().checkIsFavorite(this, (SongDetail) args[0], img_Favorite);
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

    private void updateProgress(SongDetail mSongDetail) {
        if (audio_progress != null) {
            // When SeekBar Draging Don't Show Progress
            if (!isDragingStart) {
                // Progress Value comming in point it range 0 to 1
                audio_progress.setValue((int) (mSongDetail.audioProgress * 100));
            }
            String timeString = String.format("%d:%02d", mSongDetail.audioProgressSec / 60, mSongDetail.audioProgressSec % 60);
            txt_timeprogress.setText(timeString);
        }
    }

    public void addObserver() {
        TAG_Observer = MediaController.getInstance().generateObserverTag();
        NotificationManager.getInstance().addObserver(this, NotificationManager.audioDidReset);
        NotificationManager.getInstance().addObserver(this, NotificationManager.audioPlayStateChanged);
        NotificationManager.getInstance().addObserver(this, NotificationManager.audioDidStarted);
        NotificationManager.getInstance().addObserver(this, NotificationManager.audioProgressDidChanged);
        NotificationManager.getInstance().addObserver(this, NotificationManager.newaudioloaded);
    }

    public void removeObserver() {
        NotificationManager.getInstance().removeObserver(this, NotificationManager.audioDidReset);
        NotificationManager.getInstance().removeObserver(this, NotificationManager.audioPlayStateChanged);
        NotificationManager.getInstance().removeObserver(this, NotificationManager.audioDidStarted);
        NotificationManager.getInstance().removeObserver(this, NotificationManager.audioProgressDidChanged);
        NotificationManager.getInstance().removeObserver(this, NotificationManager.newaudioloaded);
    }

    private void loadAlreadyPlayng() {
        SongDetail mSongDetail = MusicPreferance.getLastSong(this);
        ArrayList<SongDetail> playlist = MusicPreferance.getPlaylist(this);
        if (mSongDetail != null) {
            updateTitle(false);
        }
        MediaController.getInstance().checkIsFavorite(this, mSongDetail, img_Favorite);
    }

    private void updateTitle(boolean shutdown) {
        SongDetail mSongDetail = MediaController.getInstance().getPlayingSongDetail();
        if (mSongDetail == null && shutdown) {
            return;
        } else {
            updateProgress(mSongDetail);
            if (MediaController.getInstance().isAudioPaused()) {
                btn_playpausePanel.Pause();
                btn_playpause.Pause();
            } else {
                btn_playpausePanel.Play();
                btn_playpause.Play();
            }
                       SongDetail audioInfo = MediaController.getInstance().getPlayingSongDetail();
            loadSongsDetails(audioInfo);

            if (txt_timetotal != null) {
                long duration = Long.valueOf(audioInfo.getDuration());
//                txt_timetotal.setText(duration != 0 ? String.format("%d:%02d", duration / 60, duration % 60) : "--:--");
                txt_timetotal.setText(mSongDetail.time);
            }
        }
    }
}

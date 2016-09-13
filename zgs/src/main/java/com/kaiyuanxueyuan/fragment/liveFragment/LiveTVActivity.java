package com.kaiyuanxueyuan.fragment.liveFragment;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.MyFragmentPager;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.popupwindow.PlayerShare;
import com.kaiyuanxueyuan.server.PlayerServer;
import com.kaiyuanxueyuan.view.MyViewPager;
import com.kaiyuanxueyuan.view.ViewPagerIndicator;

import java.util.ArrayList;

import cn.com.video.venvy.param.JjVideoRelativeLayout;
import cn.com.video.venvy.param.JjVideoView;
import cn.com.video.venvy.param.OnJjBufferCompleteListener;
import cn.com.video.venvy.param.OnJjBufferStartListener;
import cn.com.video.venvy.param.OnJjBufferingUpdateListener;
import cn.com.video.venvy.param.OnJjOpenStartListener;
import cn.com.video.venvy.param.OnJjOpenSuccessListener;
import cn.com.video.venvy.param.OnJjOutsideLinkClickListener;
import cn.com.video.venvy.widget.UsetMediaContoller;

/**
 * 电视台频道直播
 */
public class LiveTVActivity extends FragmentActivity implements View.OnClickListener {

	private JjVideoView mVideoView;
	private View mLoadBufferView;
	private TextView mLoadBufferTextView;
	private View mLoadView;
	private TextView mLoadText;
    private ImageView playDownload,action;
    private LinearLayout actionbar;
    private boolean isfull = false;
    private TextView title;
	private MyViewPager myViewPager;
	private ViewPagerIndicator mIndicator;
	private ArrayList<String> mTitles = SimulatedData.PlayerTitle();
	private ArrayList<Fragment> mContents = SimulatedData.PlayerFragement();
	private MyFragmentPager mAdapter;
    private UsetMediaContoller usetMediaContoller;
    private PlayerShare playerShare;
    //private String TVPath = "http://stream2.ahtv.cn/ahws/hd/live.m3u8";//安徽卫视
    //private String TVPath = "rtmp://116.199.115.228/live/gztv_tv";//广州
    private String TVPath = "rtmp://live.hkstv.hk.lxdns.com/live/hks";//东莞
    // 服务
    private PlayerServer.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(getApplicationContext(), "后台服务已经断开", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (PlayerServer.MyBinder) service;
            myBinder.player();
        }
    };

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
		setContentView(R.layout.video_tv);

        initView();
        initVideoView();
	}

    /***
     * sdk_ijk_progress_bar_layout 类似这种是由video++ 第三方命名，尊重其作品未修改
     * 有兴趣可到其官方网站
     */
	public void initView(){

        mVideoView = (JjVideoView) findViewById(R.id.video_tv_videoView);
        mLoadView = findViewById(R.id.sdk_ijk_progress_bar_layout);
        mLoadText = (TextView) findViewById(R.id.sdk_ijk_progress_bar_text);
        mLoadBufferView = findViewById(R.id.sdk_load_layout);
        mLoadBufferTextView = (TextView) findViewById(R.id.sdk_sdk_ijk_load_buffer_text);
        actionbar = (LinearLayout) findViewById(R.id.video_tv_actionbar);
        action = (ImageView) findViewById(R.id.video_tv_edit);
        title = (TextView) findViewById(R.id.video_tv_title);
        title.setText("香港电视台");
        action.setOnClickListener(this);
	}

    public void initVideoView(){
        /**
         * 视频控制器
         */
         usetMediaContoller = new UsetMediaContoller(this, new UsetMediaContoller.OnStausListener() {
            @Override
            public void isFull(boolean isFull) {
                           isfull = isFull;
                if(isFull){
                    actionbar.setVisibility(View.GONE);
                    mVideoView.scale(1);
                }else{
                    mVideoView.scale(2);
                    actionbar.setVisibility(View.VISIBLE);
                }

            }
        });

        //mVideoView.setMediaController(new VideoJjMediaContoller(this, true));
        mVideoView.setMediaController(usetMediaContoller);

        mLoadBufferTextView.setTextColor(Color.RED);

        /***
         * 用户自定义的外链 可 获取外链点击时间
         */
        mVideoView.setOnJjOutsideLinkClickListener(new OnJjOutsideLinkClickListener() {

            @Override
            public void onJjOutsideLinkClick(String arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onJjOutsideLinkClose() {
                // TODO Auto-generated method stub

            }
        });
        /***
         * 设置缓冲
         */
        mVideoView.setMediaBufferingView(mLoadBufferView);
        /***
         * 视频开始加载数据
         */
        mVideoView.setOnJjOpenStart(new OnJjOpenStartListener() {

            @Override
            public void onJjOpenStart(String arg0) {
                mLoadText.setText(arg0);
            }
        });

        /***
         * 视频开始播放
         */
        mVideoView.setOnJjOpenSuccess(new OnJjOpenSuccessListener() {

            @Override
            public void onJjOpenSuccess() {
                mLoadView.setVisibility(View.GONE);
            }
        });

        /**
         * 缓冲开始
         */
        mVideoView.setOnJjBufferStart(new OnJjBufferStartListener() {

            @Override
            public void onJjBufferStartListener(int arg0) {
                Log.e("Video++", "====================缓冲值=====" + arg0);
            }
        });

        mVideoView.setOnJjBufferingUpdateListener(new OnJjBufferingUpdateListener() {

            @Override
            public void onJjBufferingUpdate(int arg1) {
                // TODO Auto-generated method stub
                if (mLoadBufferView.getVisibility() == View.VISIBLE) {
                    mLoadBufferTextView.setText(String.valueOf(mVideoView.getBufferPercentage()) + "%");
                    Log.e("Video++", "====================缓冲值2=====" + arg1);
                }
            }
        });

        /**
         *  缓冲完成
         */
        mVideoView.setOnJjBufferComplete(new OnJjBufferCompleteListener() {

            @Override
            public void onJjBufferCompleteListener(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        /**
         * 注意VideoView 要调用下面方法 配置你用户信息 com.example.videojjsdkdemo
         */
        mVideoView.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mVideoView.setVideoJjAppKey("NyaNf6yub");
        mVideoView.setVideoJjPageName("com.kaiyuanxueyuan");
        mVideoView.setMediaCodecEnabled(true);// 是否开启 硬解 硬解对一些手机有限制
        /**
         *  判断是否源 0 代表 8大视频网站url 3代表自己服务器的视频源 2代表直播地址 1代表本地视频(手机上的视频源),4特殊需求
         */
        mVideoView.setVideoJjType(2);
        /**
         * 视频标签显示的时间 默认显示5000毫秒 可设置 传入值 long类型 毫秒
         */

        /**
         * 参数代表是否记录视频播放位置 默认false不记录 true代表第二次或多次进入，直接跳转到上次退出的时间点开始播放
         */
        mVideoView.setVideoJjSaveExitTime(true);
        /**
         * 指定时间开始播放 毫秒 http://7xr5j6.com1.z0.glb.clouddn.com/hunantv0129.mp4?v=3
         */
        mVideoView.setResourceVideo(TVPath);
        /**
         * 设置画面比例 0：默认，1：16:9 2: 4:3
         */
        mVideoView.scale(2);
        mVideoView.start();

        RelativeLayout mLayout = (RelativeLayout) findViewById(R.id.video_tv);
        JjVideoRelativeLayout mJjVideoRelativeLayout = (JjVideoRelativeLayout) findViewById(R.id.video_tv_jjlayout);
        mJjVideoRelativeLayout.setJjToFront(mLayout);// 设置此方法重新排序视图层级JjVideoRelativeLayout，避免横屏其它遮挡
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 必须调用 要不直播有问题
		if (mVideoView != null){
            mVideoView.onDestroy();
        }
	}

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            if (isfull) {
                usetMediaContoller.changeScreenlandscape();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.player_edit:
                playerShare(v);
                break;
            default:

                break;
        }

    }

    public void playerShare(View v){
        if (playerShare == null){
            playerShare = new PlayerShare(this, new PlayerShare.OnSuccessListener() {
                @Override
                public void back(boolean exit) {

                    if (exit){
                        mVideoView.resume();
                    }
                }
            });
        }
        playerShare.show(v);
        mVideoView.pause();
    }
}

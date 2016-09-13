package com.kaiyuanxueyuan.server;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kaiyuanxueyuan.utils.NetWork;

import cn.com.video.venvy.param.JjVideoView;

/**
 * Created by Administrator on 2016/7/6.
 */
public class PlayerServer extends Service {

    private String TAG = "PlayerServer";

    private NetWork netWork = new NetWork();

    private MyBinder mBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    public class MyBinder extends Binder {

        JjVideoView mVideoView;

        public MyBinder(){

        }

        public void setVideoView(JjVideoView VideoView){
            this.mVideoView = VideoView;
        }

        //boolean statue = getNetWork(mainActivity);

        public void player(){



        }

        public boolean getNetWork(Context context){
            int netstatue = -1 ;
            boolean net = false;
            if (netWork.isNetworkConnected(context)) {
                netstatue = netWork.GetNetype(context);
            }
            switch (netstatue) {
                case -1:
                    break;
                case 1:
                    net = netWork.isWifiConnected(context);
                    break;
                case 2:
                    net = netWork.isMobileConnected(context);
                    break;
                case 3:
                    net = netWork.isMobileConnected(context);
                    break;

                default:
                    break;
            }
            return net;
        }
    }
}

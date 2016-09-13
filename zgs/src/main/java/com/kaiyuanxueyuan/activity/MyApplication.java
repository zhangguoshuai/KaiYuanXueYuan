package com.kaiyuanxueyuan.activity;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;

import com.kaiyuanxueyuan.BuildConfig;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.rey.material.app.ThemeManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Rey on 5/22/2015.
 */
public class MyApplication extends Application{

    public static Context applicationContext = null;
    public static volatile Handler applicationHandler = null;
    public static Point displaySize = new Point();
    public static float density = 1;

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG)
            refWatcher = LeakCanary.install(this);
            ThemeManager.init(this, 2, 0, null);
//        /** music*/
//        applicationContext = getApplicationContext();
//        applicationHandler = new Handler(applicationContext.getMainLooper());
//
//        /**
//         * Data base initialize
//         */
//        initilizeDB();
//        /*
//         * Display Density Calculation so that Application not problem with All
//		 * resolution.
//		 */
//        checkDisplaySize();
//        density = applicationContext.getResources().getDisplayMetrics().density;
//
//		/*
//         * Imageloader initialize
//		 */
         initImageLoader(getApplicationContext());





    }

    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    /**music*/


}

package com.kaiyuanxueyuan.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 信息工具类
 * Created by 张国帅 on 2016/6/14.
 */
public class ShowInfo {

    public static void Toast(Context context, String info){
        Toast.makeText(context,info, Toast.LENGTH_SHORT).show();
    }
}

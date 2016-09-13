package com.dmplayer.childfragment;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.dmplayer.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/7/15.
 */
public class MusicAction {

    public interface OnSuccessListener {
        void back(boolean exit);
    }

    // 弹窗控件
    private PopupWindow popup;
    // 弹窗
    private View popupView;
    // 指明活动
    private Activity activity;
    // 指明父控件
    private View partent;

    private RecyclerView musicAction;

    private ImageView back;

    private OnSuccessListener onSuccessListener;

    public MusicAction(Activity activity) {

        this.activity = activity;
    }

    public MusicAction(Activity activity, OnSuccessListener onSuccessListener) {

        this.activity = activity;
        this.onSuccessListener = onSuccessListener;
    }

    public void show(View view) {
        this.partent = view;
        init(partent);
    }

    // 弹窗 在空间下方
    private void init(final View parent) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(activity);
        popupView = mLayoutInflater.inflate(R.layout.music_action, null);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        musicAction = (RecyclerView) popupView.findViewById(R.id.music_action_list);
        initRecyclerview(musicAction);
        ActivityNoKeyCenter(activity,parent, popup);
    }

    public void initRecyclerview(RecyclerView recyclerView){
        // 创建一个线性布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // 设置布局管理器
        recyclerView.setLayoutManager(new GridLayoutManager(activity,4));

        MusicActionAdapter musicActionAdapter = new MusicActionAdapter(activity,getMusicAction());

        recyclerView.setAdapter(musicActionAdapter);
    }

    public ArrayList<MusicActionInfo> getMusicAction(){

        ArrayList<MusicActionInfo> musicActionInfos = new ArrayList<MusicActionInfo>();
        for (int i = 0; i < 20;i++){
            MusicActionInfo musicActionInfo = new MusicActionInfo();
            musicActionInfo.setName("功能" + i);
            musicActionInfo.setId(R.mipmap.music_action);
            musicActionInfos.add(i,musicActionInfo);
        }
        return musicActionInfos;
    }

    public static void ActivityNoKeyCenter(final Activity activity, View parent, PopupWindow popupWindow) {
        ColorDrawable cd = new ColorDrawable(0x000000);
        popupWindow.setBackgroundDrawable(cd);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.9f;
        activity.getWindow().setAttributes(lp);
        //popupWindow.setAnimationStyle(R.style.popup_center);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAsDropDown(parent);
        popupWindow.update();
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);

            }
        });
    }
}

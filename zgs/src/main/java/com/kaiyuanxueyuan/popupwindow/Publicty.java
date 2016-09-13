package com.kaiyuanxueyuan.popupwindow;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.PublictyAdapter;
import com.kaiyuanxueyuan.entity.PublictyInfo;

import java.util.ArrayList;

/**
 * 关注的课程
 * Created by 张国帅 on 2016/6/22.
 */
public class Publicty {

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

    private RecyclerView myPublicty;

    private ImageView back;

	private OnSuccessListener onSuccessListener;

    public Publicty(Activity activity) {

        this.activity = activity;
    }

	public Publicty(Activity activity, OnSuccessListener onSuccessListener) {

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
        popupView = mLayoutInflater.inflate(R.layout.publicity, null);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        back = (ImageView) popupView.findViewById(R.id.publicty_back);
		myPublicty = (RecyclerView) popupView.findViewById(R.id.publicty_list);
		initRecyclerview(myPublicty);

		back.setOnClickListener(new SettingOnClick());
        PopupwindowSet.ActivityNoKeyCenter(activity,parent, popup);
	}

	public void initRecyclerview(RecyclerView recyclerView){
		// 创建一个线性布局管理器
		LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
		// 默认是Vertical，可以不写
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		// 设置布局管理器
		recyclerView.setLayoutManager(layoutManager);

		PublictyAdapter publictyAdapter = new PublictyAdapter(activity,getPublicInfo());

		recyclerView.setAdapter(publictyAdapter);
	}

	public ArrayList<PublictyInfo> getPublicInfo(){
		ArrayList<PublictyInfo> publictyInfos = new ArrayList<PublictyInfo>();
		publictyInfos.clear();
		for (int i = 0;i < 20; i++){
			PublictyInfo publictyInfo = new PublictyInfo();
			Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(),R.mipmap.one);
			/*Bitmap new_bitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / 5, bitmap.getHeight() / 5);
			bitmap.recycle();*/
			publictyInfo.setPicture(bitmap);
			publictyInfo.setTitle("我是标题" + i);
			publictyInfo.setStudy(i + "");
			publictyInfo.setLesson("1-" + i);
			publictyInfo.setUpdate(i + "-1");
			publictyInfos.add(i,publictyInfo);
		}

		return publictyInfos;
	}

	public class SettingOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){

				case R.id.publicty_back:
					popup.dismiss();
					break;
				default :
					break;
			}
		}
	}
}

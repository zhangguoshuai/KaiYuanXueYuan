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
import com.kaiyuanxueyuan.adapter.MessageAdapter;
import com.kaiyuanxueyuan.entity.MessageInfo;
import com.kaiyuanxueyuan.utils.ImageTools;

import java.util.ArrayList;

/**
 * 我的消息
 * Created by 张国帅 on 2016/6/22.
 */
public class MyMessage {

	public interface OnSuccessListener {
		public void back(boolean exit);
	}

	// 弹窗控件
	private PopupWindow popup;
	// 弹窗
	private View popupView;
	// 指明活动
	private Activity activity;
	// 指明父控件
	private View partent;

    private RecyclerView myMessage;

    private ImageView back,friends;

	private OnSuccessListener onSuccessListener;

    public MyMessage(Activity activity) {

        this.activity = activity;
    }

	public MyMessage(Activity activity, OnSuccessListener onSuccessListener) {

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
        popupView = mLayoutInflater.inflate(R.layout.my_message, null);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        back = (ImageView) popupView.findViewById(R.id.message_back);
		myMessage = (RecyclerView) popupView.findViewById(R.id.message_list);
		friends = (ImageView) popupView.findViewById(R.id.message_friends);
		initRecyclerview(myMessage);

		back.setOnClickListener(new SettingOnClick());
		friends.setOnClickListener(new SettingOnClick());
        PopupwindowSet.ActivityNoKeyCenter(activity,parent, popup);
	}

	public void initRecyclerview(RecyclerView recyclerView){
		// 创建一个线性布局管理器
		LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
		// 默认是Vertical，可以不写
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		// 设置布局管理器
		recyclerView.setLayoutManager(layoutManager);

		MessageAdapter messageAdapter = new MessageAdapter(activity,getMessageInfo());

		recyclerView.setAdapter(messageAdapter);
	}

	public ArrayList<MessageInfo> getMessageInfo(){
		ArrayList<MessageInfo> messageInfos = new ArrayList<MessageInfo>();
		messageInfos.clear();
		for (int i = 0;i < 20; i++){
			MessageInfo messageInfo = new MessageInfo();
			Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(),R.mipmap.one);
			Bitmap new_bitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / 5, bitmap.getHeight() / 5);
			bitmap.recycle();
			messageInfo.setIcon(new_bitmap);
			messageInfo.setUser("我是用户" + i);
			messageInfo.setTime(i + "-" + i);
			messageInfo.setContent("Hi,亲爱的Forever Love，欢迎你来到华清远见，从今天起我将通过视频、代码、" +
					"联系等一系列方式，帮助你完成课程学习." + i);
			messageInfos.add(i,messageInfo);
		}

		return messageInfos;
	}

	public class SettingOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){

				case R.id.message_back:
					popup.dismiss();
					break;
				case R.id.message_friends:
					Contacts contacts = new Contacts(activity);
					contacts.show(partent);
					break;
				default :
					break;
			}
		}
	}
}

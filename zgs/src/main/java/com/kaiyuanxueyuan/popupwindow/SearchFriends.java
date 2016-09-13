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
import com.kaiyuanxueyuan.adapter.SearchFriendsAdapter;
import com.kaiyuanxueyuan.entity.SearchfriendsInfo;
import com.kaiyuanxueyuan.utils.ImageTools;

import java.util.ArrayList;

/**
 * 查找用户
 * Created by 张国帅 on 2016/6/23.
 */
public class SearchFriends {

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

    private RecyclerView recyclerView;

    private ImageView back;

	private OnSuccessListener onSuccessListener;

    public SearchFriends(Activity activity) {

        this.activity = activity;
    }

	public SearchFriends(Activity activity, OnSuccessListener onSuccessListener) {

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
        popupView = mLayoutInflater.inflate(R.layout.search_friend, null);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        back = (ImageView) popupView.findViewById(R.id.search_friends_back);
		recyclerView = (RecyclerView) popupView.findViewById(R.id.search_friends_list);
		initRecyclerview(recyclerView);
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

		SearchFriendsAdapter searchFriendsAdapter = new SearchFriendsAdapter(activity,getSearchFriendsInfo());

		recyclerView.setAdapter(searchFriendsAdapter);
	}

	public ArrayList<SearchfriendsInfo> getSearchFriendsInfo(){
		ArrayList<SearchfriendsInfo> searchfriendsInfos = new ArrayList<SearchfriendsInfo>();
		searchfriendsInfos.clear();
		for (int i = 0;i < 20; i++){
			SearchfriendsInfo searchfriendsInfo = new SearchfriendsInfo();
			Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(),R.mipmap.one);
			Bitmap new_bitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / 5, bitmap.getHeight() / 5);
			bitmap.recycle();
			searchfriendsInfo.setIcon(new_bitmap);
			searchfriendsInfo.setUser("我是用户" + i);
			searchfriendsInfos.add(i,searchfriendsInfo);
		}

		return searchfriendsInfos;
	}

	public class SettingOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){

				case R.id.search_friends_back:
					popup.dismiss();
					break;

				case R.id.search_friends_ok:

					break;
				default :
					break;
			}
		}
	}
}

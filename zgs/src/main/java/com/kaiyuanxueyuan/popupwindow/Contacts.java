package com.kaiyuanxueyuan.popupwindow;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.ui.contacts.IndexableRecyclerViewAdapter;
import com.kaiyuanxueyuan.ui.contacts.ItemModel;
import com.kaiyuanxueyuan.view.IndexableRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 通信录
 * Created by 张国帅 on 2016/6/23.
 */
public class Contacts {

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

	private IndexableRecyclerView mIndexableRecyclerView;

    private ImageView back,addFriends;

	private OnSuccessListener onSuccessListener;

    public Contacts(Activity activity) {

        this.activity = activity;
    }

	public Contacts(Activity activity, OnSuccessListener onSuccessListener) {

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
        popupView = mLayoutInflater.inflate(R.layout.contacts, null);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        back = (ImageView) popupView.findViewById(R.id.contacts_back);
		addFriends = (ImageView) popupView.findViewById(R.id.contacts_addFriends);
		mIndexableRecyclerView = (IndexableRecyclerView)popupView.findViewById(R.id.contacts_indexable);

		initContactsView();

		back.setOnClickListener(new SettingOnClick());
		addFriends.setOnClickListener(new SettingOnClick());
        PopupwindowSet.ActivityNoKeyCenter(activity,parent, popup);
	}

	public void initContactsView(){

		IndexableRecyclerViewAdapter indexableRecyclerViewAdapter = new IndexableRecyclerViewAdapter(activity, getData());
		mIndexableRecyclerView.setAdapter(indexableRecyclerViewAdapter);

	}

	private List<ItemModel> getData() {

		List<ItemModel> models = new ArrayList<ItemModel>();
		models.clear();
		models.add(new ItemModel("国睿科技", R.mipmap.one));
		models.add(new ItemModel("海格雷", R.mipmap.two));
		models.add(new ItemModel("恒华", R.mipmap.three));
		models.add(new ItemModel("海的", R.mipmap.four));
		models.add(new ItemModel("恒瑞", R.mipmap.five));
		models.add(new ItemModel("佳美", R.mipmap.six));
		models.add(new ItemModel("皖中", R.mipmap.seven));
		models.add(new ItemModel("东昕", R.mipmap.eight));
		models.add(new ItemModel("真武", R.mipmap.nine));
		models.add(new ItemModel("日生", R.mipmap.one));
		models.add(new ItemModel("化陶", R.mipmap.three));
		models.add(new ItemModel("玉泉", R.mipmap.two));
		models.add(new ItemModel("老田", R.mipmap.four));
		models.add(new ItemModel("女孩", R.mipmap.five));
		models.add(new ItemModel("360", R.mipmap.six));
		models.add(new ItemModel("-1", R.mipmap.eight));
		return models;
	}

	public class SettingOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){

				case R.id.contacts_back:
					popup.dismiss();
					break;
				case R.id.contacts_addFriends:
					SearchFriends searchFriends = new SearchFriends(activity);
					searchFriends.show(partent);
					break;
				default :
					break;
			}
		}
	}


}

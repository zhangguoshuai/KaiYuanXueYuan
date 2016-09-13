package com.kaiyuanxueyuan.popupwindow;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.kaiyuanxueyuan.R;

/**
 * 设置
 * Created by 张国帅 on 2016/6/21.
 */
public class Setting {

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

    private RecyclerView history,hotSearch;

    private ImageView back;

	private LinearLayout personData;

	private OnSuccessListener onSuccessListener;

    public Setting(Activity activity) {

        this.activity = activity;
    }

	public Setting(Activity activity, OnSuccessListener onSuccessListener) {

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
        popupView = mLayoutInflater.inflate(R.layout.seting, null);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);


        back = (ImageView) popupView.findViewById(R.id.set_back);
		personData = (LinearLayout) popupView.findViewById(R.id.personData);

		back.setOnClickListener(new SettingOnClick());
		personData.setOnClickListener(new SettingOnClick());
        PopupwindowSet.ActivityNoKeyCenter(activity,parent, popup);
	}

	public class SettingOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){

				case R.id.set_back:
					popup.dismiss();
					break;

				case R.id.personData:
                    PersonData personData = new PersonData(activity);
                    personData.show(partent);
					break;
				default :
					break;
			}
		}
	}
}

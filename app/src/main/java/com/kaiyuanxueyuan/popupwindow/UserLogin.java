package com.kaiyuanxueyuan.popupwindow;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;

/**
 * 设置
 * Created by 张国帅 on 2016/6/21.
 */
public class UserLogin {

	public interface OnSuccessListener {
		public void back(boolean exit);
	}

	// 弹窗控件
	private PopupWindow popup;
	// 弹窗
	private View popupView;
	// 指明活动
	private FragmentActivity activity;
	// 指明父控件
	private View partent;

	private TextView register,forget;

    private ImageView back;

	private UserRegisterPhone userRegister;

	private OnSuccessListener onSuccessListener;

    public UserLogin(FragmentActivity activity) {

        this.activity = activity;
    }

	public UserLogin(FragmentActivity activity, OnSuccessListener onSuccessListener) {

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
        popupView = mLayoutInflater.inflate(R.layout.user_login, null);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        back = (ImageView) popupView.findViewById(R.id.user_login_close);
		register = (TextView) popupView.findViewById(R.id.user_login_register);
		forget = (TextView) popupView.findViewById(R.id.user_login_forget);

		back.setOnClickListener(new SettingOnClick());
		register.setOnClickListener(new SettingOnClick());
		forget.setOnClickListener(new SettingOnClick());

        PopupwindowSet.ActivityNoKeyCenter(activity,parent,popup);
	}

	public class SettingOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){

				case R.id.user_login_close:
                    popup.dismiss();
					break;

				case R.id.user_login_register:

					if (userRegister == null){
						userRegister = new UserRegisterPhone(activity);
					}
					userRegister.show(partent);
					break;
				default :
					break;
			}
		}
	}
}

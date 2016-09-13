package com.kaiyuanxueyuan.popupwindow;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.kaiyuanxueyuan.R;

/**
 * 弹窗位置设置
 * Created by 张国帅 on 2016/6/14.
 */
public class PopupwindowSet {
	
	public static void PopupwindowAsDown(View parent, PopupWindow popupWindow, final Activity activity, final int menu){
		ColorDrawable cd = new ColorDrawable(0x000000);
		popupWindow.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = 0.9f;
		activity.getWindow().setAttributes(lp);
		popupWindow.setAnimationStyle(R.style.popup);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.showAsDropDown(parent);
		popupWindow.update();
		popupWindow.setOnDismissListener(new OnDismissListener() {
			public void onDismiss() {
				if (menu == 2) {
					
				}else{
					WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
					lp.alpha = 1f;
					activity.getWindow().setAttributes(lp);
				}
			}
		});
	}

	public static void ActivityKeyNoCenter(final Activity activity, View parent, PopupWindow popupWindow){
		ColorDrawable cd = new ColorDrawable(0x000000);
		popupWindow.setBackgroundDrawable(null);
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = 0.9f;
		activity.getWindow().setAttributes(lp);
		popupWindow.setAnimationStyle(R.style.popup_center);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.showAsDropDown(parent);
		popupWindow.update();
		popupWindow.setOnDismissListener(new OnDismissListener() {
			public void onDismiss() {

				WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
				lp.alpha = 1f;
				activity.getWindow().setAttributes(lp);

			}
		});
	}

	public static void ActivityNoKeyCenter(final Activity activity, View parent, PopupWindow popupWindow) {
		ColorDrawable cd = new ColorDrawable(0x000000);
		popupWindow.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = 0.9f;
		activity.getWindow().setAttributes(lp);
		popupWindow.setAnimationStyle(R.style.popup_center);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
		// popupWindow.showAsDropDown(parent);
		popupWindow.update();
		popupWindow.setOnDismissListener(new OnDismissListener() {
			public void onDismiss() {
				WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
				lp.alpha = 1f;
				activity.getWindow().setAttributes(lp);

			}
		});
	}

	public static void PopupwindowKeyCenter(final FragmentActivity activity, View parent, PopupWindow popupWindow) {
		ColorDrawable cd = new ColorDrawable(0x000000);
		popupWindow.setBackgroundDrawable(null);
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = 0.9f;
		activity.getWindow().setAttributes(lp);
		popupWindow.setAnimationStyle(R.style.popup_center);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
		// popupWindow.showAsDropDown(parent);
		popupWindow.update();
		popupWindow.setOnDismissListener(new OnDismissListener() {
			public void onDismiss() {
				WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
				lp.alpha = 1f;
				activity.getWindow().setAttributes(lp);

			}
		});
	}

	public static void PopupwindowNoKeyCenter(final FragmentActivity activity, View parent, PopupWindow popupWindow) {
		ColorDrawable cd = new ColorDrawable(0x000000);
		popupWindow.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
		lp.alpha = 0.9f;
		activity.getWindow().setAttributes(lp);
		popupWindow.setAnimationStyle(R.style.popup_center);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setFocusable(true);
		popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
		// popupWindow.showAsDropDown(parent);
		popupWindow.update();
		popupWindow.setOnDismissListener(new OnDismissListener() {
			public void onDismiss() {
				WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
				lp.alpha = 1f;
				activity.getWindow().setAttributes(lp);

			}
		});
	}
}

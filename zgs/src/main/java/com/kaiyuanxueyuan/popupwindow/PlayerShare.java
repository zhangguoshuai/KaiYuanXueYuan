package com.kaiyuanxueyuan.popupwindow;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
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
public class PlayerShare {

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

	private LinearLayout qq,emails,weiXin,qqZone,friends,xinLang;

	private OnSuccessListener onSuccessListener;

    public PlayerShare(Activity activity) {

        this.activity = activity;
    }

	public PlayerShare(Activity activity, OnSuccessListener onSuccessListener) {

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
        popupView = mLayoutInflater.inflate(R.layout.player_share, null);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        popupView.setFocusable(true);
        popupView.setFocusableInTouchMode(true);

        back = (ImageView) popupView.findViewById(R.id.player_share_close);
		qq = (LinearLayout) popupView.findViewById(R.id.player_share_qq);
		emails = (LinearLayout) popupView.findViewById(R.id.player_share_email);
		weiXin = (LinearLayout) popupView.findViewById(R.id.player_share_weixin);
		qqZone = (LinearLayout) popupView.findViewById(R.id.player_share_qqzone);
		friends = (LinearLayout) popupView.findViewById(R.id.player_share_friends);
		xinLang = (LinearLayout) popupView.findViewById(R.id.player_share_xinlang);

        popupView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    System.out.println("share back");
                    popup.dismiss();
                    if (onSuccessListener != null){
                        onSuccessListener.back(true);
                    }
                }
                return false;

            }
        });

		back.setOnClickListener(new SettingOnClick());
		qq.setOnClickListener(new SettingOnClick());
		emails.setOnClickListener(new SettingOnClick());
		weiXin.setOnClickListener(new SettingOnClick());
		qqZone.setOnClickListener(new SettingOnClick());
		friends.setOnClickListener(new SettingOnClick());
		xinLang.setOnClickListener(new SettingOnClick());

        PopupwindowSet.ActivityKeyNoCenter(activity,parent,popup);
	}

	public class SettingOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){

				case R.id.player_share_close:
                    popup.dismiss();
                    onSuccessListener.back(true);
					break;

				case R.id.player_share_qq:
					/*QQShare qqShare = new QQShare(activity);
					boolean isCommit = qqShare.commitServer();

					System.out.println("qq 已连接：" + isCommit);

					if (isCommit){
						qqShare.onClickLogin();
					}*/

					break;
				default :
					break;
			}
		}
	}
}

package com.kaiyuanxueyuan.popupwindow;

import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.entity.VideoDownInfo;
import com.kaiyuanxueyuan.view.PinnedHeaderListView;

import java.util.ArrayList;

/**
 * 查看下载对象的信息
 * Created by 张国帅 on 2016/6/24.
 */
public class VideoDown {

	public interface OnSuccessListener {
		void back(boolean exit);
	}

	// 弹窗控件
	private PopupWindow popup;
	// 弹窗
	private View popupView;
	// 指明活动
	private FragmentActivity activity;
	// 指明父控件
	private View partent;

    private PinnedHeaderListView listView;

	//private PlayerVideoDownAdapter adapter;

    private ImageView back;

	private OnSuccessListener onSuccessListener;

    public VideoDown(FragmentActivity activity) {

        this.activity = activity;
    }

	public VideoDown(FragmentActivity activity, OnSuccessListener onSuccessListener) {

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
        popupView = mLayoutInflater.inflate(R.layout.player_down, null);
        popup = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupView.setFocusable(true);
        popupView.setFocusableInTouchMode(true);
        back = (ImageView) popupView.findViewById(R.id.play_down_back);
		listView = (PinnedHeaderListView) popupView.findViewById(R.id.play_down_listView);
        initView();
		back.setOnClickListener(new SettingOnClick());
        popupView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    popup.dismiss();
                    if (onSuccessListener != null){
                        onSuccessListener.back(true);
                    }
                }
                return false;

            }
        });

        PopupwindowSet.PopupwindowKeyCenter(activity,parent,popup);
	}

	public void initView() {
//		// * 创建新的HeaderView，即置顶的HeaderView
//		View HeaderView = activity.getLayoutInflater().inflate(R.layout.listview_item_header, listView, false);
//		listView.setPinnedHeader(HeaderView);
//		ArrayList<VideoDownInfo> data = createTestData();
//		adapter = new PlayerVideoDownAdapter(activity, data);
//
//        listView.requestFocus();
//
//		listView.setAdapter(adapter);
//
//		listView.setOnScrollListener(adapter);

//		listView.setOnItemClickListener(new PinnedHeaderListView.OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				// TODO Auto-generated method stub
//				Toast.makeText(activity, "点击的位置" + position, Toast.LENGTH_SHORT).show();
//				System.out.println("点击的位置" + position);
//			}
//
//		});

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(activity, "点击的位置" + position, Toast.LENGTH_SHORT).show();
				System.out.println("点击的位置" + position);
			}
		});
	}

	private ArrayList<VideoDownInfo> createTestData() {

		ArrayList<VideoDownInfo> data = new ArrayList<VideoDownInfo>();

        VideoDownInfo item = new VideoDownInfo("地方丁", "陕西01","10MB",false);
        VideoDownInfo item1 = new VideoDownInfo("地方丁", "陕西02","10MB",false);
        VideoDownInfo item2 = new VideoDownInfo("地方丁", "陕西03","10MB",false);
        VideoDownInfo item3 = new VideoDownInfo("地方丁", "陕西04","10MB",false);
        VideoDownInfo item4 = new VideoDownInfo("地方丁", "陕西05","10MB",false);

        VideoDownInfo item5 = new VideoDownInfo("书籍丙", "书法大全01","20MB",false);
        VideoDownInfo item6 = new VideoDownInfo("书籍丙", "书法大全02","20MB",false);
        VideoDownInfo item7 = new VideoDownInfo("书籍丙", "书法大全03","20MB",false);
        VideoDownInfo item8 = new VideoDownInfo("书籍丙", "书法大全04","20MB",false);
        VideoDownInfo item9 = new VideoDownInfo("书籍丙", "书法大全05","20MB",false);

        VideoDownInfo item10 = new VideoDownInfo("事件乙", "车祸01","07MB",false);
        VideoDownInfo item11 = new VideoDownInfo("事件乙", "车祸02","07MB",false);
        VideoDownInfo item12 = new VideoDownInfo("事件乙", "车祸03","07MB",false);
        VideoDownInfo item13 = new VideoDownInfo("事件乙", "车祸04","07MB",false);
        VideoDownInfo item14 = new VideoDownInfo("事件乙", "车祸05","07MB",false);

        VideoDownInfo item15 = new VideoDownInfo("路人甲", "照明01","17MB",false);
        VideoDownInfo item16 = new VideoDownInfo("路人甲", "照明02","17MB",false);
        VideoDownInfo item17 = new VideoDownInfo("路人甲", "照明03","17MB",false);
        VideoDownInfo item18 = new VideoDownInfo("路人甲", "照明04","17MB",false);
        VideoDownInfo item19 = new VideoDownInfo("路人甲", "照明05","17MB",false);

		data.add(item);data.add(item1);data.add(item2);data.add(item3);data.add(item4);
		data.add(item5);data.add(item6);data.add(item7);data.add(item8);data.add(item9);
		data.add(item10); data.add(item11);data.add(item12);data.add(item13);data.add(item14);
		data.add(item15);data.add(item16);data.add(item17);data.add(item18);data.add(item19);
		return data;
	}

	public class SettingOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){

				case R.id.play_down_back:
					popup.dismiss();
                    onSuccessListener.back(true);
					break;
				default :
					break;
			}
		}
	}
}

package com.kaiyuanxueyuan.fragment.universityFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.UniversityVideoAdapter;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.player.VideoActivity;
import com.kaiyuanxueyuan.utils.ShowInfo;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 关注的课程
 * Created by 张国帅 on 2016/6/22.
 */
public class StudyActivity extends AppCompatActivity{

	public interface OnSuccessListener {
		void back(boolean exit);
	}

    private PullLoadMoreRecyclerView recyclerView;

    private ImageView back,screen;

	private OnSuccessListener onSuccessListener;

	public StudyActivity(){

	}

	public StudyActivity(OnSuccessListener onSuccessListener) {

		this.onSuccessListener = onSuccessListener;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.study_video);

		back = (ImageView) findViewById(R.id.study_video_back);
		screen = (ImageView) findViewById(R.id.study_video_screen);
		recyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.study_video_list);
		// 创建数据集
		SimulatedData simulatedData = new SimulatedData();
		initRecyclerview(recyclerView,simulatedData);

		back.setOnClickListener(new SettingOnClick());
	}

	public void initRecyclerview(PullLoadMoreRecyclerView recyclerView,SimulatedData simulatedData){

		recyclerView.setLinearLayout();

		// 创建Adapter，并指定数据集
		UniversityVideoAdapter myListAdapter = new UniversityVideoAdapter(getApplicationContext(), simulatedData.getImageList(),
				simulatedData.getTitleList(), simulatedData.getPersonNumberList(),
				simulatedData.getStatusList());

		myListAdapter.setOnItemClickLitener(new UniversityVideoAdapter.OnItemClickLitener() {

			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
				startActivity(intent);
				ShowInfo.Toast(getApplicationContext(),"onItemLongClick：" + position);

			}

			@Override
			public void onItemLongClick(View view, int position) {
				ShowInfo.Toast(getApplicationContext(),"onItemLongClick：" + position);
			}
		});
		recyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
		// 设置Adapter
		recyclerView.setAdapter(myListAdapter);
	}


	class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

		@Override
		public void onRefresh() {
			ShowInfo.Toast(getApplicationContext(),"onRefresh");
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					recyclerView.setPullLoadMoreCompleted();
				}
			}, 1000);
		}

		@Override
		public void onLoadMore() {
			ShowInfo.Toast(getApplicationContext(),"onLoadMore");
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					recyclerView.setPullLoadMoreCompleted();
				}
			}, 1000);
		}
	}

	public class SettingOnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
				case R.id.study_video_back:
					finish();
					break;
				default :
					break;
			}
		}
	}
}

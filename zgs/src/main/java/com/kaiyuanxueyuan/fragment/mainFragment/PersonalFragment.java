package com.kaiyuanxueyuan.fragment.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.PersonAdapter;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.fragment.myInfoFragment.MyArticleActivity;
import com.kaiyuanxueyuan.popupwindow.MyMessage;
import com.kaiyuanxueyuan.popupwindow.Publicty;
import com.kaiyuanxueyuan.popupwindow.Setting;
import com.kaiyuanxueyuan.popupwindow.UserLogin;
import com.kaiyuanxueyuan.utils.ShowInfo;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * 个人信息碎片
 * Created by 张国帅 on 2016/6/17.
 *
 */
public class PersonalFragment extends MyFragment implements OnClickListener {

	public static final String BUNDLE_TITLE = "title";

	// 暂时没用用到 注释掉了
	private PullLoadMoreRecyclerView recyclerView;
	private TextView login;
    private LinearLayout publicty,myMessage,myArticle,myNotes,myMonkey;
	private ImageView set;
	private UserLogin userLogin;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_personal, null);
		initView(view);
		return view;
	}

	public static PersonalFragment newInstance(String title) {
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_TITLE, title);
		PersonalFragment fragment = new PersonalFragment();
		fragment.setArguments(bundle);
		return fragment;

	}
	public static PersonalFragment newInstance() {
		PersonalFragment fragment = new PersonalFragment();
		return fragment;
	}

	public void initView(View view) {
		recyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.person_list);
		login = (TextView) view.findViewById(R.id.myInfo_login);
		SimulatedData simulatedData = new SimulatedData();
		initRecycleView(recyclerView,simulatedData);
		login.setOnClickListener(this);
	}

	public void initRecycleView(final PullLoadMoreRecyclerView recyclerView, SimulatedData simulatedData){

		recyclerView.setLinearLayout();

		PersonAdapter adapter = new PersonAdapter(getActivity(),simulatedData.getPersonName(),simulatedData.getPersonIcon());

		adapter.setOnItemClickLitener(new PersonAdapter.OnItemClickLitener() {

			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(getActivity(), MyArticleActivity.class);
				switch (position){
					case 0:
						Publicty publicty = new Publicty(getActivity());
						publicty.show(recyclerView);
						break;
					case 1:
						break;
					case 2:
						MyMessage myMessage = new MyMessage(getActivity());
						myMessage.show(recyclerView);
						break;
					case 3:
						intent.putExtra("flage","0");
						startActivity(intent);
						break;
					case 4:
						intent.putExtra("flage","1");
						startActivity(intent);
						break;
					case 5:
						intent.putExtra("flage","2");
						startActivity(intent);
						break;
					case 6:
						Setting setting = new Setting(getActivity());
						setting.show(recyclerView);
						break;

				}
			}

			@Override
			public void onItemLongClick(View view, int position) {
				ShowInfo.Toast(getActivity(),"onItemLongClick：" + position);
			}
		});
		recyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
		// 设置Adapter
		recyclerView.setAdapter(adapter);
	}

	class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

		@Override
		public void onRefresh() {
			ShowInfo.Toast(getActivity(),"onRefresh");
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					recyclerView.setPullLoadMoreCompleted();
				}
			}, 1000);
		}

		@Override
		public void onLoadMore() {
			ShowInfo.Toast(getActivity(),"onLoadMore");
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					recyclerView.setPullLoadMoreCompleted();
				}
			}, 1000);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()){

			case R.id.myInfo_login:
				if (userLogin == null){
					userLogin = new UserLogin(getActivity());
				}
				userLogin.show(v);
				break;

			default:
				break;
		}
	}
}

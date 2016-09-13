package com.kaiyuanxueyuan.fragment.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.data.SimulatedData;
import com.kaiyuanxueyuan.fragment.myInfoFragment.MyArticleActivity;
import com.kaiyuanxueyuan.popupwindow.MyMessage;
import com.kaiyuanxueyuan.popupwindow.Publicty;
import com.kaiyuanxueyuan.popupwindow.Setting;
import com.kaiyuanxueyuan.popupwindow.UserLogin;

/**
 * 个人信息碎片
 * Created by 张国帅 on 2016/6/17.
 *
 */
public class MyInfoFragment extends MyFragment implements OnClickListener {

	public static final String BUNDLE_TITLE = "title";

	// 暂时没用用到 注释掉了
	private RecyclerView recyclerView;
	private TextView login;
    private LinearLayout publicty,myMessage,myArticle,myNotes,myMonkey;
	private ImageView set;
	private UserLogin userLogin;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.my_info, null);
		initView(view);
		return view;
	}

	public static MyInfoFragment newInstance(String title) {
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_TITLE, title);
		MyInfoFragment fragment = new MyInfoFragment();
		fragment.setArguments(bundle);
		return fragment;

	}
	public static MyInfoFragment newInstance() {
		MyInfoFragment fragment = new MyInfoFragment();
		return fragment;
	}

	public void initView(View view) {
		recyclerView = (RecyclerView) view.findViewById(R.id.myInfo_list);
		login = (TextView) view.findViewById(R.id.myInfo_login);
		set = (ImageView) view.findViewById(R.id.myInfo_set);
		publicty = (LinearLayout) view.findViewById(R.id.myInfo_publicty);
        myMessage = (LinearLayout) view.findViewById(R.id.myInfo_message);
		myArticle = (LinearLayout) view.findViewById(R.id.myInfo_myArticle);
        myNotes = (LinearLayout) view.findViewById(R.id.myInfo_myNotes);
        myMonkey = (LinearLayout) view.findViewById(R.id.myInfo_myMonkey);
		initRecyclerview(recyclerView);

		set.setOnClickListener(this);
		login.setOnClickListener(this);
		publicty.setOnClickListener(this);
        myMessage.setOnClickListener(this);
		myArticle.setOnClickListener(this);
        myNotes.setOnClickListener(this);
        myMonkey.setOnClickListener(this);
	}

	public void initRecyclerview(RecyclerView recyclerView){
		// 创建一个线性布局管理器
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		// 默认是Vertical，可以不写
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		// 设置布局管理器
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

		SimulatedData simulatedData = new SimulatedData();

//		MyInfoAdapter myInfoAdapter = new MyInfoAdapter(getActivity(),simulatedData.getMyinfoImageList(),simulatedData.getMyinfoTitleList());
//
//		recyclerView.setAdapter(myInfoAdapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()){
			case R.id.myInfo_set:
				Setting setting = new Setting(getActivity());
				setting.show(v);
				break;

			case R.id.myInfo_publicty:
				Publicty publicty = new Publicty(getActivity());
				publicty.show(v);
				break;

            case R.id.myInfo_message:
                MyMessage myMessage = new MyMessage(getActivity());
                myMessage.show(v);
                break;

			case R.id.myInfo_myArticle:
                Intent intent = new Intent(getActivity(), MyArticleActivity.class);
                intent.putExtra("flage","0");
                startActivity(intent);
				break;
            case R.id.myInfo_myNotes:
                Intent intent1 = new Intent(getActivity(), MyArticleActivity.class);
                intent1.putExtra("flage","1");
                startActivity(intent1);
                break;
            case R.id.myInfo_myMonkey:
                Intent intent2 = new Intent(getActivity(), MyArticleActivity.class);
                intent2.putExtra("flage","2");
                startActivity(intent2);
                break;

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

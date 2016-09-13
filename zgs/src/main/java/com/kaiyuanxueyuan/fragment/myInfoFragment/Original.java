package com.kaiyuanxueyuan.fragment.myInfoFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.ArticleAdapter;
import com.kaiyuanxueyuan.entity.ArticleInfo;
import com.kaiyuanxueyuan.fragment.mainFragment.MyFragment;
import com.kaiyuanxueyuan.utils.ImageTools;

import java.util.ArrayList;

/**
 *  原创的文章
 * Created by 张国帅 on 2016/6/23.
 *
 */
public class Original extends MyFragment implements OnClickListener {

	public static final String BUNDLE_TITLE = "title";
    private RecyclerView recyclerView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.my_article_ist, null);
		initView(view);
		return view;
	}

	public static Original newInstance(String title) {
		Bundle bundle = new Bundle();
		bundle.putString(BUNDLE_TITLE, title);
		Original fragment = new Original();
		fragment.setArguments(bundle);
		return fragment;

	}
	public static Original newInstance() {
		Original fragment = new Original();
		return fragment;
	}

	public void initView(View view) {
		recyclerView = (RecyclerView) view.findViewById(R.id.myArticle_list);
		initRecyclerview(recyclerView);
	}

	public void initRecyclerview(RecyclerView recyclerView){
		// 创建一个线性布局管理器
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		// 默认是Vertical，可以不写
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		// 设置布局管理器
		recyclerView.setLayoutManager(layoutManager);

		ArticleAdapter articleAdapter = new ArticleAdapter(getActivity(),getArticleInfo());

		recyclerView.setAdapter(articleAdapter);
	}

	public ArrayList<ArticleInfo> getArticleInfo(){
		ArrayList<ArticleInfo> articleInfos = new ArrayList<ArticleInfo>();
        articleInfos.clear();
		for (int i = 0;i < 20; i++){
			ArticleInfo articleInfo = new ArticleInfo();
			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.one);
            Bitmap new_bitmap = ImageTools.zoomBitmap(bitmap, bitmap.getWidth() / 5, bitmap.getHeight() / 5);
            bitmap.recycle();
            articleInfo.setPicture(new_bitmap);
			articleInfo.setTitle("我是标题" + i);
			articleInfo.setNumber(i*20);
			articleInfo.setType("java" + i);
			articleInfo.setContent("我是内容加上就肯定会阿克苏接电话哇塞接电话" +
					"阿萨德瓦斯的玩的哇速度哇速度挖坟的这些安神定志X承认个套餐吴阿斯顿无啊是" + i);
			articleInfos.add(i,articleInfo);
		}

		return articleInfos;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}

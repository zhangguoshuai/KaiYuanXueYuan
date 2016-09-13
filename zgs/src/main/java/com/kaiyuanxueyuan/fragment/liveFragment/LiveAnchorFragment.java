package com.kaiyuanxueyuan.fragment.liveFragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.LiveAnchorAdapter;
import com.kaiyuanxueyuan.entity.LiveAnchorInfo;
import com.kaiyuanxueyuan.fragment.mainFragment.MyFragment;
import com.kaiyuanxueyuan.utils.ShowInfo;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;

/**
 * 网络直播或主播
 * Created by 张国帅 on 2016/7/11.
 */
public class LiveAnchorFragment extends  MyFragment implements View.OnClickListener {

    private PullLoadMoreRecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.live_anchor, null);
        initView(view);
        return view;
    }

    public static LiveAnchorFragment newInstance(){
        LiveAnchorFragment fragment = new LiveAnchorFragment();
        return fragment;
    }

    private void initView(View view){

        recyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.live_anchor_list);
        initRecycleView(recyclerView);
    }

    public void initRecycleView(PullLoadMoreRecyclerView recyclerView){

        recyclerView.setGridLayout(2,2);
        ArrayList<LiveAnchorInfo> liveAnchorInfos = new ArrayList<LiveAnchorInfo>();
        liveAnchorInfos.clear();

        for(int i = 0; i < 20; i++){

            LiveAnchorInfo liveAnchorInfo = new LiveAnchorInfo();
            liveAnchorInfo.setName("我是主播" + i);
            liveAnchorInfo.setNumber(i * 1000);
            liveAnchorInfo.setPerson(getYy().get(i));
            liveAnchorInfos.add(i,liveAnchorInfo);

        }

        LiveAnchorAdapter adapter = new LiveAnchorAdapter(getActivity(),liveAnchorInfos);


        adapter.setOnItemClickLitener(new LiveAnchorAdapter.OnItemClickLitener()
        {

            @Override
            public void onItemClick(View view, int position)
            {
//                Intent intent = new Intent(getActivity(), VideoActivity.class);
//                startActivity(intent);
                ShowInfo.Toast(getActivity(),"点击的位置：" + position);

            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getActivity(), position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreListener());
        // 设置Adapter
        recyclerView.setAdapter(adapter);
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {

        @Override
        public void onRefresh() {
            Toast.makeText(getActivity(), "onRefresh", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setPullLoadMoreCompleted();
                }
            }, 1000);
        }

        @Override
        public void onLoadMore() {
            Toast.makeText(getActivity(), "onLoadMore", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setPullLoadMoreCompleted();
                }
            }, 1000);
        }
    }

    private static ArrayList<Integer> getYy(){
        ArrayList<Integer> yy = new ArrayList<Integer>();

//        Bitmap yyone = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yyone);
//        Bitmap yytwo = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yytwo);
//        Bitmap yythree = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yythree);
//        Bitmap yyfour = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yyfour);
//        Bitmap yyfive = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yyfive);
//        Bitmap yysix = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yysix);
//        Bitmap yyseven = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yyseven);
//        Bitmap yyeight = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yyeight);
//        Bitmap yynine = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yynine);
//        Bitmap yyten = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yyten);
//        Bitmap yy11 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy11);
//        Bitmap yy12 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy12);
//        Bitmap yy13 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy13);
//        Bitmap yy14 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy14);
//        Bitmap yy15 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy15);
//        Bitmap yy16 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy16);
//        Bitmap yy17 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy17);
//        Bitmap yy18 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy18);
//        Bitmap yy19 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy19);
//        Bitmap yy20 = BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.yy20);
//
//        yy.add(yyone);yy.add(yytwo);yy.add(yythree);yy.add(yyfour);yy.add(yyfive);yy.add(yysix);yy.add(yyseven);
//        yy.add(yyeight);yy.add(yynine);yy.add(yyten);yy.add(yy11);yy.add(yy12);yy.add(yy13);yy.add(yy14);
//        yy.add(yy15);yy.add(yy16);yy.add(yy17);yy.add(yy18);yy.add(yy19);yy.add(yy20);

        yy.add(R.mipmap.yyone);yy.add(R.mipmap.yytwo);yy.add(R.mipmap.yythree);yy.add(R.mipmap.yyfour);yy.add(R.mipmap.yyfive);
        yy.add(R.mipmap.yysix);yy.add(R.mipmap.yyseven);yy.add(R.mipmap.yyeight);yy.add(R.mipmap.yynine);yy.add(R.mipmap.yyten);
        yy.add(R.mipmap.yy11);yy.add(R.mipmap.yy12);yy.add(R.mipmap.yy13);yy.add(R.mipmap.yy14);yy.add(R.mipmap.yy15);
        yy.add(R.mipmap.yy16);yy.add(R.mipmap.yy17);yy.add(R.mipmap.yy18);yy.add(R.mipmap.yy19);yy.add(R.mipmap.yy20);

        return yy;
    }

    @Override
    public void onClick(View v) {

    }
}

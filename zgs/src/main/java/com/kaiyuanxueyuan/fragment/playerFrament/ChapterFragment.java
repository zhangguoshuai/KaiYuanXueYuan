package com.kaiyuanxueyuan.fragment.playerFrament;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.PlayerChapterAdapter;
import com.kaiyuanxueyuan.entity.PlayerChapterInfo;
import com.kaiyuanxueyuan.fragment.mainFragment.MyFragment;
import com.kaiyuanxueyuan.view.PinnedHeaderListView;

import java.util.ArrayList;


/**
 * 章节
 * Created by Administrator on 2016/7/1.
 */
public class ChapterFragment extends MyFragment implements View.OnClickListener{

    public static final String BUNDLE_TITLE = "title";
    private PlayerChapterAdapter adapter;
    private PinnedHeaderListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_chapter, null);
        initView(view);
        return view;
    }

    public static ChapterFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        ChapterFragment fragment = new ChapterFragment();
        fragment.setArguments(bundle);
        return fragment;

    }
    public static ChapterFragment newInstance() {
        ChapterFragment fragment = new ChapterFragment();
        return fragment;
    }

    public void initView(View view) {
        listView = (PinnedHeaderListView)view.findViewById(R.id.player_chapter_list);
        // * 创建新的HeaderView，即置顶的HeaderView
        View HeaderView = getActivity().getLayoutInflater().inflate(R.layout.listview_item_header, listView, false);
        listView.setPinnedHeader(HeaderView);
        ArrayList<PlayerChapterInfo> data = createTestData();
        adapter = new PlayerChapterAdapter(getActivity(), data);
        listView.setAdapter(adapter);

        listView.setOnScrollListener(adapter);

        listView.setOnItemClickListener(new PinnedHeaderListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getActivity(), "点击的位置" + position, Toast.LENGTH_SHORT).show();
            }

        });

    }

    private ArrayList<PlayerChapterInfo> createTestData() {

        ArrayList<PlayerChapterInfo> data = new ArrayList<PlayerChapterInfo>();

        PlayerChapterInfo item = new PlayerChapterInfo("地方丁", "陕西01","10:30");
        PlayerChapterInfo item1 = new PlayerChapterInfo("地方丁", "陕西02","10:30");
        PlayerChapterInfo item2 = new PlayerChapterInfo("地方丁", "陕西03","10:30");
        PlayerChapterInfo item3 = new PlayerChapterInfo("地方丁", "陕西04","10:30");
        PlayerChapterInfo item4 = new PlayerChapterInfo("地方丁", "陕西05","10:30");

        PlayerChapterInfo item5 = new PlayerChapterInfo("书籍丙", "书法大全01","20:30");
        PlayerChapterInfo item6 = new PlayerChapterInfo("书籍丙", "书法大全02","20:30");
        PlayerChapterInfo item7 = new PlayerChapterInfo("书籍丙", "书法大全03","20:30");
        PlayerChapterInfo item8 = new PlayerChapterInfo("书籍丙", "书法大全04","20:30");
        PlayerChapterInfo item9 = new PlayerChapterInfo("书籍丙", "书法大全05","20:30");

        PlayerChapterInfo item10 = new PlayerChapterInfo("事件乙", "车祸01","07:30");
        PlayerChapterInfo item11 = new PlayerChapterInfo("事件乙", "车祸02","07:30");
        PlayerChapterInfo item12 = new PlayerChapterInfo("事件乙", "车祸03","07:30");
        PlayerChapterInfo item13 = new PlayerChapterInfo("事件乙", "车祸04","07:30");
        PlayerChapterInfo item14 = new PlayerChapterInfo("事件乙", "车祸05","07:30");

        PlayerChapterInfo item15 = new PlayerChapterInfo("路人甲", "照明01","17:30");
        PlayerChapterInfo item16 = new PlayerChapterInfo("路人甲", "照明02","17:30");
        PlayerChapterInfo item17 = new PlayerChapterInfo("路人甲", "照明03","17:30");
        PlayerChapterInfo item18 = new PlayerChapterInfo("路人甲", "照明04","17:30");
        PlayerChapterInfo item19 = new PlayerChapterInfo("路人甲", "照明05","17:30");

        data.add(item);data.add(item1);data.add(item2);data.add(item3);data.add(item4);
        data.add(item5);data.add(item6);data.add(item7);data.add(item8);data.add(item9);
        data.add(item10); data.add(item11);data.add(item12);data.add(item13);data.add(item14);
        data.add(item15);data.add(item16);data.add(item17);data.add(item18);data.add(item19);
      return data;
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}

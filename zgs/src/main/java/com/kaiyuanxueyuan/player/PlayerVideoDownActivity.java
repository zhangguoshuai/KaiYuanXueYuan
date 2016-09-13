package com.kaiyuanxueyuan.player;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.adapter.PlayerChapterAdapter;
import com.kaiyuanxueyuan.entity.PlayerChapterInfo;
import com.kaiyuanxueyuan.view.PinnedHeaderListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/6.
 */
public class PlayerVideoDownActivity extends Activity {

    private PinnedHeaderListView listView;

    private PlayerChapterAdapter adapter;

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_down);
        initView();
    }

    public void initView() {
        back = (ImageView)findViewById(R.id.play_down_back);
        listView = (PinnedHeaderListView)findViewById(R.id.play_down_listView);
        // * 创建新的HeaderView，即置顶的HeaderView
        View HeaderView = getLayoutInflater().inflate(R.layout.listview_item_header, listView, false);
        listView.setPinnedHeader(HeaderView);
        ArrayList<PlayerChapterInfo> data = createTestData();
        adapter = new PlayerChapterAdapter(PlayerVideoDownActivity.this, data);

        listView.setAdapter(adapter);
        listView.setOnScrollListener(adapter);
        listView.setOnItemClickListener(new PinnedHeaderListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "点击的位置" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private ArrayList<VideoDownInfo> createTestData() {
//
//        ArrayList<VideoDownInfo> data = new ArrayList<>();
//
//        VideoDownInfo item = new VideoDownInfo("地方丁", "陕西01","10MB",false);
//        VideoDownInfo item1 = new VideoDownInfo("地方丁", "陕西02","10MB",false);
//        VideoDownInfo item2 = new VideoDownInfo("地方丁", "陕西03","10MB",false);
//        VideoDownInfo item3 = new VideoDownInfo("地方丁", "陕西04","10MB",false);
//        VideoDownInfo item4 = new VideoDownInfo("地方丁", "陕西05","10MB",false);
//
//        VideoDownInfo item5 = new VideoDownInfo("书籍丙", "书法大全01","20MB",false);
//        VideoDownInfo item6 = new VideoDownInfo("书籍丙", "书法大全02","20MB",false);
//        VideoDownInfo item7 = new VideoDownInfo("书籍丙", "书法大全03","20MB",false);
//        VideoDownInfo item8 = new VideoDownInfo("书籍丙", "书法大全04","20MB",false);
//        VideoDownInfo item9 = new VideoDownInfo("书籍丙", "书法大全05","20MB",false);
//
//        VideoDownInfo item10 = new VideoDownInfo("事件乙", "车祸01","07MB",false);
//        VideoDownInfo item11 = new VideoDownInfo("事件乙", "车祸02","07MB",false);
//        VideoDownInfo item12 = new VideoDownInfo("事件乙", "车祸03","07MB",false);
//        VideoDownInfo item13 = new VideoDownInfo("事件乙", "车祸04","07MB",false);
//        VideoDownInfo item14 = new VideoDownInfo("事件乙", "车祸05","07MB",false);
//
//        VideoDownInfo item15 = new VideoDownInfo("路人甲", "照明01","17MB",false);
//        VideoDownInfo item16 = new VideoDownInfo("路人甲", "照明02","17MB",false);
//        VideoDownInfo item17 = new VideoDownInfo("路人甲", "照明03","17MB",false);
//        VideoDownInfo item18 = new VideoDownInfo("路人甲", "照明04","17MB",false);
//        VideoDownInfo item19 = new VideoDownInfo("路人甲", "照明05","17MB",false);
//
//        data.add(item);data.add(item1);data.add(item2);data.add(item3);data.add(item4);
//        data.add(item5);data.add(item6);data.add(item7);data.add(item8);data.add(item9);
//        data.add(item10); data.add(item11);data.add(item12);data.add(item13);data.add(item14);
//        data.add(item15);data.add(item16);data.add(item17);data.add(item18);data.add(item19);
//        return data;
//    }

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

    public class SettingOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch(v.getId()){

                case R.id.play_down_back:
                    finish();
                    break;
                default :
                    break;
            }
        }
    }
}

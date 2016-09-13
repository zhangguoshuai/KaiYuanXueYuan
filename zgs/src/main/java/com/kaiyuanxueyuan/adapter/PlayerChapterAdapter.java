package com.kaiyuanxueyuan.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.entity.PlayerChapterInfo;
import com.kaiyuanxueyuan.view.PinnedHeaderListView;

import java.util.List;

public class PlayerChapterAdapter extends BaseAdapter
        implements OnScrollListener, PinnedHeaderListView.PinnedHeaderAdapter {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String TAG = PlayerChapterAdapter.class.getSimpleName();

    // ===========================================================
    // Fields
    // ===========================================================

    private Context context;
    private List<PlayerChapterInfo> list;
    private LayoutInflater mLayoutInflater;

    // ===========================================================
    // Constructors
    // ===========================================================

    public PlayerChapterAdapter(Context context, List<PlayerChapterInfo> list) {
        this.context = context;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }


    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 常见的优化ViewHolder
        ViewHolder viewHolder = null;
        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.player_chapter_listview_item, null);

            viewHolder = new ViewHolder();
            viewHolder.header = (TextView) convertView.findViewById(R.id.chapter_header);
            viewHolder.title = (TextView) convertView.findViewById(R.id.chapter_title);
            viewHolder.duration = (TextView) convertView.findViewById(R.id.chapter_duration);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 获取数据
        PlayerChapterInfo itemEntity = (PlayerChapterInfo) getItem(position);
        viewHolder.title.setText(itemEntity.getTitle());
        viewHolder.duration.setText(itemEntity.getDuration());

        if ( needTitle(position) ) {
            // 显示标题并设置内容
            viewHolder.header.setText(itemEntity.getHeader());
            viewHolder.header.setVisibility(View.VISIBLE);
        } else {
            // 内容项隐藏标题
            viewHolder.header.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        if (null != list) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (null != list && position < getCount()) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

        if ( view instanceof PinnedHeaderListView) {
            ((PinnedHeaderListView) view).controlPinnedHeader(firstVisibleItem);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }


    @Override
    public int getPinnedHeaderState(int position) {
        if (getCount() == 0 || position < 0) {
            return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_GONE;
        }

        if (isMove(position) == true) {
            return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_PUSHED_UP;
        }

        return PinnedHeaderListView.PinnedHeaderAdapter.PINNED_HEADER_VISIBLE;
    }


    @Override
    public void configurePinnedHeader(View headerView, int position, int alpaha) {
        // 设置标题的内容
        PlayerChapterInfo itemEntity = (PlayerChapterInfo) getItem(position);
        String headerValue = itemEntity.getHeader();

//        Log.e(TAG, "header = " + headerValue);

        if (!TextUtils.isEmpty(headerValue)) {
            TextView headerTextView = (TextView) headerView.findViewById(R.id.header);
            headerTextView.setText( headerValue );
        }

    }

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * 判断是否需要显示标题
     *
     * @param position
     * @return
     */
    private boolean needTitle(int position) {
        // 第一个肯定是分类
        if (position == 0) {
            return true;
        }

        // 异常处理
        if (position < 0) {
            return false;
        }

        // 当前  // 上一个
        PlayerChapterInfo currentEntity = (PlayerChapterInfo) getItem(position);
        PlayerChapterInfo previousEntity = (PlayerChapterInfo) getItem(position - 1);
        if (null == currentEntity || null == previousEntity) {
            return false;
        }

        String currentTitle = currentEntity.getHeader();
        String previousTitle = previousEntity.getHeader();
        if (null == previousTitle || null == currentTitle) {
            return false;
        }

        // 当前item分类名和上一个item分类名不同，则表示两item属于不同分类
        return !currentTitle.equals(previousTitle);

    }


    private boolean isMove(int position) {
        // 获取当前与下一项
        PlayerChapterInfo currentEntity = (PlayerChapterInfo) getItem(position);
        PlayerChapterInfo nextEntity = (PlayerChapterInfo) getItem(position + 1);
        if (null == currentEntity || null == nextEntity) {
            return false;
        }

        // 获取两项header内容
        String currentTitle = currentEntity.getHeader();
        String nextTitle = nextEntity.getHeader();
        if (null == currentTitle || null == nextTitle) {
            return false;
        }

        // 当前不等于下一项header，当前项需要移动了
        return !currentTitle.equals(nextTitle);

    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    private class ViewHolder {
        TextView header;
        TextView title;
        TextView duration;
        ImageView icon;
    }

}

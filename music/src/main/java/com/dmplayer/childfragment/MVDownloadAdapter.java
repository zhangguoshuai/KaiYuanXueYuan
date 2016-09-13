package com.dmplayer.childfragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dmplayer.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/19.
 */
public class MVDownloadAdapter extends RecyclerView.Adapter<MVDownloadAdapter.ViewHolder>{

    // 数据集
    private ArrayList<Integer> listIcon;
    private ArrayList<String> listTitle;
    public interface OnItemClickLitener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {

        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public MVDownloadAdapter(Context context, ArrayList<Integer>listIcon , ArrayList<String> listTitle) {

        super();
        this.listTitle = listTitle;
        this.listIcon = listIcon;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.mvdown_list_item, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        // 绑定数据到ViewHolder上
        viewHolder.name.setText(listTitle.get(i));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(viewHolder.itemView, pos);
                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = viewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(viewHolder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listIcon.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView singer;
        public TextView size;
        public TextView schedule;
        public View itemview;
        public ViewHolder(View view) {
            super(view);
            itemview = view;
            name = (TextView) view.findViewById(R.id.mvDown_name);
            singer = (TextView) view.findViewById(R.id.mvDown_singer);
            size = (TextView) view.findViewById(R.id.mvDown_size);
            schedule = (TextView) view.findViewById(R.id.mvDown_schedule);
        }
    }
}


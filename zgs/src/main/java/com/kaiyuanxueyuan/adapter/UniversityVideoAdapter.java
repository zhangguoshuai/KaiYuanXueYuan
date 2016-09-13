package com.kaiyuanxueyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;

import java.util.ArrayList;

/**
 * 主界面适配器
 * Created by baoyz on 2014/6/29.
 */
public class UniversityVideoAdapter extends RecyclerView.Adapter<UniversityVideoAdapter.ViewHolder>{

    // 数据集
    private ArrayList<String> titleList,personNumberList,statusList;
    private ArrayList<Integer> logoList;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public UniversityVideoAdapter(Context context, ArrayList<Integer> logoList, ArrayList<String> titleList, ArrayList<String> personNumberList, ArrayList<String> statusList ) {
        super();
        this.titleList = titleList;
        this.personNumberList = personNumberList;
        this.statusList = statusList;
        this.logoList = logoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.university_video_list_item, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        // 绑定数据到ViewHolder上
        //viewHolder.mTextView.setText(mDataset[i]);
        viewHolder.title.setText(titleList.get(i));
        viewHolder.status.setText("更新至：" + statusList.get(i));
        viewHolder.personNumber.setText(personNumberList.get(i));
        viewHolder.logo.setImageResource(logoList.get(i));

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
        return titleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView personNumber;
        public TextView status;
        public ImageView logo;
        public View itemview;
        public ViewHolder(View view) {
            super(view);
            itemview = view;
            title = (TextView) view.findViewById(R.id.title);
            personNumber = (TextView) view.findViewById(R.id.personNumber);
            status = (TextView) view.findViewById(R.id.status);
            logo = (ImageView) view.findViewById(R.id.logo);
        }
    }
}

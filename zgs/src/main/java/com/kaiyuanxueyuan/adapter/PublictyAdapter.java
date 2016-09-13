package com.kaiyuanxueyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.entity.PublictyInfo;
import java.util.ArrayList;

/**
 * 正常适配器
 * Created by baoyz on 2016/6/15.
 */
public class PublictyAdapter extends RecyclerView.Adapter<PublictyAdapter.ViewHolder>{

    // 数据集
    private ArrayList<PublictyInfo> publictyList;

    public PublictyAdapter(Context context, ArrayList<PublictyInfo> publictyList){
        this.publictyList = publictyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.publicity_list, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.title.setText(publictyList.get(i).getTitle());
        viewHolder.study.setText("已学" + publictyList.get(i).getStudy() + "%");
        viewHolder.lesson.setText(publictyList.get(i).getLesson());
        viewHolder.update.setText("更新至" + publictyList.get(i).getUpdate());
        viewHolder.picture.setImageBitmap(publictyList.get(i).getPicture());
    }

    @Override
    public int getItemCount() {

        return publictyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView study;
        public TextView update;
        public TextView lesson;
        public ImageView picture;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.public_title);
            study = (TextView) view.findViewById(R.id.public_study);
            update = (TextView) view.findViewById(R.id.public_update);
            lesson = (TextView) view.findViewById(R.id.public_lseeon);
            picture = (ImageView) view.findViewById(R.id.public_picture);


        }
    }
}

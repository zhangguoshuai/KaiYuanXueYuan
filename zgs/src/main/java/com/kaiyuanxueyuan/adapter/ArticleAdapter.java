package com.kaiyuanxueyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.entity.ArticleInfo;

import java.util.ArrayList;

/**
 * 正常适配器
 * Created by baoyz on 2016/6/15.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder>{

    // 数据集
    private ArrayList<ArticleInfo> articleList;

    public ArticleAdapter(Context context, ArrayList<ArticleInfo> articleList){
        this.articleList = articleList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.article_list_item, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.title.setText(articleList.get(i).getTitle());
        viewHolder.type.setText(articleList.get(i).getType());
        viewHolder.content.setText(articleList.get(i).getContent());
        viewHolder.number.setText(articleList.get(i).getNumber() + "");
        viewHolder.picture.setImageBitmap(articleList.get(i).getPicture());
    }

    @Override
    public int getItemCount() {

        return articleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView type;
        public TextView content;
        public TextView number;
        public ImageView picture;
        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.article_title);
            type = (TextView) view.findViewById(R.id.article_type);
            content = (TextView) view.findViewById(R.id.article_content);
            number = (TextView) view.findViewById(R.id.article_number);
            picture = (ImageView) view.findViewById(R.id.article_picture);


        }
    }
}

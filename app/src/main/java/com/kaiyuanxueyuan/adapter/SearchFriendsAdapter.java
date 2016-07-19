package com.kaiyuanxueyuan.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.entity.SearchfriendsInfo;

import java.util.ArrayList;

/**
 * 查找用户对象
 * Created by baoyz on 2016/6/23.
 */
public class SearchFriendsAdapter extends RecyclerView.Adapter<SearchFriendsAdapter.ViewHolder>{

    // 数据集
    private ArrayList<SearchfriendsInfo> searchFriendsList;
    private Activity activity;

    public SearchFriendsAdapter(Activity activity, ArrayList<SearchfriendsInfo> searchFriendsList){
        this.searchFriendsList = searchFriendsList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.search_friends_list, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.user.setText(searchFriendsList.get(i).getUser());
        viewHolder.icon.setImageBitmap(searchFriendsList.get(i).getIcon());
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {

        return searchFriendsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView user;
        public Button add;
        public ImageView icon;

        public ViewHolder(View view) {
            super(view);
            user = (TextView) view.findViewById(R.id.search_friends_user);
            add = (Button) view.findViewById(R.id.search_friends_add);
            icon = (ImageView) view.findViewById(R.id.search_friends_icon);
        }
    }
}

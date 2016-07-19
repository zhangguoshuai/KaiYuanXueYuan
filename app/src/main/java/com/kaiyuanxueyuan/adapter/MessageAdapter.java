package com.kaiyuanxueyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.entity.MessageInfo;
import java.util.ArrayList;

/**
 * 正常适配器
 * Created by baoyz on 2016/6/15.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    // 数据集
    private ArrayList<MessageInfo> messageList;

    public MessageAdapter(Context context, ArrayList<MessageInfo> messageList){
        this.messageList = messageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.message_list, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        viewHolder.user.setText(messageList.get(i).getUser());
        viewHolder.time.setText(messageList.get(i).getTime());
        viewHolder.content.setText(messageList.get(i).getContent());
        viewHolder.icon.setImageBitmap(messageList.get(i).getIcon());
    }

    @Override
    public int getItemCount() {

        return messageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView user;
        public TextView time;
        public TextView content;
        public ImageView icon;
        public ViewHolder(View view) {
            super(view);
            user = (TextView) view.findViewById(R.id.message_user);
            time = (TextView) view.findViewById(R.id.message_time);
            content = (TextView) view.findViewById(R.id.message_content);
            icon = (ImageView) view.findViewById(R.id.message_icon);


        }
    }
}

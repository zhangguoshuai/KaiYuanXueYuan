package com.kaiyuanxueyuan.adapter;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.entity.CommentInfo;
import com.kaiyuanxueyuan.view.RoundImageView;

import java.util.ArrayList;


/**
 * 下载适配器
 * Created by baoyz on 2016/6/17.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    // 数据集
    private ArrayList<CommentInfo> commentInfos;
    private ViewHolder item[];
    private CommentHandle commentHandle = new CommentHandle();

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public CommentListAdapter(Context context, ArrayList<CommentInfo> commentInfos) {
        super();
        this.commentInfos = commentInfos;
        item = new ViewHolder[commentInfos.size()];

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // 创建一个View，简单起见直接使用系统提供的布局，就是一个TextView
        View view = View.inflate(viewGroup.getContext(), R.layout.player_comment_list_item, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        item[i] = viewHolder;
        viewHolder.user.setText(commentInfos.get(i).getUser());
        viewHolder.number.setText(commentInfos.get(i).getLiked() + "");
        viewHolder.date.setText(commentInfos.get(i).getData());
        viewHolder.image.setImageBitmap(commentInfos.get(i).getBitmap());
        viewHolder.liked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("点我");
                commentHandle.setPosition(i);
                commentHandle.sendEmptyMessage(commentInfos.get(i).getLiked() + 1);
            }
        });
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
                    return true;
                }
            });
        }
    }

    //改变图片的亮度方法 0--原样 >0---调亮 <0---调暗
    private void changeLight(ImageView imageView, int brightness) {
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1, 0, 0,
                brightness,// 改变亮度
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
        imageView.setColorFilter(new ColorMatrixColorFilter(cMatrix));
    }

    public class CommentHandle extends android.os.Handler{

        int position;
        public void setPosition(int position){
            this.position = position;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            System.out.println("点的位置" + position + "改变的数量" + msg.what);
            commentInfos.get(position).setLiked(msg.what);
            item[position].number.setText( msg.what + "");
        }
    }


    @Override
    public int getItemCount() {
        return commentInfos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public RoundImageView image;
        public TextView user;
        public TextView date;
        public ImageView liked;
        public TextView number;
        public TextView content;
        public View itemview;

        public ViewHolder(View view) {
            super(view);
            itemview = view;
            user = (TextView) view.findViewById(R.id.comment_user);
            number = (TextView) view.findViewById(R.id.comment_number);
            date = (TextView) view.findViewById(R.id.comment_date);
            content = (TextView) view.findViewById(R.id.comment_content);
            image = (RoundImageView) view.findViewById(R.id.comment_image);
            liked = (ImageView) view.findViewById(R.id.comment_liked);
        }
    }
}

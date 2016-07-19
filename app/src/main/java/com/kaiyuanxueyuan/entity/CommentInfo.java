package com.kaiyuanxueyuan.entity;

import android.graphics.Bitmap;

/**
 * Created by 张国帅 on 2016/7/4.
 */
public class CommentInfo {
    // 用户图片
    private Bitmap bitmap;
    // 用户
    private String user;
    // 点赞
    private int liked;
    // 时间戳
    private String data;
    // 评论内容
    private String content;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

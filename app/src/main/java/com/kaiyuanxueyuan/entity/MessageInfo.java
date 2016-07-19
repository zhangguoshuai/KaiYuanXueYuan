package com.kaiyuanxueyuan.entity;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/6/22.
 */
public class MessageInfo {

    private Bitmap icon;

    private String user;

    private String time;

    private String content;

    @Override
    public String toString() {
        return "MessageInfo{" +
                "inon=" + icon +
                ", user='" + user + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap inon) {
        this.icon = inon;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

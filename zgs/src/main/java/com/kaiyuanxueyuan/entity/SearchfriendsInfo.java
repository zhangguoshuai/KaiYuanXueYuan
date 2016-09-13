package com.kaiyuanxueyuan.entity;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/6/23.
 */
public class SearchfriendsInfo {

    private Bitmap icon;

    private String user;

    @Override
    public String toString() {
        return "SearchfriendsInfo{" +
                "icon=" + icon +
                ", user='" + user + '\'' +
                '}';
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

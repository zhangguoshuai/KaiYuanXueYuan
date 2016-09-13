package com.kaiyuanxueyuan.entity;

import android.graphics.Bitmap;

/**
 * 文章信息
 * Created by Administrator on 2016/6/21.
 */
public class ArticleInfo {

    private String title;
    private String type;
    private String content;
    private int number;
    private Bitmap picture;

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", number=" + number +
                ", picture=" + picture +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}

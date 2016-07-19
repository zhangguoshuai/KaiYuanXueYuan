package com.kaiyuanxueyuan.entity;

import android.graphics.Bitmap;

/**
 * 关注的课程信息
 * Created by Administrator on 2016/6/22.
 */
public class PublictyInfo {

    private Bitmap picture;

    private String title;

    private String study;

    private String lesson;

    private String update;

    @Override
    public String toString() {
        return "PublictyInfo{" +
                "picture=" + picture +
                ", title='" + title + '\'' +
                ", study='" + study + '\'' +
                ", lesson='" + lesson + '\'' +
                ", update='" + update + '\'' +
                '}';
    }

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}

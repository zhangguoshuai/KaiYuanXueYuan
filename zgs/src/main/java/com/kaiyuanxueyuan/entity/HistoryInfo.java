package com.kaiyuanxueyuan.entity;

import java.util.ArrayList;

/**
 * 学习记录信息  此处信息在数据库中 现在只是模拟
 * Created by Administrator on 2016/6/15.
 */
public class HistoryInfo {

    // 时间
    private String date;
    // 课程
    private ArrayList<String> course;
    // 目录(第几课时)
    private ArrayList<String> catalog;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getCourse() {
        return course;
    }

    public void setCourse(ArrayList<String> course) {
        this.course = course;
    }

    public ArrayList<String> getCatalog() {
        return catalog;
    }

    public void setCatalog(ArrayList<String> catalog) {
        this.catalog = catalog;
    }

    @Override
    public String toString() {
        return "HistoryInfo{" +
                "date='" + date + '\'' +
                ", course=" + course +
                ", catalog=" + catalog +
                '}';
    }
}
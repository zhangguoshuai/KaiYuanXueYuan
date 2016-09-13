package com.kaiyuanxueyuan.entity;

/**
 * Created by Administrator on 2016/7/5.
 */
public class PlayerChapterInfo {

    private String header;
    private String title;
    private String duration;

    public PlayerChapterInfo(String Pheader , String pTitle, String pduration) {
        header = Pheader;
        title = pTitle;
        duration = pduration;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

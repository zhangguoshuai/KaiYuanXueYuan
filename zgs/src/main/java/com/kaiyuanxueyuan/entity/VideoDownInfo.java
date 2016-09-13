package com.kaiyuanxueyuan.entity;

/**
 * Created by 张国帅 on 2016/7/5.
 */
public class VideoDownInfo {

    private String title;
    private String header;
    private String size;
    private boolean isChoose;

    public VideoDownInfo(String header, String title, String size, boolean isChoose){

        this.header = header;
        this.title = title;
        this.size = size;
        this.isChoose = isChoose;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(boolean isChoose) {
        this.isChoose = isChoose;
    }
}

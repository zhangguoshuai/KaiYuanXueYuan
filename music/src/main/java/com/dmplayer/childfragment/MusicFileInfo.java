package com.dmplayer.childfragment;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/7/22.
 */
public class MusicFileInfo {

    private int id;

    private String fileName;

    private String filePath;

    private Bitmap thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }
}

package com.kaiyuanxueyuan.entity;

/**
 * Created by Administrator on 2016/6/24.
 */
public class DownloadHasInfo {

    // 文件的名字
    private String title;
    // 文件的大小
    private int size;
    // 已经下载的大小
    private int percentage;
    // 已经下载的大小
    private int hasSize;
    // 是否下载完成
    private boolean isFinish;
    // 是否是暂停状态
    private boolean isPause;
    // 是否是下载状态
    private boolean isStart;
    // 文件播放的路劲
    private boolean url;

    @Override
    public String toString() {
        return "DownloadHasInfo{" +
                "title='" + title + '\'' +
                ", size=" + size +
                ", percentage=" + percentage +
                ", hasSize=" + hasSize +
                ", isFinish=" + isFinish +
                ", isPause=" + isPause +
                ", isStart=" + isStart +
                ", url=" + url +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHasSize() {
        return hasSize;
    }

    public void setHasSize(int hasSize) {
        this.hasSize = hasSize;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isUrl() {
        return url;
    }

    public void setUrl(boolean url) {
        this.url = url;
    }
}

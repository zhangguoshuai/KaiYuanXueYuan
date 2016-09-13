package com.kaiyuanxueyuan.entity;

import android.graphics.Bitmap;

/**
 * 猿问信息
 * Created by Administrator on 2016/6/21.
 */
public class MonkeyInfo {

    private String title;
    private String type;
    private int answerNumber;
    private Bitmap answerPerson;
    private int agreeMost;
    private String content;
    private boolean isAnswer;

    @Override
    public String toString() {
        return "MonkeyInfo{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", answerNumber=" + answerNumber +
                ", answerPerson=" + answerPerson +
                ", agreeMost=" + agreeMost +
                ", content='" + content + '\'' +
                ", isAnswer=" + isAnswer +
                '}';
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
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

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public Bitmap getAnswerPerson() {
        return answerPerson;
    }

    public void setAnswerPerson(Bitmap answerPerson) {
        this.answerPerson = answerPerson;
    }

    public int getAgreeMost() {
        return agreeMost;
    }

    public void setAgreeMost(int agreeMost) {
        this.agreeMost = agreeMost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

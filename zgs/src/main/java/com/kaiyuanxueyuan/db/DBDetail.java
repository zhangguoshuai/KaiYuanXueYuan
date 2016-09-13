/*
 * This is the source code of DMPLayer for Android v. 1.0.0.
 * You should have received a copy of the license in this archive (see LICENSE).
 * Copyright @Dibakar_Mistry, 2015.
 */
package com.kaiyuanxueyuan.db;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;

public class DBDetail {

	public int id;
	public String news;
	public String studty;
	public String video;
	public String isDownLoad;
	public String music;

	public String time = "";
	public DBDetail(int _id, String news, String studty, String video, String isDownLoad) {
		this.id = _id;
		this.news = news;
		this.studty = studty;
		this.video = video;
		this.isDownLoad = isDownLoad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNews() {
		return news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public String getStudty() {
		return studty;
	}

	public void setStudty(String studty) {
		this.studty = studty;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getIsDownLoad() {
		return isDownLoad;
	}

	public void setIsDownLoad(String isDownLoad) {
		this.isDownLoad = isDownLoad;
	}

	public String getMusic() {
		return music;
	}

	public void setMusic(String music) {
		this.music = music;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}

package com.dmplayer.childfragment;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;

import com.dmplayer.models.SongDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/21.
 */
public class MusicInfo {

    private ArrayList<Integer> singID = new ArrayList<>();

    private ArrayList<Integer> singAlbumID = new ArrayList<>();

    private ArrayList<String> singName = new ArrayList<>();

    private ArrayList<String> singer = new ArrayList<>();

    private ArrayList<String> singerAlbum = new ArrayList<>();

    private ArrayList<String> singerFevorite = new ArrayList<>();

    private ArrayList<Bitmap> singerArtist = new ArrayList<>();

    private HashMap<String,ArrayList<String>> singerList = new HashMap<>();

    private ArrayList<String> musicName = new ArrayList<>();

    private ArrayList<Integer> musicNumber = new ArrayList<>();

    private Cursor cursor;

    public MusicInfo(Context context){

        getMusicInfo(context);

    }

    public void getMusicInfo(Context context) {

        ContentResolver mResolver = context.getContentResolver();

        cursor = mResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

        if(cursor != null && cursor.moveToFirst()) {

//            System.out.println("数量" + cursor.getCount());

            if (cursor.getCount() > 0) {

                for (int i = 0; i <cursor.getCount(); i++ ){

                    int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    int albumId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                    String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    String display_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                    String year = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.YEAR));
                    int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                    String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)); // 文件路径
                    singID.add(id);
                    singName.add(i,title);
                    singer.add(i,artist);
                    singerAlbum.add(i,album);
                    singAlbumID.add(i,albumId);
//                    System.out.println("ID:" + id + "  url:" + url + "  title:" + title + "  album:" + album
//                            + "  artist:" + artist + "  displayName:" + display_name + "  year:" + year
//                            + "  时长:" + duration + " 大小：" + size );
                    cursor.moveToNext();
                }
            }
        }
    }

    public ArrayList<String> getFevoriteInfo(ArrayList<SongDetail> songsList){

        if(cursor != null && cursor.moveToFirst()) {

            if (cursor.getCount() > 0) {

                for (int k = 0; k < songsList.size(); k++ ){

                    for (int i = 0; i < singID.size(); i++ ){

                        if (songsList.get(k).getId() == singID.get(i)){
                            cursor.moveToPosition(i);
                            String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                            singerFevorite.add(album);
                        }
                    }
                }
            }
        }
        return singerFevorite;
    }

    public ArrayList queryAllImage(final Context context) {
        if (context == null) { //判断传入的参数的有效性
            return null;
        }
        ArrayList images = new ArrayList();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            //查询数据库，参数分别为（路径，要查询的列名，条件语句，条件参数，排序）
            cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null ,null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    MusicFileInfo image = new MusicFileInfo();
                    image.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media._ID))); //获取唯一id
                    image.setFilePath(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))); //文件路径
                    image.setFileName(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))); //文件名
                    //...   还有很多属性可以设置
                    //可以通过下一行查看属性名，然后在Images.Media.里寻找对应常量名

                    //获取缩略图（如果数据量大的话，会很耗时——需要考虑如何开辟子线程加载）
                /*
                 * 可以访问android.provider.MediaStore.Images.Thumbnails查询图片缩略图
                 * Thumbnails下的getThumbnail方法可以获得图片缩略图，其中第三个参数类型还可以选择MINI_KIND
                 */
                    Bitmap thumbnail = MediaStore.Images.Thumbnails.getThumbnail(resolver, image.getId(), MediaStore.Images.Thumbnails.MICRO_KIND, null);
                    image.setThumbnail(thumbnail);

                    images.add(image);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return images;
    }

    public ArrayList queryAllVideo(final Context context) {
        if (context == null) { //判断传入的参数的有效性
            return null;
        }
        ArrayList videos = new ArrayList();
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            //查询数据库，参数分别为（路径，要查询的列名，条件语句，条件参数，排序）
            cursor = resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null ,null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    MusicFileInfo video = new MusicFileInfo();
                    video.setId(cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media._ID))); //获取唯一id
                    video.setFilePath(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA))); //文件路径
                    video.setFileName(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME))); //文件名
                    //...   还有很多属性可以设置
                    //可以通过下一行查看属性名，然后在Video.Media.里寻找对应常量名
                    Log.i("music info", "queryAllImage --- all column name --- " + cursor.getColumnName(cursor.getPosition()));

                    //获取缩略图（如果数据量大的话，会很耗时——需要考虑如何开辟子线程加载）
                /*
                 * 可以访问android.provider.MediaStore.Video.Thumbnails查询图片缩略图
                 * Thumbnails下的getThumbnail方法可以获得图片缩略图，其中第三个参数类型还可以选择MINI_KIND
                 */
                    Bitmap thumbnail = MediaStore.Video.Thumbnails.getThumbnail(resolver, video.getId(), MediaStore.Video.Thumbnails.MICRO_KIND, null);
                    video.setThumbnail(thumbnail);

                    videos.add(video);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return videos;
    }


    public ArrayList<String> getSingName(){
        return singName;
    }

    public ArrayList<String> getSinger(){
        return singer;
    }

    public ArrayList<String> getSingerAlbum(){
        return singerAlbum;
    }

    public HashMap<String,ArrayList<String>> getSingerList(){

        singerList.clear();

        for (int j = 0; j < singName.size(); j++){

            if (singerList.containsKey(singer.get(j))){
                ArrayList<String> numberList = singerList.get(singer.get(j));
                numberList.add(singName.get(j));
            }else{
                ArrayList<String> name = new ArrayList<>();
                name.add(singName.get(j));
                singerList.put(singer.get(j),name);
            }
        }

        Set set = singerList.keySet();

        for(Iterator iterator = set.iterator(); iterator.hasNext();) {
            Object key = iterator.next();
            musicName.add(key.toString());
            Object val = singerList.get(key);
            ArrayList arrayList = singerList.get(key);
            musicNumber.add(arrayList.size());
//                System.out.println(key + "===================================");
//                for (Object o : arrayList) {
//                    System.out.println(o);
//                }
        }

        return singerList;
    }

    public ArrayList<String> getMusicName(){
        return musicName;
    }

    public ArrayList<Integer> getMusicNumber(){return  musicNumber;}

    public ArrayList<Bitmap> getSingerArtist(){
        return singerArtist;
    }
}

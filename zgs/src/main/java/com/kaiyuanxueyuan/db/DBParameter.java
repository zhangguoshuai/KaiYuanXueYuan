package com.kaiyuanxueyuan.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DBParameter {

    public static final String TABLENAME = "MostPlay";

    public static final String ID = "_id";
    public static final String NEWS = "news";
    public static final String VIDEO = "video";
    public static final String STUDY = "study";
    public static final String MUSIC = "music";
    public static final String ISDOWNLOAD = "isDownLoad";
    public static final String PLAYCOUNT = "playcount";

    private static DatabaseHelper DB_HELPER = null;
    private static DBParameter mInstance;
    private SQLiteDatabase sampleDB;
    public Context context;

    public static synchronized DBParameter getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBParameter(context);
        }
        return mInstance;
    }

    public DBParameter(Context context_) {
        this.context = context_;
        if (DB_HELPER == null) {
            initilizeDB(context_);
        }
    }

    private void initilizeDB(Context context_) {
        if (DB_HELPER == null) {
            DB_HELPER = new DatabaseHelper(context_);
        }
        try {
            DB_HELPER.getWritableDatabase();
            DB_HELPER.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeDB() {
        try {
            DB_HELPER.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserSong(DBDetail dbDetail) {
        try {

            if (isSongExist(dbDetail.getId())) {
                return;
            }

            sampleDB = DB_HELPER.getDB();
            sampleDB.beginTransaction();

            String sql = "Insert or Replace into " + TABLENAME + " values(?,?,?,?,?,?,?);";
            SQLiteStatement insert = sampleDB.compileStatement(sql);

            try {
                if (dbDetail != null) {
                    insert.clearBindings();
                    insert.bindLong(1, dbDetail.getId());
                    insert.bindString(2, dbDetail.getNews());
                    insert.bindString(3, dbDetail.getMusic());
                    insert.bindString(4, dbDetail.getStudty());
                    insert.bindString(5, dbDetail.getVideo());
                    insert.bindString(6, dbDetail.getIsDownLoad());
                    insert.bindLong(7, 1);
                    insert.execute();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sampleDB.setTransactionSuccessful();

        } catch (Exception e) {
            Log.e("XML:", e.toString());
        } finally {
            sampleDB.endTransaction();
        }
    }

    private boolean isSongExist(int id_) {
        Cursor mCursor = null;
        boolean isExist = false;
        try {
            String sqlQuery = "select * from " + TABLENAME + " where " + ID + "=" + id_;
            sampleDB = DB_HELPER.getDB();
            mCursor = sampleDB.rawQuery(sqlQuery, null);
            if (mCursor != null && mCursor.getCount() >= 1) {
                mCursor.moveToNext();
                long count = mCursor.getLong(mCursor.getColumnIndex(PLAYCOUNT));
                count++;
                updateStatus_(count, id_);
                isExist = true;
            }
            closeCurcor(mCursor);
        } catch (Exception e) {
            closeCurcor(mCursor);
            e.printStackTrace();
        }
        return isExist;
    }

    public void updateStatus_(long count, int musicid) {
        try {
            ContentValues values = new ContentValues();
            values.put(PLAYCOUNT, count);
            long success = sampleDB.update(TABLENAME, values, ID + "=?", new String[]{String.valueOf(musicid)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeCurcor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
    }

}

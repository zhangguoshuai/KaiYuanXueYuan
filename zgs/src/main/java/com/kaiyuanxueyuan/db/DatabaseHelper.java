package com.kaiyuanxueyuan.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 张国帅 on 2016/9/12.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context_;
    private static String DATABASE_NAME = "zgs";
    private static int DATABASE_VERSION = 10;
    private String DB_PATH = "";
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context_ = context;
        DB_PATH = context.getDatabasePath(DATABASE_NAME).getPath();
        context.openOrCreateDatabase(DATABASE_NAME, SQLiteDatabase.OPEN_READWRITE, null);
    }

    public SQLiteDatabase getDB() {
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO 创建数据库后，对数据库的操作
        try {
            db.execSQL(sqlForCreateMostPlay());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO 更改数据库版本的操作
        try {
            db.execSQL("DROP TABLE IF EXISTS " + DBParameter.TABLENAME);
            onCreate(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db_) {
        super.onOpen(db_);
        // TODO 每次成功打开数据库后首先被执行
        try {
            db_ = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openDataBase() throws SQLException {
        try {
            db = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void close() {
        if (getDB() != null)
            getDB().close();

        super.close();
    }

    public static String sqlForCreateMostPlay() {
        String sql = "CREATE TABLE " + DBParameter.TABLENAME + " (" + DBParameter.ID + " INTEGER NOT NULL PRIMARY KEY,"
                + DBParameter.NEWS + " INTEGER NOT NULL,"
                + DBParameter.MUSIC + " TEXT NOT NULL,"
                + DBParameter.VIDEO + " TEXT NOT NULL,"
                + DBParameter.ISDOWNLOAD + " TEXT NOT NULL,"
                + DBParameter.STUDY + " TEXT NOT NULL,"
                + DBParameter.PLAYCOUNT + " INTEGER NOT NULL);";
        return sql;
    }
}

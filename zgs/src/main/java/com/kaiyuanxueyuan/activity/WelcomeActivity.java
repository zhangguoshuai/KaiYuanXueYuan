package com.kaiyuanxueyuan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.db.DatabaseHelper;
import com.kaiyuanxueyuan.fragment.liveFragment.LiveTrailer;

/**
 * 欢迎页
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                    DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                    databaseHelper.getWritableDatabase();
                    databaseHelper.openDataBase();
                    LiveTrailer.getTraile();
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

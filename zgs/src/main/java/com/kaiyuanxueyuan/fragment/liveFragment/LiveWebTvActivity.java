package com.kaiyuanxueyuan.fragment.liveFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kaiyuanxueyuan.R;
import com.kaiyuanxueyuan.utils.ShowInfo;

/**
 * 提供一些视频资源网站 webview加载
 * Created by Administrator on 2016/8/19.
 */
public class LiveWebTvActivity extends AppCompatActivity implements View.OnClickListener {

    // 进度条
    private ProgressBar pb;
    private WebView mWebView;
    private TextView webtitle;
    private ImageView back;
    private String Url = "http://tv.youku.com/";
    // 退出标志位 1 退出app
    int flage, isFirst = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_web);
        mWebView = (WebView) findViewById(R.id.live_web_webview);
        pb = (ProgressBar) findViewById(R.id.live_web_pb);
        webtitle = (TextView) findViewById(R.id.live_web_title);
        back = (ImageView) findViewById(R.id.live_web_back);
        back.setOnClickListener(this);
        // pb.setIndeterminate(true);
        getMyIntent();
        initWebView();
    }

    public void getMyIntent(){
        Intent intent = this.getIntent();
        Url = intent.getStringExtra("TV_URL");
    }

    public void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl(Url);
        mWebView.requestFocusFromTouch();
        // 优先使用缓存
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.setWebViewClient(new WebViewClientBase());
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress > 80) {
                    Log.i("onProgressChanged()", "isFirst" + isFirst);
                    pb.setVisibility(View.GONE);
                } else {
                    if (View.VISIBLE != pb.getVisibility()) {
                        pb.setPressed(true);
                        pb.setVisibility(View.VISIBLE);
                    }
                    pb.setProgress(newProgress);
                }

                super.onProgressChanged(view, newProgress);

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                webtitle.setText(title);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    private class WebViewClientBase extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            isFirst = 0;
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

        }

//        @Override
//        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//            // TODO Auto-generated method stub
//            super.onReceivedError(view, errorCode, description, failingUrl);
//        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            ShowInfo.Toast(getApplicationContext(), "出错回首页");
            isFirst = 0;
            view.loadUrl(Url);
        }

        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            // TODO Auto-generated method stub
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if(mWebView.canGoBack()){
                mWebView.goBack();
            } else if (isFirst == 1) {
                finish();
            }else{
                isFirst = 1;
                Toast.makeText(getApplicationContext(), "再点击一次退出", Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

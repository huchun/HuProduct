package com.client.huaccount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.client.huaccount.base.BaseActivity;

/**
 * Created by l on 2018/7/18.
 */

public class AboutHtmlActivity extends BaseActivity {

    private Toolbar  mToolbar = null;
    private ProgressBar mProgressbar = null;
    private WebView     mWebview = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgressbar=(ProgressBar) findViewById(R.id.progressbar);
        mWebview = (WebView) findViewById(R.id.webview);

        String url = "https://www.baidu.com/";//测试访问百度首页

        //setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWebview.loadUrl(url);
        init();

    }

    private void init() {
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
               if (newProgress == 100){
                    mProgressbar.setVisibility(View.GONE);
               }else{
                     mProgressbar.setVisibility(View.VISIBLE);
                     mProgressbar.setProgress(newProgress);
               }
            }
        });
    }


}

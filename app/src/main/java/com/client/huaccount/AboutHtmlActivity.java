package com.client.huaccount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.client.huaccount.base.BaseActivity;
import com.client.huaccount.http.Networkutil;
import com.client.huaccount.http.httpServices;

/**
 * Created by l on 2018/7/18.
 */

public class AboutHtmlActivity extends BaseActivity {

    private static final String TAG = "AboutHtmlActivity";

    public Toolbar mToolbar = null;
    private ProgressBar  mProgressBar = null;
    private TextView    mEmptyView = null;
    private WebView     mWebview = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webview);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);
        mEmptyView = (TextView)findViewById(android.R.id.empty);
        mWebview = (WebView) findViewById(R.id.webview);

        setupBackAsUp(R.string.action_settings);
        init();
        Log.d(TAG,"onCreate");
    }

    private void init() {
        if (Networkutil.hasNetWorkConnection(this)){
            mWebview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        }else{
            mWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        mWebview.loadUrl(httpServices.Webview_url);
        mWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "onProgressChanged" + url.toString());
                view.loadUrl(url);
                return true;
            }
        });

        mWebview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d(TAG, "onProgressChanged");
               if (newProgress == 0){
                   Log.d(TAG, "newProgress");
                   mProgressBar.setProgress(newProgress);
                   mProgressBar.setVisibility(View.VISIBLE);
                   mEmptyView.setVisibility(View.VISIBLE);
             /*  }else if (newProgress == 50){
                   Log.d(TAG, "newProgress 50");
                   mProgressBar.setVisibility(View.VISIBLE);
                   mEmptyView.setVisibility(View.VISIBLE);*/
               }else if (newProgress == 100){
                   Log.d(TAG, "newProgress 100");
                   mProgressBar.setVisibility(View.GONE);
                   mEmptyView.setVisibility(View.GONE);
               }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }
}

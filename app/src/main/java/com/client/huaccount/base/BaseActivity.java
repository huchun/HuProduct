package com.client.huaccount.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.client.huaccount.http.OkHttpUtil;

/**
 * Created by l on 2018/7/15.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    public OkHttpUtil okhttpUtil = OkHttpUtil.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    public void setupBackAsUp(int title) {
        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null){
            toolbar.setTitle(title);
            toolbar.setDisplayHomeAsUpEnabled(true);
            toolbar.setDisplayShowHomeEnabled(false);
        }
    }


    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "keyCode" + "event");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}

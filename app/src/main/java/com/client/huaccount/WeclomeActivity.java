package com.client.huaccount;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.client.huaccount.base.BaseActivity;
import com.client.huaccount.configure.ConstantInfo;

/**
 * Created by l on 2018/7/28.
 */

public class WeclomeActivity extends BaseActivity {

   private Handler  mHandler;
   public boolean  isFirst = ConstantInfo.getIsFirst(ConstantInfo.isFirst);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window  window = getWindow();
        window.setFlags(flag,flag);
        setContentView(R.layout.layout_weclome);
        mHandler = new Handler();
        ConstantInfo.setIsFirst(ConstantInfo.isFirst, false);
        handleSplash();
    }

    private void handleSplash() {
        mHandler.postDelayed(new Runnable() {
            Intent intent = new Intent();

            @Override
            public void run() {
                if (isFirst){
                     intent = new Intent(WeclomeActivity.this, GuideActivity.class);
                }else{
                    intent = new Intent(WeclomeActivity.this, MainActivity.class);
                }
                WeclomeActivity.this.startActivity(intent);
                WeclomeActivity.this.finish();
            }
        }, 3000);
    }
}

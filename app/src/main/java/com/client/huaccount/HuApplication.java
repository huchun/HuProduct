package com.client.huaccount;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by l on 2018/7/15.
 */

public class HuApplication extends Application {

    private static final String TAG = "HuApplication";

   private static HuApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    public HuApplication(){
        mInstance = this;
    }

    public static Context getContext() {
        return mInstance;
    }
}

package com.client.huaccount.configure;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.client.huaccount.HuApplication;

/**
 * Created by l on 2018/7/28.
 */

public class ConstantInfo {

    public static final  String  isFirst= "isFirst";
    public static Context mContext = HuApplication.getContext();
    public static Preferences mPreference = Preferences.getPrefer(mContext);
    private static Handler mHandler;
    /**
     * save isFirst login
     * @param isFirst
     * @param isFirst
     */
    public static void setIsFirst(String isFirstStr, boolean isFirst) {
        mPreference.putBoolean(isFirstStr, isFirst);
    }

    public static boolean getIsFirst(String isFirstStr) {
        return mPreference.getBoolean(isFirstStr,true);
    }

    public static Handler getHandler() {
        if (null == mHandler){
            mHandler = new Handler();
        }
        return mHandler;
    }
}

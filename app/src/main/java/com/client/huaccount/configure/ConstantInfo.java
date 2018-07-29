package com.client.huaccount.configure;

import android.content.Context;

import com.client.huaccount.HuApplication;

/**
 * Created by l on 2018/7/28.
 */

public class ConstantInfo {

    public static final  String  isFirst= "isFirst";
    public static Context mContext = HuApplication.getContext();
    public static Preferences mPreference = Preferences.getPrefer(mContext);

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
}

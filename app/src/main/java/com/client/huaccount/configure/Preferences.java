package com.client.huaccount.configure;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by l on 2018/7/28.
 */

public class Preferences {

    private static Preferences mInstance = null;
    private SharedPreferences   mSharedPreferences;
    private SharedPreferences.Editor  mEditor;
    private boolean  exist = false;

    public synchronized static Preferences getPrefer(Context context) {
        if (null == mInstance){
            mInstance = new Preferences(context, null);
        }
        return mInstance;
    }

    public Preferences(Context context, String name){
        if (name == null  || name.toString().trim().length() == 0){
            name = context.getPackageName();
        }
        mSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    /**
     *
     * @param key
     * @param flag
     */
    public void putBoolean(String key, boolean flag) {
       mEditor.putBoolean(key, flag);
       mEditor.commit();
    }

    /**
     *
     * @param key
     * @param defValue
     * @return
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }



}

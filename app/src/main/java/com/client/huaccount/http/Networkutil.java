package com.client.huaccount.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by l on 2018/7/21.
 */

public class Networkutil {

    private static ConnectivityManager connectivityManager = null;

    public static ConnectivityManager getConnectivityManager(Context context){
        if (connectivityManager == null){
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return connectivityManager;
    }

    /**
     * if connection
     *
     * @return
     */
    public static boolean hasNetWorkConnection(Context context) {
        // conntion networkinfo
        NetworkInfo networkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isAvailable());
    }
}

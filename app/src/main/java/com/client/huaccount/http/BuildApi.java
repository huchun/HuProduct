package com.client.huaccount.http;

import android.util.Log;

import com.client.huaccount.HuApplication;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by l on 2018/8/4.
 */

public class BuildApi {

    private static Retrofit retrofit;

    public static APIService getNetWorkService() {
        Log.d("TAG", "getNetWorkService");
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpServices.base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(HuApplication.OkHttpUtil())
                    .build();
        }
        return retrofit.create(APIService.class);
    }
}

package com.client.huaccount.http;

import android.content.Context;
import android.util.Log;

import com.client.huaccount.HuApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by l on 2018/8/2.
 */

public class OkHttpUtil {

    private OkHttpClient.Builder mOkHttpClient;
    private static OkHttpUtil  okhttp = null;

   public OkHttpUtil(){
       /*mOkHttpClient = new OkHttpClient.Builder();
       mOkHttpClient.readTimeout(20*1000, TimeUnit.MILLISECONDS);
       mOkHttpClient.writeTimeout(30*1000, TimeUnit.MILLISECONDS);
       mOkHttpClient.connectTimeout(15*1000, TimeUnit.MILLISECONDS);
       File httpCacheDirectory = new File(HuApplication.getContext().getCacheDir(), "okhttpCache");
       Cache cache = new Cache(httpCacheDirectory, 10*1024*1024);
       mOkHttpClient.cache(cache);
       mOkHttpClient.addInterceptor(LoggingInterceptor);
       //mOkHttpClient.addInterceptor(new ChuckInterceptor(okhttp));
       mOkHttpClient.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
       mOkHttpClient.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
       mOkHttpClient.build();*/
   }

    public static synchronized OkHttpUtil getInstance() {
        if (okhttp == null){
            okhttp = new OkHttpUtil();
        }
        return okhttp;
    }

    /*private static Interceptor LoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            MediaType mediaType = response.body().contentType();
            String  content = response.body().string();
            Log.d("TAG", "-----LoggingInterceptor----- :\nrequest url:" + request.url() + "\ntime:" + (t2 - t1) / 1e6d + "\nbody:" + content + "\n");
            return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
        }
    };

    private Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            boolean connection = Networkutil.hasNetWorkConnection(HuApplication.getContext());
            Request request = chain.request();
            if (!connection){
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (connection){
                String cacheControl = request.cacheControl().toString();
                response.newBuilder().removeHeader("Pragma")
                         .header("Cache-Control", cacheControl)
                         .build();
            }else{
                int maxStale = 60 * 60 * 24 * 7;
                response.newBuilder().removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        }
    };*/
}

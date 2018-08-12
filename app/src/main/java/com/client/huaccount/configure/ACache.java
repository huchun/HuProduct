package com.client.huaccount.configure;

import android.content.Context;

import com.client.huaccount.util.TimeUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by l on 2018/8/12.
 */

public class ACache {

    private static final String ACaches = "ACache";
    private static final int MAX_SIZE = 1000 * 1000 * 50;
    private static final int MAX_COUNT = Integer.MAX_VALUE;// 不限制存放数据的数量
    private static Map<String, ACache> mInstanceMap = new HashMap<>();
    private ACacheManager mCache;

    public ACache(File file, int maxSize, int maxCount) {
        if (!file.exists() && !file.mkdirs()){
            throw new RuntimeException("can't make dirs in " + file.getAbsolutePath());
        }
        mCache = new ACacheManager(file, maxSize, maxCount);
    }

    public static ACache get(Context context) {
        return get(context, ACaches);
    }

    private static ACache get(Context context, String cacheName) {
        File file = new File(context.getCacheDir(), cacheName);
        return get(file,MAX_SIZE, MAX_COUNT);
    }

    private static ACache get(File file, int maxSize, int maxCount) {
        ACache aCache = mInstanceMap.get(file.getAbsolutePath() + myPid());
        if (aCache == null){
            aCache = new ACache(file, maxSize, maxCount);
            mInstanceMap.put(file.getAbsolutePath() + myPid(), aCache);
        }
        return aCache;
    }

    private static String myPid(){
        return "_" + android.os.Process.myPid();
    }

    /**
     * 读取 Serializable数据
     * @param key
     * @return Serializable 数据
     */
    public Object getAsObject(String key) {
        byte[] data = getAsBinary(key);
        if (data != null){
            ByteArrayInputStream bis = null;
            ObjectInputStream ois = null;

            try {
                bis = new ByteArrayInputStream(data);
                ois = new ObjectInputStream(bis);
                Object object = ois.readObject();
                return object;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (bis != null){
                        bis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (ois != null){
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    /**
     * 获取 byte 数据
     * @param key
     * @return byte 数据
     */
    private byte[] getAsBinary(String key) {
        RandomAccessFile rafile = null;
        boolean removeFile = false;

        try {
            File file = mCache.get(key);
            if (!file.exists()){
                return null;
            }
            rafile = new RandomAccessFile(file, "r");
            byte[] byteArray = new byte[(int) rafile.length()];
            rafile.read(byteArray);
            if (!TimeUtils.isDue(byteArray)){
                return TimeUtils.clearDateInfo(byteArray);
            }else{
                removeFile = true;
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rafile != null) {
            try {
                rafile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            if (removeFile){
                remove(key);
            }
        }
        return null;
    }

    private boolean remove(String key) {
        return mCache.remove(key);
    }
}

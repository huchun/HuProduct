package com.client.huaccount.configure;

import android.content.Context;

import com.client.huaccount.bean.LoginInfo;
import com.client.huaccount.util.TimeUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by l on 2018/8/12.
 */

public class ACache {

    private static final String ACaches = "ACache";
    private static final int MAX_SIZE = 1000 * 1000 * 50;
    private static final int MAX_COUNT = Integer.MAX_VALUE;// 不限制存放数据的数量
    private static Map<String, ACache> mInstanceMap = new HashMap<String, ACache>();
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
            if (!file.exists())
                return null;

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

    // =======================================
    // ============= 序列化 数据 读写 ===============
    // =======================================
    /**
     * 保存 Serializable数据 到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的value
     */
    public void put(String key, Serializable value) {
        put(key, value, -1);
    }

    /**
     * 保存 Serializable数据到 缓存中
     *
     * @param key
     *            保存的key
     * @param value
     *            保存的value
     * @param saveTime
     *            保存的时间，单位：秒
     */
    private void put(String key, Serializable value, int saveTime) {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            byte[] data = bos.toByteArray();
            if (saveTime != -1){
                put(key, data, saveTime);
            }else{
                put(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
                try {
                    if (oos != null && bos != null) {
                        oos.close();
                        bos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }

    public void put(String key, byte[] value){
        File file = mCache.newFile(key);
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            fos.write(value);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mCache.put(file);
        }

    }
}

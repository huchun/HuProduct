package com.client.huaccount.configure;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 缓存管理器
 * Created by l on 2018/8/12.
 */

public class ACacheManager {

    private AtomicLong cacheSize;
    private AtomicInteger cacheCount;
    private long  sizeLimit;
    private int countLimit;
    private Map<File, Long>  lastUsageDatas = Collections.synchronizedMap(new HashMap<File, Long>());
    private File cacheDir;

    public ACacheManager(File file, int maxSize, int maxCount) {
        this.cacheDir = file;
        this.sizeLimit = maxSize;
        this.countLimit = maxCount;
        cacheSize = new AtomicLong();
        cacheCount = new AtomicInteger();
        calculateCacheSizeAndCacheCount();
    }

    /**
     * 计算 cacheSize和cacheCount
     */
    private void calculateCacheSizeAndCacheCount() {
       new Thread(new Runnable() {
           @Override
           public void run() {
               int size = 0;
               int count = 0;
               File[] cachedFiles = cacheDir.listFiles();
               if (cachedFiles != null){
                   for (File cachedFile : cachedFiles){
                       size += calculateSize(cachedFile);
                       count += 1;
                       lastUsageDatas.put(cachedFile, cachedFile.lastModified());
                   }
                   cacheSize.set(size);
                   cacheCount.set(count);
               }
           }
       }) .start();
    }

    private long calculateSize(File file) {
        return file.length();
    }

    public File get(String key) {
        File file = newFile(key);
        Long currentTime = System.currentTimeMillis();
        file.setLastModified(currentTime);
        lastUsageDatas.put(file, currentTime);
        return file;
    }

    private File newFile(String key) {
        return new File(cacheDir, key.hashCode() + "");
    }

    public boolean remove(String key) {
        File image = get(key);
        return image.delete();
    }
}

package com.client.huaccount.configure;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

    public File newFile(String key) {
        return new File(cacheDir, key.hashCode() + "");
    }

    public boolean remove(String key) {
        File image = get(key);
        return image.delete();
    }

    public void put(File file) {
        int curCacheCount = cacheCount.get();
        while (curCacheCount + 1 > countLimit){
            long freedSize = removeNext();
            cacheSize.addAndGet(-freedSize);
            curCacheCount=cacheCount.addAndGet(-1);
        }
        cacheCount.addAndGet(1);

        long valueSize = calculateSize(file);
        long curCacheSize = cacheSize.get();
        while (curCacheSize + valueSize > sizeLimit){
            long freedSize = removeNext();
            curCacheSize = cacheSize.addAndGet(-freedSize);
        }
        cacheSize.addAndGet(valueSize);

        Long currentTime = System.currentTimeMillis();
        file.setLastModified(currentTime);
        lastUsageDatas.put(file, curCacheSize);
    }

    /**
     * 移除旧的文件
     *
     * @return
     */
    private long removeNext() {
       if (lastUsageDatas.isEmpty()){
           return 0;
       }
       Long oldestUsage = null;
       File mostLongUsedFile = null;
        Set<Map.Entry<File, Long>> entities = lastUsageDatas.entrySet();
        synchronized (lastUsageDatas){
            for (Map.Entry<File, Long> entry : entities){
                if (mostLongUsedFile == null){
                    mostLongUsedFile = entry.getKey();
                    oldestUsage = entry.getValue();
                }else{
                    Long lastValueUsage = entry.getValue();
                    if (lastValueUsage < oldestUsage){
                        oldestUsage = lastValueUsage;
                        mostLongUsedFile = entry.getKey();
                    }
                }
            }
        }
       long fileSize = calculateSize(mostLongUsedFile);
        if (mostLongUsedFile.delete()){
            lastUsageDatas.remove(mostLongUsedFile);
        }
        return fileSize;
    }
}

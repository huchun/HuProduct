package com.client.huaccount.util;

/**
 * 时间计算工具类
 * Created by l on 2018/8/12.
 */

public class TimeUtils {

    private static final char mSeparator = ' ';
    /**
     * 判断缓存的String数据是否到期
     * @param str
     * @return true：到期了 false：还没有到期
     */
    public static boolean isDue(String str) {
        return isDue(str.getBytes());
    }
    /**
     * 判断缓存的byte数据是否到期
     *
     * @param data
     * @return true：到期了 false：还没有到期
     */
    public static boolean isDue(byte[] data) {
        String[] strs = getDateInfoFromDate(data);
        if (strs != null && strs.length == 2){
            String saveTimeStr = strs[0];
            while (saveTimeStr.startsWith("0")){
                saveTimeStr = saveTimeStr.substring(1, saveTimeStr.length());
            }
            long saveTime = Long.valueOf(saveTimeStr);
            long deleteAfter = Long.valueOf(strs[1]);
            if (System.currentTimeMillis() > saveTime + deleteAfter * 1000){
                return true;
            }
        }
        return false;
    }

    private static String[] getDateInfoFromDate(byte[] data) {
        if (hasDateInfo(data)){
            String saveDate = new String(copyOfRange(data, 0, 13));
            String deleteAfter = new String(copyOfRange(data, 14, indexOf(data, mSeparator)));
            return new String[]{saveDate, deleteAfter};
        }
        return null;
    }

    private static boolean hasDateInfo(byte[] data) {
        return data != null && data.length > 15 && data[13] == '-' && indexOf(data, mSeparator) > 14;
    }

    private static byte[] copyOfRange(byte[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        byte[] copy = new byte[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }

    private static int indexOf(byte[] data, char c) {
        for (int i = 0; i < data.length; i++){
            if (data[i] == c){
                return i;
            }
        }
        return -1;
    }

    public static byte[] clearDateInfo(byte[] data) {
        if (hasDateInfo(data)){
            return copyOfRange(data, indexOf(data, mSeparator)+1, data.length);
        }
        return data;
    }
}

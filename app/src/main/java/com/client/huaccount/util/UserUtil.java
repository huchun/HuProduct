package com.client.huaccount.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.client.huaccount.bean.LoginInfo;
import com.client.huaccount.configure.ACache;

/**
 * Created by l on 2018/8/12.
 */

public class UserUtil {

    private static Context mContext;
    private static ACache aCache;
    private static final String cache_userLogin = "cache_userLogin";

   public UserUtil(){
        aCache = ACache.get(mContext);
    }

    public static LoginInfo getUserCache() {
        LoginInfo loginInfo = (LoginInfo) UserUtil.getACache().getAsObject(cache_userLogin);
        if (loginInfo == null){
            loginInfo = new LoginInfo();
        }
        return loginInfo;
    }

    private static ACache getACache() {
        return aCache;
    }

    public static boolean CheckRegister(Context context, String account, String password, String to_password) {
         mContext = context;
        if (TextUtils.isEmpty(account)){
            Toast.makeText(mContext, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(to_password)){
            Toast.makeText(mContext, "再输入密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 6){
            Toast.makeText(mContext, "密码不能少于6位", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(to_password)){
            Toast.makeText(mContext, "两次输入不一样", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean CheckLogin(Context context, String account, String password) {
        mContext = context;
        if (TextUtils.isEmpty(account)){
            Toast.makeText(mContext, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}

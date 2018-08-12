package com.client.huaccount.http;

import android.util.Log;

import com.client.huaccount.HuApplication;
import com.client.huaccount.R;
import com.client.huaccount.bean.LoginInfo;
import com.client.huaccount.bean.RegisterInfo;
import com.client.huaccount.bean.UserBeanEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by l on 2018/8/4.
 */

public class UserRequest {

    public static final String TAG = "UserRequest";
    public final static String GET_REGISTER_FAIL = HuApplication.getContext().getString(R.string.account_register_success);
    public final static String GET_DATA_FAIL = HuApplication.getContext().getString(R.string.gank_get_data_fail);
    public final static String NET_FAIL = HuApplication.getContext().getString(R.string.gank_net_fail);

    public static Call<UserBeanEntity<RegisterInfo>> userRegister(String userName, String userPassword, final int what, final MyCallBack myCallBack) {
        Log.d(TAG, "userRegister" + userName.toString() + userPassword.toString());

        Call<UserBeanEntity<RegisterInfo>> call = BuildApi.getNetWorkService().userRegister(userName,userPassword);
        call.enqueue(new Callback<UserBeanEntity<RegisterInfo>>() {
            @Override
            public void onResponse(Call<UserBeanEntity<RegisterInfo>> call, Response<UserBeanEntity<RegisterInfo>> response) {
               Log.d(TAG, "onResponse"+response.toString());
               if (response.isSuccessful()){
                   UserBeanEntity body = response.body();
                   if (body != null){
                       /**
                        * 0  success
                        */
                       if (body.getStatus().equals("0")){
                           Log.d(TAG, "0" + body.toString());
                           myCallBack.onSuccess(what, body.getMsg());
                       }else {
                           myCallBack.onFail(what, body.getMsg());
                       }
                   }else{
                       myCallBack.onFail(what, GET_DATA_FAIL);
                   }
               }else {
                   myCallBack.onFail(what, GET_DATA_FAIL);
               }
            }

            @Override
            public void onFailure(Call<UserBeanEntity<RegisterInfo>> call, Throwable t) {
                Log.d(TAG, "onFailure"+ t.toString());
               myCallBack.onFail(what, NET_FAIL);
            }
        });
        return call;
    }

    public static Call<UserBeanEntity<LoginInfo>>  userLogin(String username, String password, final int what, final MyCallBack myCallBack) {
        Log.d(TAG, "userLogin" + username.toString() + password.toString());
        Call<UserBeanEntity<LoginInfo>>  call = BuildApi.getNetWorkService().userLogin(username, password);
        call.enqueue(new Callback<UserBeanEntity<LoginInfo>>() {
            @Override
            public void onResponse(Call<UserBeanEntity<LoginInfo>> call, Response<UserBeanEntity<LoginInfo>> response) {
                Log.d(TAG, "onResponse" + response.toString());
                if (response.isSuccessful()){
                    UserBeanEntity body = response.body();
                    if (body != null){
                        if (body.getStatus().equals("0")){
                            Log.d(TAG, "success" + body.toString());
                            myCallBack.onSuccess(what, body.getMsg());
                        }else{
                            myCallBack.onFail(what, body.getMsg());
                        }
                    }else{
                        myCallBack.onFail(what, GET_DATA_FAIL);
                    }
                }else {
                    myCallBack.onFail(what, GET_DATA_FAIL);
                }
            }

            @Override
            public void onFailure(Call<UserBeanEntity<LoginInfo>> call, Throwable t) {
                Log.d(TAG, "onFailure" + t.toString());
                myCallBack.onFail(what, GET_DATA_FAIL);
            }
        });
        return call;
    }
}

package com.client.huaccount.http;

import com.client.huaccount.bean.LoginInfo;
import com.client.huaccount.bean.RegisterInfo;
import com.client.huaccount.bean.UserBeanEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by l on 2018/8/4.
 */

public interface APIService {

    /**
     * user register
     *
     * @param username
     * @param password
     * @return
     */
    @POST(HttpServices.base_url + HttpServices.register_url)
    @FormUrlEncoded
    Call<UserBeanEntity<RegisterInfo>> userRegister(@Field("username") String username, @Field("password") String password);

    /**
     * user login
     * @param username
     * @param password
     * @return
     */
    @POST(HttpServices.base_url + HttpServices.login_url)
    @FormUrlEncoded
    Call<UserBeanEntity<LoginInfo>> userLogin(@Field("username") String username, @Field("password") String password);
}

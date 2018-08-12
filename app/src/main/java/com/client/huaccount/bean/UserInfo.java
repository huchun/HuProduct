package com.client.huaccount.bean;

import java.io.Serializable;

/**
 * Created by l on 2018/8/4.
 */

public class UserInfo implements Serializable {

    private static final long serialVersionUID = -2600330151554512031L;

    private String id;
    private String username;
    private String password;
    private String topassword;
    private String accessToken;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTopassword() {
        return topassword;
    }

    public void setTopassword(String topassword) {
        this.topassword = topassword;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

package com.client.huaccount.bean;

import java.io.Serializable;

/**
 * Created by l on 2018/8/11.
 */

public class RegisterInfo implements Serializable{

    /**
     * id : 83
     * username : huunv
     * password : $2y$13$SDnfU9JagDLJ55u9wLD0muSH2fhI43Z06FPJQ21BWg6v7eocH1ce.
     */

    private int id;
    private String username;
    private String password;
    //private String accessToken;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}

package com.client.huaccount.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by l on 2018/8/4.
 */

public class UserBeanEntity<T> implements Serializable {

    private static final long serialVersionUID = -4553802208756427393L;

    /**
     * data : {"id":83,"username":"huunv","password":"$2y$13$SDnfU9JagDLJ55u9wLD0muSH2fhI43Z06FPJQ21BWg6v7eocH1ce."}
     */
    private String msg;
    private String status;
    private T   data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

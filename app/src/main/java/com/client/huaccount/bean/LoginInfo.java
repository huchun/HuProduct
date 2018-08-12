package com.client.huaccount.bean;

import java.io.Serializable;

/**
 * Created by l on 2018/7/31.
 */

public class LoginInfo implements Serializable{

     /**
      * status : 0
      * msg : login successful
      * data : {"id":90,"username":"huchunchun","password":"$2y$13$LWEJqopvIkUwnXs8dIMg.ukKCI/jd06xAUGRLcg4v1aryO9DQ4xbO","authKey":null,"accessToken":"_uTN0NJ_Fz_9Qh0QLfaJLxNFmnRLWoBt"}
      */

     private String id;
     private String  username;
     private String  password;
     private String  accessToken;

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

     public String getAccessToken() {
          return accessToken;
     }

     public void setAccessToken(String accessToken) {
          this.accessToken = accessToken;
     }

     @Override
     public String toString() {
          return "LoginInfo{" +
                  "id=" + id +
                  ", username='" + username + '\'' +
                  ", password='" + password + '\'' +
                  ", accessToken='" + accessToken + '\'' +
                  '}';
     }
}

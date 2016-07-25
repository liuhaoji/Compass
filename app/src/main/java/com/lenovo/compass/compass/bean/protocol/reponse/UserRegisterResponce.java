package com.lenovo.compass.compass.bean.protocol.reponse;

/**
 * Created by liuxin on 2/6/16.
 */
public class UserRegisterResponce {
    String UserKey = "";
    int OpenId = -1;

    public String getUserKey() {
        return UserKey;
    }

    public void setUserKey(String userKey) {
        UserKey = userKey;
    }

    public int getOpenId() {
        return OpenId;
    }

    public void setOpenId(int openId) {
        OpenId = openId;
    }
}

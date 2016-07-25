package com.lenovo.compass.compass.bean.protocol.reponse;

/**
 * Created by hansentian on 5/20/16.
 */
public class UserLoginResponse {
    public String UserKey;
    public int AccountStat = 2; //用户账号状�?�，见Utils.ECookieStatType定义
    public int OpenId = -1;

    public String getUserKey() {
        return UserKey;
    }

    public void setUserKey(String userKey) {
        UserKey = userKey;
    }

    public int getAccountStat() {
        return AccountStat;
    }

    public void setAccountStat(int accountStat) {
        AccountStat = accountStat;
    }

    public int getOpenId() {
        return OpenId;
    }

    public void setOpenId(int openId) {
        OpenId = openId;
    }
}

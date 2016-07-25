package com.lenovo.compass.compass.bean.protocol.request;


/**
 * Created by Liuxin on 25/7/16.
 */
public class UserLoginReq {
    public int BusinessId = 1001; //客户ID
    public String UserAccount = ""; //用户账号
    public String UserPassword = ""; //用户密码

    public UserLoginReq(String userAccount, String userPassword) {
        UserAccount = userAccount;
        UserPassword = userPassword;
    }

    public int getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(int businessId) {
        BusinessId = businessId;
    }

    public String getUserAccount() {
        return UserAccount;
    }

    public void setUserAccount(String userAccount) {
        UserAccount = userAccount;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }
}

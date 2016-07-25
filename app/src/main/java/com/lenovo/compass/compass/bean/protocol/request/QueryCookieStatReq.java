package com.lenovo.compass.compass.bean.protocol.request;

/**
 * Created by Liuxin on 7/22/16.
 */
public class QueryCookieStatReq {
    public String UserKey = ""; //用户cookie

    public QueryCookieStatReq(String UserKey) {
        this.UserKey = UserKey;
    }
}

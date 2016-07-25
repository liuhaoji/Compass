package com.lenovo.compass.compass.protocol.apiImp;


import com.lenovo.compass.compass.bean.protocol.request.QueryCookieStatReq;
import com.lenovo.compass.compass.bean.protocol.request.UserLoginReq;
import com.lenovo.compass.compass.net.response.KsvcHttpCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;

public class UserApi extends BaseModelApi {
    public static final String HTTP_METHOD = "HttpMethod";
    public static final String CONTENT_TYPE = "ContentType";
    public static final String DATE = "Date";
    public static final String CONTENT_MD_5 = "ContentMd5";
    public static final String RESOURCE = "Resource";
    public static final String HEADER = "Header";
    public static final String SIGNATURE = "Signature";
    public static final String TAG = "UserApi";
    public static final String BUCKET_NAME = "BucketName";
    public static final String OBJECT_KEY = "ObjectKey";
    public static final String DOWNLOAD_URL = "DownloadUrl";
    private static final String HEADERS = "Headers";


    public static void doLogin(String phone, String password, KsvcHttpCallback ksvcHttpCallback) {
        exeRequest(SERVER_PREFIX + UserLogin, new UserLoginReq(phone, password), ksvcHttpCallback);
    }


    public static void doCheckedCookieAction(String cookie, KsvcHttpCallback ksvcHttpCallback) {
        exeRequestWithCookie(SERVER_PREFIX + QueryCookieStat, new QueryCookieStatReq(cookie), ksvcHttpCallback);
    }


    public static void doLogout(KsvcHttpCallback ksvcHttpCallback) {
        exeRequestWithCookie(SERVER_PREFIX + UserLogout, ksvcHttpCallback);
    }



}

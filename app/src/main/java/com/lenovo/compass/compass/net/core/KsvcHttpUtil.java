package com.lenovo.compass.compass.net.core;


import com.lenovo.compass.compass.net.request.KsvcHttpGetRequest;
import com.lenovo.compass.compass.net.request.KsvcHttpGetRequestBuilder;
import com.lenovo.compass.compass.net.request.KsvcHttpPostRequest;
import com.lenovo.compass.compass.net.request.KsvcHttpPostRequestBuilder;
import com.lenovo.compass.compass.net.response.KsvcHttpCallback;

import java.util.Map;

public class KsvcHttpUtil {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";

    public static void get(String url, Map<String, String> params, Map<String, String> headers, KsvcHttpCallback callback) {
        KsvcHttpGetRequest ksvcHttpGetRequest = new KsvcHttpGetRequestBuilder(KsvcHttpClient.getInstance().getHttpProvider()).method(METHOD_GET).url(url).params(params).headers(headers).build();
        KsvcHttpClient.getInstance().asyncExec(ksvcHttpGetRequest, callback);
    }

    public static void post(String url, Map<String, String> params, Map<String, String> headers, KsvcHttpCallback callback) {
        KsvcHttpPostRequest ksvcHttpPostRequest = new KsvcHttpPostRequestBuilder(KsvcHttpClient.getInstance().getHttpProvider()).method(METHOD_POST).url(url).params(params).headers(headers).build();
        KsvcHttpClient.getInstance().asyncExec(ksvcHttpPostRequest, callback);
    }

    public static void post(String url, String JSONString, Map<String, String> headers, KsvcHttpCallback callback) {
        KsvcHttpPostRequest ksvcHttpPostRequest = new KsvcHttpPostRequestBuilder(KsvcHttpClient.getInstance().getHttpProvider()).method(METHOD_POST).url(url).content(JSONString).headers(headers).build();
        KsvcHttpClient.getInstance().asyncExec(ksvcHttpPostRequest, callback);
    }

}

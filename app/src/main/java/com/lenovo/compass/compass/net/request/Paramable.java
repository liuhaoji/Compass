package com.lenovo.compass.compass.net.request;

import java.util.Map;

public interface Paramable
{
    KsvcHttpRequestBuilder params(Map<String, String> params);

    KsvcHttpRequestBuilder addParams(String key, String val);

}

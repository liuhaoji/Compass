package com.lenovo.compass.compass.net.request;



import com.lenovo.compass.compass.net.provider.KsvcNetworkProvider;

import java.util.Map;

public class KsvcHttpGetRequestBuilder extends KsvcHttpRequestBuilder implements Paramable{

    public KsvcHttpGetRequestBuilder(KsvcNetworkProvider httpProvider) {
        super(httpProvider);
    }

    @Override
    public KsvcHttpGetRequestBuilder method(String method) {
        setMethod(method);
        return this;
    }

    @Override
    public KsvcHttpGetRequestBuilder url(String url) {
        setUrl(url);
        return this;
    }

    @Override
    public KsvcHttpGetRequestBuilder tag(String tag) {
        setTag(tag);
        return this;
    }

    @Override
    public KsvcHttpGetRequestBuilder headers(Map<String, String> headers) {
        setHeaders(headers);
        return this;
    }

    @Override
    public KsvcHttpGetRequestBuilder addHeader(String key, String val) {
        getHeaders().put(key,val);
        return this;
    }

    @Override
    public KsvcHttpGetRequest build() {
        KsvcHttpGetRequest request = new KsvcHttpGetRequest();
        request.setMethod(getMethod());
        request.setUrl(getUrl());
        request.setTag(getTag());
        request.setHeaders(getHeaders());
        request.setParams(getParams());
        return request;
    }

    @Override
    public KsvcHttpGetRequestBuilder params(Map<String, String> params) {
        setParams(params);
        return this;
    }

    @Override
    public KsvcHttpGetRequestBuilder addParams(String key, String val) {
        getParams().put(key,val);
        return this;
    }
}

package com.lenovo.compass.compass.net.request;


import com.lenovo.compass.compass.net.provider.KsvcNetworkProvider;

import java.util.Map;

public class KsvcHttpPostRequestBuilder extends KsvcHttpRequestBuilder implements Paramable, Contentable {

    public KsvcHttpPostRequestBuilder(KsvcNetworkProvider httpProvider) {
        super(httpProvider);
    }

    public String content;

    @Override
    public KsvcHttpPostRequestBuilder method(String method) {
        setMethod(method);
        return this;
    }

    @Override
    public KsvcHttpPostRequestBuilder url(String url) {
        setUrl(url);
        return this;
    }

    @Override
    public KsvcHttpPostRequestBuilder tag(String tag) {
        setTag(tag);
        return this;
    }

    @Override
    public KsvcHttpPostRequestBuilder headers(Map<String, String> headers) {
        setHeaders(headers);
        return this;
    }

    @Override
    public KsvcHttpPostRequestBuilder addHeader(String key, String val) {
        getHeaders().put(key, val);
        return this;
    }

    @Override
    public KsvcHttpPostRequest build() {
        KsvcHttpPostRequest request = new KsvcHttpPostRequest();
        request.setMethod(getMethod());
        request.setUrl(getUrl());
        request.setTag(getTag());
        request.setHeaders(getHeaders());
        request.setParams(getParams());
        request.setContent(getContent());
        return request;
    }

    @Override
        public KsvcHttpPostRequestBuilder params(Map<String, String> params) {
        setParams(params);
        return this;
    }

    @Override
    public KsvcHttpPostRequestBuilder addParams(String key, String val) {
        getParams().put(key, val);
        return this;
    }

    @Override
    public KsvcHttpPostRequestBuilder content(String jsonString) {
        setContent(jsonString);
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

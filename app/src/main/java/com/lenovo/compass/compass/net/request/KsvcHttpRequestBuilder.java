package com.lenovo.compass.compass.net.request;


import com.lenovo.compass.compass.net.provider.KsvcNetworkProvider;

import java.util.Map;

public abstract class KsvcHttpRequestBuilder {
    protected String url;
    protected String tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;
    protected String method;
    protected KsvcNetworkProvider provider;

    public KsvcHttpRequestBuilder(KsvcNetworkProvider httpProvider) {
        if (httpProvider != null) {
            setProvider(httpProvider);
        } else {
            throw new IllegalArgumentException("Provider can not be null");
        }
    }

    public abstract KsvcHttpRequestBuilder method(String method);

    public abstract KsvcHttpRequestBuilder url(String url);

    public abstract KsvcHttpRequestBuilder tag(String tag);

    public abstract KsvcHttpRequestBuilder headers(Map<String, String> headers);

    public abstract KsvcHttpRequestBuilder addHeader(String key, String val);

    public abstract KsvcHttpRequest build();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public KsvcNetworkProvider getProvider() {
        return provider;
    }

    public void setProvider(KsvcNetworkProvider provider) {
        this.provider = provider;
    }
}

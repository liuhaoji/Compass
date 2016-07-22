package com.lenovo.compass.compass.net.request;


import java.util.Map;

public abstract class KsvcHttpRequest {
    protected String method;
    protected String url;
    protected String tag;
    protected Map<String, String> headers;
    protected Map<String, String> params;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getTag() {
        return tag;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getParams() {
        return params;
    }
}

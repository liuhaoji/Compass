package com.lenovo.compass.compass.net.request;

public class KsvcHttpPostRequest extends KsvcHttpRequest {

    public String Content;

    @Override
    public String getMethod() {
        return "POST";
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}

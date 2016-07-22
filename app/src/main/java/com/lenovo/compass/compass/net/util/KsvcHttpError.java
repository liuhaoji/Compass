package com.lenovo.compass.compass.net.util;


import java.io.UnsupportedEncodingException;
import java.util.Map;

public class KsvcHttpError {
    private final Map<String, String> headers;
    public int code;
    public String message;

    public KsvcHttpError(int statusCode, byte[] data, Map<String, String> headers) {
        this.code = statusCode;
        if (data != null) {
            try {
                this.message = new String(data, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        this.headers = headers;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

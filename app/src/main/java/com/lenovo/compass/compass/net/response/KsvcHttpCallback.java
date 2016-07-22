package com.lenovo.compass.compass.net.response;

import com.lenovo.compass.compass.net.util.KsvcHttpError;

import org.json.JSONObject;

public abstract class KsvcHttpCallback {
    public abstract void onSuccess(JSONObject response);
    public abstract void onFailure(KsvcHttpError ksvcHttpError);
}

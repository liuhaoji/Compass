package com.lenovo.compass.compass.net.provider;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lenovo.compass.compass.CompassApplication;
import com.lenovo.compass.compass.net.core.KsvcHttpClientConfig;
import com.lenovo.compass.compass.net.request.KsvcHttpRequest;
import com.lenovo.compass.compass.net.response.KsvcHttpCallback;


public class VolleyProvider implements KsvcNetworkProvider {

    private RequestQueue mRequestQueue;

    public VolleyProvider(KsvcHttpClientConfig config) {

    }

    @Override
    public void initProvider() {
        mRequestQueue = Volley.newRequestQueue(CompassApplication.getInstance());
    }

    @Override
    public void asyncExec(KsvcHttpRequest request, KsvcHttpCallback callback) {

    }

    @Override
    public void syncExec(KsvcHttpRequest request, KsvcHttpCallback callback) {

    }
}

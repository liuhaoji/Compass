package com.lenovo.compass.compass.net.provider;

import com.lenovo.compass.compass.net.core.KsvcHttpClientConfig;
import com.lenovo.compass.compass.net.request.KsvcHttpRequest;
import com.lenovo.compass.compass.net.response.KsvcHttpCallback;

import okhttp3.OkHttpClient;

public class OkHttpProvider implements KsvcNetworkProvider {

    private OkHttpClient client;

    public OkHttpProvider(KsvcHttpClientConfig config) {

    }

    @Override
    public void initProvider() {
        client = new OkHttpClient();
    }

    @Override
    public void asyncExec(KsvcHttpRequest request, KsvcHttpCallback callback) {

    }

    @Override
    public void syncExec(KsvcHttpRequest request, KsvcHttpCallback callback) {

    }
}

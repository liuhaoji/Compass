package com.lenovo.compass.compass.net.provider;


import com.lenovo.compass.compass.net.request.KsvcHttpRequest;
import com.lenovo.compass.compass.net.response.KsvcHttpCallback;

public interface KsvcNetworkProvider {
    void initProvider();
    void asyncExec(KsvcHttpRequest request, KsvcHttpCallback callback);
    void syncExec(KsvcHttpRequest request, KsvcHttpCallback callback);
}

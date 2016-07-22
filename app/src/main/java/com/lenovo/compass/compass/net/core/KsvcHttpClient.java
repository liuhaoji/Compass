package com.lenovo.compass.compass.net.core;


import com.lenovo.compass.compass.net.provider.KsvcNetworkProvider;
import com.lenovo.compass.compass.net.provider.OkHttpProvider;
import com.lenovo.compass.compass.net.provider.VolleyProvider;
import com.lenovo.compass.compass.net.provider.VolleyWithOkHttpProvider;
import com.lenovo.compass.compass.net.request.KsvcHttpRequest;
import com.lenovo.compass.compass.net.response.KsvcHttpCallback;

public class KsvcHttpClient {

    private static KsvcHttpClient mInstance;
    private KsvcNetworkProvider mProvider;

    private KsvcHttpClient(KsvcHttpClientConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("Provider should not be null");
        }
        setConfiguration(config);
    }

    public static KsvcHttpClient getInstance() {
        if (mInstance == null) {
            mInstance = new KsvcHttpClient(KsvcHttpClientConfig.getDefaultConfig());
        }
        return mInstance;
    }

    public void asyncExec(KsvcHttpRequest request, KsvcHttpCallback callback) {
        mProvider.asyncExec(request, callback);
    }

    public void syncExec(KsvcHttpRequest request, KsvcHttpCallback callback) {
        mProvider.syncExec(request, callback);
    }

    public void setConfiguration(KsvcHttpClientConfig config) {
        if (mProvider != null) {
            mProvider = null;
        }
        switch (config.getProviderType()) {
            case KsvcHttpClientConfig.HTTP_PROVIDER_TYPE_VOLLEY:
                mProvider = new VolleyProvider(config);
                break;
            case KsvcHttpClientConfig.HTTP_PROVIDER_TYPE_OKHTTP:
                mProvider = new OkHttpProvider(config);
                break;
            case KsvcHttpClientConfig.HTTP_PROVIDER_TYPE_VOLLEY_WITH_OKHTTP:
                mProvider = new VolleyWithOkHttpProvider(config);
                break;
        }
        if (mProvider != null) {
            mProvider.initProvider();
        }
    }

    public KsvcNetworkProvider getHttpProvider() {
        return mProvider;
    }

}

package com.lenovo.compass.compass.net.core;

public class KsvcHttpClientConfig {
    public static final int HTTP_PROVIDER_TYPE_VOLLEY = 0;
    public static final int HTTP_PROVIDER_TYPE_OKHTTP = 1;
    public static final int HTTP_PROVIDER_TYPE_VOLLEY_WITH_OKHTTP = 2;
    public static final int DEFAULT_PROVIDER_TYPE = HTTP_PROVIDER_TYPE_VOLLEY_WITH_OKHTTP;
    public static final int DEFAULT_CONNECTION_TIMEOUT = 10 * 1000;
    public static final int DEFAULT_SOCKET_TIMEOUT = 10 * 1000;
    public static final int DEFAULT_RETRY_TIMEOUT = 10 * 1000;
    public static final int DEFAULT_RETRY_COUNT = 1;
    public static final int DEFAULT_MAX_CONNECTIONS = 10;
    private int providerType;
    private int connectionTimeout;
    private int socketTimeOut;
    private int retryCount;
    private int retryTimeOut;
    private int maxConnections;
    private static KsvcHttpClientConfig instance = null;

    private KsvcHttpClientConfig() {
    }

    public static KsvcHttpClientConfig getDefaultConfig() {
        if (instance == null) {
            instance = new KsvcHttpClientConfig();
            instance.setProviderType(DEFAULT_PROVIDER_TYPE);
            instance.setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT);
            instance.setSocketTimeOut(DEFAULT_SOCKET_TIMEOUT);
            instance.setRetryCount(DEFAULT_RETRY_COUNT);
            instance.setRetryTimeOut(DEFAULT_RETRY_TIMEOUT);
            instance.setMaxConnections(DEFAULT_MAX_CONNECTIONS);
        }
        return instance;
    }

    public KsvcHttpClientConfig(KsvcHttpClientConfig config) {
        setProviderType(config.providerType);
        setConnectionTimeout(config.connectionTimeout);
        setSocketTimeOut(config.socketTimeOut);
        setRetryCount(config.retryCount);
        setRetryTimeOut(config.retryTimeOut);
        setMaxConnections(config.maxConnections);
    }

    public void setProviderType(int providerType) {
        this.providerType = providerType;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public void setRetryTimeOut(int retryTimeOut) {
        this.retryTimeOut = retryTimeOut;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getProviderType() {
        return providerType;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public int getRetryTimeOut() {
        return retryTimeOut;
    }

    public int getMaxConnections() {
        return maxConnections;
    }
}

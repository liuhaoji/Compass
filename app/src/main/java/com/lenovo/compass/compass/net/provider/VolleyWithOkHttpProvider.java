package com.lenovo.compass.compass.net.provider;


import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.lenovo.compass.compass.CompassApplication;
import com.lenovo.compass.compass.net.core.KsvcHttpClientConfig;
import com.lenovo.compass.compass.net.request.KsvcHttpGetRequest;
import com.lenovo.compass.compass.net.request.KsvcHttpPostRequest;
import com.lenovo.compass.compass.net.request.KsvcHttpRequest;
import com.lenovo.compass.compass.net.request.volley.JsonObjectPostParamRequest;
import com.lenovo.compass.compass.net.response.KsvcHttpCallback;
import com.lenovo.compass.compass.net.util.KsvcHttpError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.OkHttpClient;

public class VolleyWithOkHttpProvider implements KsvcNetworkProvider {
    public static final String DEBUG_TAG = "VolleyWithOkHttp";
    private RequestQueue mRequestQueue;
    private KsvcHttpClientConfig mConfig;

    public VolleyWithOkHttpProvider(KsvcHttpClientConfig config) {
        mConfig = config;
    }

    @Override
    public void initProvider() {
        //For volley + okhttp
        mRequestQueue = Volley.newRequestQueue(CompassApplication.getInstance(), new OkHttp3Stack(new OkHttpClient()));
    }

    @Override
    public void asyncExec(final KsvcHttpRequest request, final KsvcHttpCallback callback) {
        JsonRequest<JSONObject> jsonObjectRequest = null;
        if (request instanceof KsvcHttpGetRequest) {
            // For get request of volley
            jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getUrlWithParam(request.getUrl(), request.getParams(), false), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    callback.onSuccess(response);
                    Log.d(DEBUG_TAG, response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    KsvcHttpError ksvcHttpError;
                    if (error.networkResponse != null) {
                        ksvcHttpError = new KsvcHttpError(error.networkResponse.statusCode, error.networkResponse.data, error.networkResponse.headers);
                    } else {
                        ksvcHttpError = new KsvcHttpError(-1, null, null);
                    }
                    callback.onFailure(ksvcHttpError);
                    Log.d(DEBUG_TAG, error.toString());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return request.getHeaders();
                }

                @Override
                public Object getTag() {
                    return request.getTag();
                }
            };
        } else if (request instanceof KsvcHttpPostRequest) {// For post request of volley
            KsvcHttpPostRequest ksvcHttpPostRequest = (KsvcHttpPostRequest) request;
            String jsonContent = ksvcHttpPostRequest.getContent();
            if (!TextUtils.isEmpty(jsonContent)) {
                // Has content
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(jsonContent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
                jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, request.getUrl(), jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                        Log.d(DEBUG_TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        KsvcHttpError ksvcHttpError;
                        if (error.networkResponse != null) {
                            ksvcHttpError = new KsvcHttpError(error.networkResponse.statusCode, error.networkResponse.data, error.networkResponse.headers);
                        } else {
                            ksvcHttpError = new KsvcHttpError(-1, null, null);
                        }
                        callback.onFailure(ksvcHttpError);
                        Log.d(DEBUG_TAG, error.toString());
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return request.getHeaders();
                    }

                    @Override
                    public Object getTag() {
                        return request.getTag();
                    }
                };
            } else {
                // No content,only param
                jsonObjectRequest = new JsonObjectPostParamRequest(Request.Method.POST, request.getUrl(), getUrlWithParam(request.getUrl(), request.getParams(), true), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccess(response);
                        Log.d(DEBUG_TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        KsvcHttpError ksvcHttpError;
                        if (error.networkResponse != null) {
                            ksvcHttpError = new KsvcHttpError(error.networkResponse.statusCode, error.networkResponse.data, error.networkResponse.headers);
                        } else {
                            ksvcHttpError = new KsvcHttpError(-1, null, null);
                        }
                        callback.onFailure(ksvcHttpError);
                        Log.d(DEBUG_TAG, error.toString());
                    }
                });
            }
        }
        if (jsonObjectRequest != null) {
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(mConfig.getConnectionTimeout(), mConfig.getRetryCount(), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mRequestQueue.add(jsonObjectRequest);
        }
    }

    private String getUrlWithParam(String url, Map<String, String> params, boolean onlyQuery) {
        if (params != null && params.size() > 0) {
            Uri uri = Uri.parse(url);
            Uri.Builder builder = uri.buildUpon();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            if (onlyQuery) {
                return builder.build().getQuery();
            } else {
                return builder.build().toString();
            }
        } else {
            return "";
        }

    }

    @Override
    public void syncExec(KsvcHttpRequest request, KsvcHttpCallback callback) {
        //TODO
    }
}

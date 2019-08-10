package com.jihan.mini_core.net;

import android.content.Context;

import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.IRequest;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.net.callback.RestCallBack;
import com.jihan.mini_core.ui.loader.LoaderStyle;
import com.jihan.mini_core.ui.loader.MiniLoader;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Jihan on 2019/8/9
 */
public class RestClient {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final IRequest IREQUEST;
    private final RequestBody REQUESTBODY;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;

    RestClient(String url,
               WeakHashMap<String, Object> params,
               ISuccess iSuccess,
               IFailure iFailure,
               IError iError,
               IRequest iRequest,
               RequestBody requestBody,
               Context context,
               LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS = params;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.IREQUEST = iRequest;
        this.REQUESTBODY = requestBody;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void onRequest(HttpMethod method) {
        RestService restService = RestCreator.RestServiceHolder.REST_SERVICE;

        Call<String> call = null;

        if (LOADER_STYLE != null) {
            MiniLoader.showLoading(CONTEXT);
        }

        switch (method) {
            case GET:
                call = restService.get(URL, PARAMS);
                break;
            case POST:
                call = restService.post(URL, PARAMS);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRestCallBack());
        }
    }

    private Callback<String> getRestCallBack() {
        return new RestCallBack(ISUCCESS, IFAILURE, IERROR, IREQUEST,LOADER_STYLE);
    }

    public void get() {
        onRequest(HttpMethod.GET);
    }

    public void post() {
        onRequest(HttpMethod.POST);
    }

    public void put() {
        onRequest(HttpMethod.PUT);
    }

    public void delete() {
        onRequest(HttpMethod.DELETE);
    }
}

package com.jihan.mini_core.net;

import android.content.Context;

import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.IRequest;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.loader.LoaderStyle;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Jihan on 2019/8/9
 */
public class RestClientBuilder {

    private String mUrl;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private IRequest mIRequest;
    private RequestBody mRequestBody;
    private WeakHashMap<String, Object> mParams = RestCreator.getParams();
    private Context mContext;
    private LoaderStyle mLoaderStyle;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.mIFailure = failure;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mIError = error;
        return this;
    }

    public final RestClientBuilder request(IRequest request) {
        this.mIRequest = request;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.LineScalePulseOutIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, mParams, mISuccess, mIFailure, mIError, mIRequest, mRequestBody,mContext,mLoaderStyle);
    }
}

package com.jihan.mini_core.net.rx;

import android.content.Context;

import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.RestCreator;
import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.IRequest;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Jihan on 2019/8/9
 */
public class RxRestClientBuilder {

    private String mUrl;
    private RequestBody mRequestBody;
    private File mFile;
    private WeakHashMap<String, Object> mParams = RestCreator.getParams();
    private Context mContext;
    private LoaderStyle mLoaderStyle;
    private String mDownloadDir;
    private String mExtension;
    private String mName;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        mParams.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RxRestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RxRestClientBuilder dirName(String name) {
        this.mName = name;
        return this;
    }


    public final RxRestClientBuilder raw(String raw) {
        this.mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.LineScalePulseOutIndicator;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl, mParams,
                mRequestBody,
                mFile, mDownloadDir, mExtension, mName,
                mContext, mLoaderStyle);
    }
}

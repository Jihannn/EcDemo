package com.jihan.mini_core.net;

import android.content.Context;

import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.IRequest;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.net.callback.RestCallBack;
import com.jihan.mini_core.net.download.DownloadHandler;
import com.jihan.mini_core.ui.loader.LoaderStyle;
import com.jihan.mini_core.ui.loader.MiniLoader;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private final RequestBody BODY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;

    RestClient(String url,
               WeakHashMap<String, Object> params,
               ISuccess iSuccess,
               IFailure iFailure,
               IError iError,
               IRequest iRequest,
               RequestBody requestBody,
               File file,
               String downloadDir,
               String extension,
               String name,
               Context context,
               LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS = params;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.IREQUEST = iRequest;
        this.BODY = requestBody;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
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
            case POST_RAW:
                call = restService.postRaw(URL, BODY);
                break;
            case PUT:
                call = restService.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = restService.putRaw(URL, BODY);
                break;
            case DELETE:
                call = restService.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(
                        MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.
                        createFormData("file", FILE.getName(), requestBody);
                call = restService.upload(URL, body);
                break;
            case DOWNLOAD:
                new DownloadHandler(URL, PARAMS, ISUCCESS, IFAILURE, IERROR, IREQUEST, DOWNLOAD_DIR, EXTENSION, NAME)
                        .handleDownload();
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRestCallBack());
        }
    }

    private Callback<String> getRestCallBack() {
        return new RestCallBack(ISUCCESS, IFAILURE, IERROR, IREQUEST, LOADER_STYLE);
    }

    public void get() {
        onRequest(HttpMethod.GET);
    }

    public void post() {
        if (BODY == null) {
            onRequest(HttpMethod.POST);
        } else {
            if (PARAMS != null) {
                throw new RuntimeException("params must be null");
            }
            onRequest(HttpMethod.POST_RAW);
        }
    }

    public void put() {
        if (BODY == null) {
            onRequest(HttpMethod.PUT);
        } else {
            if (PARAMS != null) {
                throw new RuntimeException("params must be null");
            }
            onRequest(HttpMethod.PUT_RAW);
        }
    }

    public void delete() {
        onRequest(HttpMethod.DELETE);
    }

    public void upload() {
        onRequest(HttpMethod.UPLOAD);
    }

    public void download() {
        onRequest(HttpMethod.DOWNLOAD);
    }
}

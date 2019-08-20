package com.jihan.mini_core.net.rx;

import android.content.Context;

import com.jihan.mini_core.net.HttpMethod;
import com.jihan.mini_core.net.RestClientBuilder;
import com.jihan.mini_core.net.RestCreator;
import com.jihan.mini_core.net.RestService;
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

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Jihan on 2019/8/9
 */
public class RxRestClient {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final RequestBody BODY;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;

    RxRestClient(String url,
                 WeakHashMap<String, Object> params,
                 RequestBody body,
                 File file,
                 String downloadDir,
                 String extension,
                 String name,
                 Context context,
                 LoaderStyle loaderStyle) {
        this.URL = url;
        this.PARAMS = params;
        this.BODY = body;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> onRequest(HttpMethod method) {
        RxRestService restService = RestCreator.RxRestServiceHolder.RX_REST_SERVICE;

        Observable<String> observable = null;

        switch (method) {
            case GET:
                observable = restService.get(URL, PARAMS);
                break;
            case POST:
                observable = restService.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = restService.postRaw(URL, BODY);
                break;
            case PUT:
                observable = restService.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = restService.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = restService.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(
                        MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.
                        createFormData("file", FILE.getName(), requestBody);
                observable = restService.upload(URL, body);
                break;
            default:
                break;
        }
        return observable;
    }


    public Observable<String> get() {
        return onRequest(HttpMethod.GET);
    }

    public Observable<String> post() {
        if (BODY == null) {
            return onRequest(HttpMethod.POST);
        } else {
            if (PARAMS != null) {
                throw new RuntimeException("params must be null");
            }
            return onRequest(HttpMethod.POST_RAW);
        }
    }

    public Observable<String> put() {
        if (BODY == null) {
            return onRequest(HttpMethod.PUT);
        } else {
            if (PARAMS != null) {
                throw new RuntimeException("params must be null");
            }
            return onRequest(HttpMethod.PUT_RAW);
        }
    }

    public Observable<String> delete() {
        return onRequest(HttpMethod.DELETE);
    }

    public Observable<String> upload() {
        return onRequest(HttpMethod.UPLOAD);
    }

    public Observable<ResponseBody> download() {
        return RestCreator.RxRestServiceHolder.RX_REST_SERVICE.download(URL, PARAMS);
    }
}

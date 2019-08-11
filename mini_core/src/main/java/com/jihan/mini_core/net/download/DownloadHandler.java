package com.jihan.mini_core.net.download;

import android.content.Context;
import android.os.AsyncTask;

import com.jihan.mini_core.net.RestCreator;
import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.IRequest;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jihan
 * @date 2019/8/11
 */
public class DownloadHandler {

    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final IRequest IREQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public DownloadHandler(String url,
                           WeakHashMap<String, Object> params,
                           ISuccess iSuccess,
                           IFailure iFailure,
                           IError iError,
                           IRequest iRequest,
                           String downloadDir,
                           String extension,
                           String name) {
        this.URL = url;
        this.PARAMS = params;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.IREQUEST = iRequest;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public void handleDownload() {
        if (IREQUEST != null) {
            IREQUEST.requestStart();
        }
        RestCreator.RestServiceHolder.REST_SERVICE
                .download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            ResponseBody body = response.body();
                            SaveFileTask saveFileTask = new SaveFileTask(ISUCCESS);
                            saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,body,NAME);
                            if(saveFileTask.isCancelled()){
                                if(IREQUEST != null){
                                    IREQUEST.requestEnd();
                                }
                            }
                        }else{
                            if(IERROR != null){
                                IERROR.error(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if(IFAILURE != null){
                            IFAILURE.failure(t.getMessage());
                        }
                    }
                });
    }
}

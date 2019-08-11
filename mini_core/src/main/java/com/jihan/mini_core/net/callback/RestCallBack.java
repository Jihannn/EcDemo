package com.jihan.mini_core.net.callback;

import android.os.Handler;

import com.jihan.mini_core.ui.loader.LoaderStyle;
import com.jihan.mini_core.ui.loader.MiniLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jihan
 * @date 2019/8/10
 */
public class RestCallBack implements Callback<String> {

    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final IRequest IREQUEST;
    private final LoaderStyle LOADERSTYLE;
    private static final Handler HANDLER = new Handler();

    public RestCallBack(ISuccess iSuccess,
                        IFailure iFailure,
                        IError iError,
                        IRequest irequest,
                        LoaderStyle loaderStyle) {
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.IERROR = iError;
        this.IREQUEST = irequest;
        this.LOADERSTYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (ISUCCESS != null) {
                    ISUCCESS.success(response.body());
                }
            }
        } else {
            if (IERROR != null) {
                IERROR.error(response.code(), response.message());
            }
        }
        if (LOADERSTYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MiniLoader.stopLoading();
                }
            }, 2000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE != null) {
            IFAILURE.failure(t.getMessage());
        }

        if (IREQUEST != null) {
            IREQUEST.requestEnd();
        }

        if (LOADERSTYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MiniLoader.stopLoading();
                }
            }, 2000);
        }
    }
}

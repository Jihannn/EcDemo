package com.jihan.myecdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.myecdemo.R;

/**
 * Created by Jihan on 2019/8/9
 */
public class MainFragment extends MiniDelegate {
    private static final String TAG = "MainFragment";

    @Override
    public Object setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testHttp();
    }

    private void testHttp() {
        RestClient.builder()
                .url("http://127.0.0.1/test")
                .success(new ISuccess() {
                    @Override
                    public void success(String response) {
                        Log.d(TAG, "success: " + response);
                        Mini.showToast(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void failure(String content) {
                        Mini.showToast(content);
                    }
                })
                .loader(getContext())
                .build()
                .get();

    }
}

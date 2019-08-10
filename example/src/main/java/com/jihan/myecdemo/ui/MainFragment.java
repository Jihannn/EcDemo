package com.jihan.myecdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
    @Override
    public Object setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testHttp();
    }

    private void testHttp(){
        RestClient.builder()
                .url("https://raw.githubusercontent.com/Snailclimb/JavaGuide/master/.gitattributes")
                .success(new ISuccess() {
                    @Override
                    public void success(String response) {
                        Mini.showToast(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void failure(String msg) {
                        Mini.showToast(msg);
                    }
                })
                .loader(getContext())
                .build()
                .get();

    }
}

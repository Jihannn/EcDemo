package com.jihan.myecdemo;

import android.app.Application;

import com.jihan.mini_core.app.Mini;

/**
 * Created by Jihan on 2019/8/8
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Mini.init(this)
                .finish();
    }
}

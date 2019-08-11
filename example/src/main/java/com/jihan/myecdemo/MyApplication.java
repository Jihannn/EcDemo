package com.jihan.myecdemo;

import android.app.Application;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.net.interceptor.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Jihan on 2019/8/8
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Mini.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withInterceptor(new DebugInterceptor("test",R.raw.test))
                .finish();
    }
}

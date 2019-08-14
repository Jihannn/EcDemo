package com.jihan.myecdemo;

import android.app.Application;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.net.interceptor.DebugInterceptor;
import com.jihan.moni_ec.database.DatabaseManager;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Jihan on 2019/8/8
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Mini.init(this)
                .withApiHost("http://192.168.0.103:8080/")
                .withIcon(new FontAwesomeModule())
                .finish();

        DatabaseManager.getInstance().init(this);
    }
}

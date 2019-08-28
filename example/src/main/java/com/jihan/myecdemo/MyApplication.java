package com.jihan.myecdemo;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.jihan.mini_core.app.Mini;
import com.jihan.moni_ec.database.DatabaseManager;
import com.jihan.myecdemo.event.TestEvent;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Jihan on 2019/8/8
 */
public class MyApplication extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Mini.init(this)
                .withApiHost("https://www.wanandroid.com/")
                .withIcon(new FontAwesomeModule())
//                .withJavaScriptInterface("mini")
//                .withWebEvent("test",new TestEvent())
//                .withWebHost("https://www.baidu.com/")
//                .withInterceptor(new AddCookieInterceptor())
                .finish();

        DatabaseManager.getInstance().init(this);
    }
}

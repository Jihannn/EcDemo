package com.jihan.mini_core.app;

import android.content.Context;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;
import com.jihan.mini_core.util.T;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


/**
 * Created by Jihan on 2019/8/8
 */
public final class Mini {

    public static Configurator init(Context context) {
        getConfigurator()
                .getMiniConfigs()
                .put(ConfigType.APPLICATION_CONTEXT.name()
                        , context.getApplicationContext());
        //Toast初始化
        T.init(context.getApplicationContext());
        //Utils初始化
        Utils.init(context.getApplicationContext());
        //Logger初始化
        Logger.addLogAdapter(new AndroidLogAdapter());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getMiniConfigs(Enum<ConfigType> key){
        return getConfigurator().getMiniConfigs(key);
    }

    public static Context getApplicationContext(){
        return getConfigurator().getMiniConfigs(ConfigType.APPLICATION_CONTEXT);
    }

    public static Handler getHandler(){
        return getMiniConfigs(ConfigType.HANDLER);
    }

    public static void showToast(String content){
        T.showToast(content);
    }

}

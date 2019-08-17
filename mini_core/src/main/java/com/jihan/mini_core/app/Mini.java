package com.jihan.mini_core.app;

import android.content.Context;
import android.os.Handler;

import com.jihan.mini_core.util.T;


/**
 * Created by Jihan on 2019/8/8
 */
public final class Mini {

    public static Configurator init(Context context) {
        getConfigurator()
                .getMiniConfigs()
                .put(ConfigType.APPLICATION_CONTEXT.name()
                        , context.getApplicationContext());
        T.init(context.getApplicationContext());
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

package com.jihan.mini_core.app;

import android.content.Context;

import com.jihan.mini_core.util.T;


/**
 * Created by Jihan on 2019/8/8
 */
public final class Mini {

    public static Configuration init(Context context) {
        getConfiguration()
                .getMiniConfigs()
                .put(ConfigType.APPLICATION_CONTEXT.name()
                        , context.getApplicationContext());
        T.init(context.getApplicationContext());
        return Configuration.getInstance();
    }

    public static Configuration getConfiguration() {
        return Configuration.getInstance();
    }

    public static Context getApplicationContext(){
        return getConfiguration().getMiniConfigs(ConfigType.APPLICATION_CONTEXT);
    }

    public static void showToast(String content){
        T.showToast(content);
    }

}

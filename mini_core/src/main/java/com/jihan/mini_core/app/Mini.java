package com.jihan.mini_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Jihan on 2019/8/8
 */
public final class Mini {

    public static Configuration init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configuration.getInstance();
    }

    private static HashMap<String,Object> getConfigurations(){
        return Configuration.getInstance().getMiniConfigs();
    }
}

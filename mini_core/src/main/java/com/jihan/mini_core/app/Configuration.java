package com.jihan.mini_core.app;

import java.util.WeakHashMap;

/**
 * Created by Jihan on 2019/8/8
 */
public class Configuration {

    private static final WeakHashMap<String,Object> MINI_CONFIGS = new WeakHashMap<>();

    private Configuration(){
        MINI_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    private static class Holder{
        private static final Configuration INSTANCE = new Configuration();
    }

    public static Configuration getInstance(){
        return Holder.INSTANCE;
    }

    public WeakHashMap<String,Object> getMiniConfigs(){
        return MINI_CONFIGS;
    }

    public Configuration withApiHost(String host){
        MINI_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T>T getConfiguration(Enum<ConfigType> key){
        checkFinish();
        return (T) MINI_CONFIGS.get(key);
    }

    public void finish(){
        MINI_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    private void checkFinish(){
        final boolean isReady = (boolean) MINI_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Config is not ready");
        }
    }
}

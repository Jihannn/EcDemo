package com.jihan.mini_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jihan on 2019/8/8
 */
public class Configuration {

    private static final HashMap<String, Object> MINI_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configuration() {
        MINI_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    private static class Holder {
        private static final Configuration INSTANCE = new Configuration();
    }

    static Configuration getInstance() {
        return Holder.INSTANCE;
    }

    HashMap<String, Object> getMiniConfigs() {
        return MINI_CONFIGS;
    }

    public Configuration withApiHost(String host) {
        MINI_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    private void initIcon() {
        if (ICONS.size() > 0){
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public Configuration withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public <T> T getConfiguration(Enum<ConfigType> key) {
        checkFinish();
        return (T) MINI_CONFIGS.get(key.name());
    }

    public void finish() {
        initIcon();
        MINI_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    private void checkFinish() {
        final boolean isReady = (boolean) MINI_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Config is not ready");
        }
    }
}

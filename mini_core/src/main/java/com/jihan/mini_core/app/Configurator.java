package com.jihan.mini_core.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by Jihan on 2019/8/8
 */
public class Configurator {

    private static final HashMap<String, Object> MINI_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    private Configurator() {
        MINI_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        MINI_CONFIGS.put(ConfigType.HANDLER.name(),HANDLER);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public HashMap<String, Object> getMiniConfigs() {
        return MINI_CONFIGS;
    }

    public Configurator withApiHost(String host) {
        MINI_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withWeChatAppId(String appId) {
        MINI_CONFIGS.put(ConfigType.WE_CHAT_APP_ID.name(), appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        MINI_CONFIGS.put(ConfigType.WE_CHAT_APP_SECRET.name(), appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        MINI_CONFIGS.put(ConfigType.ACTIVITY.name(), activity);
        return this;
    }

    private void initIcon() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        MINI_CONFIGS.put(ConfigType.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    public Configurator withInterceptor(List<Interceptor> interceptor) {
        INTERCEPTORS.addAll(interceptor);
        MINI_CONFIGS.put(ConfigType.INTERCEPTOR.name(), INTERCEPTORS);
        return this;
    }

    protected <T> T getMiniConfigs(Enum<ConfigType> key) {
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

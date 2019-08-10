package com.jihan.mini_core.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * @author Jihan
 * @date 2019/8/10
 */
public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(Context context, String type) {
        AVLoadingIndicatorView loadingIndicatorView = new AVLoadingIndicatorView(context);
        Indicator indicator = LOADING_MAP.get(type);
        if (indicator == null) {
            indicator = getIndicator(type);
            if (indicator != null) {
                LOADING_MAP.put(type, indicator);
            }
        }
        loadingIndicatorView.setIndicator(indicator);
        return loadingIndicatorView;
    }

    private static Indicator getIndicator(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!type.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(type);
        try {
            Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

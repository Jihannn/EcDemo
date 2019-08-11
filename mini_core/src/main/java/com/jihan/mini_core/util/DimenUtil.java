package com.jihan.mini_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.jihan.mini_core.app.Mini;

/**
 * @author Jihan
 * @date 2019/8/10
 */
public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Mini.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Mini.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}

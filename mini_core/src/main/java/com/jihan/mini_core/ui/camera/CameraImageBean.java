package com.jihan.mini_core.ui.camera;

import android.net.Uri;

/**
 * @author Jihan
 * @date 2019/8/23
 */
public class CameraImageBean {

    private Uri path = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return path;
    }

    public void setPath(Uri path) {
        this.path = path;
    }
}

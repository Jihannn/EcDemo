package com.jihan.mini_core.ui.camera;

import android.net.Uri;

import com.jihan.mini_core.delegates.BaseDelegate;
import com.jihan.mini_core.util.FileUtil;

/**
 * @author Jihan
 * @date 2019/8/23
 */
public class MiniCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(BaseDelegate delegate){
        new CameraHandler(delegate).showDialog();
    }
}

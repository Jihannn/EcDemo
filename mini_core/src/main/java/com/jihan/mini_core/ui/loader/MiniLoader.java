package com.jihan.mini_core.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.jihan.mini_core.R;
import com.jihan.mini_core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/10
 */
public class MiniLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SIZE = 10;
    private static final String DEFAULT_STYLE = LoaderStyle.LineScalePulseOutIndicator.name();

    private static final List<AppCompatDialog> DIALOGS = new ArrayList<>();


    public static void showLoading(Context context){
        showLoading(context,DEFAULT_STYLE);
    }

    public static void showLoading(Context context, String type){
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        AVLoadingIndicatorView loadingIndicatorView = LoaderCreator.create(context,type);
        dialog.setContentView(loadingIndicatorView);

        final Window dialogWindow = dialog.getWindow();
        final int deviceWidth = DimenUtil.getScreenWidth();
        final int deviceHeight = DimenUtil.getScreenHeight();

        if(dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceHeight / LOADER_OFFSET_SIZE;
            lp.gravity = Gravity.CENTER;
        }

        DIALOGS.add(dialog);
        dialog.show();
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog : DIALOGS) {
            if(dialog != null){
                if(dialog.isShowing()){
                    dialog.cancel();
                }
            }
        }
    }

}

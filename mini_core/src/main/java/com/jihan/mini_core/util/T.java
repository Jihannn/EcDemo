package com.jihan.mini_core.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * @author Jihan
 * @date 2019/8/10
 */
public class T {

    private static Toast mToast;

    @SuppressLint("ShowToast")
    public static void init(Context context){
        mToast = Toast.makeText(context,"",Toast.LENGTH_SHORT);
    }

    public static void showToast(String content){
        mToast.setText(content);
        mToast.show();
    }

}

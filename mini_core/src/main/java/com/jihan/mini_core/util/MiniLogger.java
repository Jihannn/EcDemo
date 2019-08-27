package com.jihan.mini_core.util;

import com.orhanobut.logger.Logger;

/**
 * @author Jihan
 * @date 2019/8/26
 */
public class MiniLogger {

    private static final int DEBUG = 1;
    private static final int INFO = 2;
    private static final int WARN = 3;
    private static final int ERROR = 4;
    private static final int NOTHING = 5;

    private static int LEVEL = DEBUG;

    public static void d(String tag,Object message){
        if(LEVEL <= DEBUG){
            Logger.t(tag).d(message);
        }
    }

    public static void d(Object message){
        if(LEVEL <= DEBUG){
            Logger.d(message);
        }
    }

    public static void i(String tag, String message) {
        if (LEVEL <= INFO) {
            Logger.t(tag).i(message);
        }
    }

    public static void w(String tag, String message) {
        if (LEVEL <= WARN) {
            Logger.t(tag).w(message);
        }
    }

    public static void e(String tag, String message) {
        if (LEVEL <= ERROR) {
            Logger.t(tag).e(message);
        }
    }

    public static void json(String tag, String message) {
        if (LEVEL <= DEBUG) {
            Logger.t(tag).json(message);
        }
    }

}

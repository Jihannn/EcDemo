package com.jihan.mini_core.delegates.web.event;

import android.util.Log;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public class UnDefineEvent extends Event {
    @Override
    public String execute(String params) {
        Log.d("UndefineEvent",params);
        return null;
    }
}

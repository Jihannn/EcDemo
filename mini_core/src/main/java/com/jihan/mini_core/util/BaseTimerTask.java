package com.jihan.mini_core.util;

import java.util.TimerTask;

/**
 * @author Jihan
 * @date 2019/8/12
 */
public class BaseTimerTask extends TimerTask {

    private ITimer mITimerListener;

    public BaseTimerTask(ITimer timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}

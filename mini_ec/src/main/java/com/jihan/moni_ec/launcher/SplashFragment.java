package com.jihan.moni_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jihan.mini_core.app.AccountManager;
import com.jihan.mini_core.app.IUserChecker;
import com.jihan.mini_core.delegates.BaseDelegate;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.ui.launcher.ILauncherFinish;
import com.jihan.mini_core.ui.launcher.LauncherFlags;
import com.jihan.mini_core.util.ITimer;
import com.jihan.mini_core.util.BaseTimerTask;
import com.jihan.mini_core.util.MiniPreference;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;
import com.jihan.moni_ec.sign.SignInFragment;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Jihan
 * @date 2019/8/12
 */
public class SplashFragment extends MiniDelegate implements ITimer {
    private static final String TAG = "SplashFragment";

    @BindView(R2.id.btn_timer)
    Button mBtnSkip;

    @OnClick(R2.id.btn_timer)
    void onClickSkip() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsFirstLaunch();
        }
    }

    private Timer mTimer;
    private int mTimerCount = 5;
    private ILauncherFinish mListener;

    @Override
    public Object setLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherFinish) {
            mListener = (ILauncherFinish) activity;
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }


    public final void checkIsFirstLaunch() {
        if (!MiniPreference.getAppFlag(LauncherFlags.LAUNCH_APP_AGAIN.name())) {
            start(new ScrollFragment());
        } else {
            AccountManager.checkSignIn(new IUserChecker() {
                @Override
                public void isSignIn() {
                    if(mListener !=null){
                        mListener.launcherFinish(LauncherFlags.FINISH_SIGN);
                    }
                }

                @Override
                public void isNotSignIn() {
                    if (mListener != null) {
                        mListener.launcherFinish(LauncherFlags.FINISH_NOT_SIGN);
                    }
                }
            });
        }
    }


    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "onTimer: " + mTimerCount);
                if (mBtnSkip != null) {
                    mBtnSkip.setText(String.format("跳过 (%d)s", mTimerCount));
                    mTimerCount--;
                    if (mTimerCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsFirstLaunch();
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}

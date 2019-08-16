package com.jihan.myecdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.jihan.mini_core.activities.ProxyActivity;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.ui.launcher.ILauncherFinish;
import com.jihan.mini_core.ui.launcher.LauncherFlags;
import com.jihan.moni_ec.launcher.SplashFragment;
import com.jihan.moni_ec.main.EcBottomFragment;
import com.jihan.moni_ec.sign.ISignListener;
import com.jihan.moni_ec.sign.SignInFragment;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherFinish {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public MiniDelegate setRootDelegate() {
        return new SplashFragment();
    }

    @Override
    public void onSignInSuccess() {
        Mini.showToast("登陆成功！");
        startWithPop(new EcBottomFragment());
    }

    @Override
    public void onSignUpSuccess() {
        Mini.showToast("注冊成功！");
        startWithPop(new SignInFragment());
    }

    @Override
    public void launcherFinish(LauncherFlags flags) {
        switch (flags) {
            case FINISH_SIGN:
                startWithPop(new EcBottomFragment());
                break;
            case FINISH_NOT_SIGN:
                startWithPop(new SignInFragment());
                break;
            default:
                break;
        }
    }
}

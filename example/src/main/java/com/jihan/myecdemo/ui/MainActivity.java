package com.jihan.myecdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.jihan.mini_core.activities.ProxyActivity;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.ui.launcher.ILauncherFinish;
import com.jihan.mini_core.ui.launcher.LauncherFlags;
import com.jihan.mini_core.util.MiniLogger;
import com.jihan.moni_ec.launcher.SplashFragment;
import com.jihan.moni_ec.main.EcBottomFragment;
import com.jihan.moni_ec.sign.ISignListener;
import com.jihan.moni_ec.sign.SignInFragment;
import com.jihan.myecdemo.R;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherFinish {

    private static int mTheme = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(mTheme != 0){
            setTheme(mTheme);
        }
        super.onCreate(savedInstanceState);
//        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    public MiniDelegate setRootDelegate() {
        return new SplashFragment();
    }

    @Override
    public void onSignInSuccess() {
        Mini.showToast("登陆成功！");
        getSupportDelegate().replaceFragment(new EcBottomFragment(),false);
    }

    @Override
    public void onSignInFailure() {
        Mini.showToast("登陆失败，请检查下账号信息!");
    }

    @Override
    public void onSignUpSuccess() {
        Mini.showToast("注冊成功！");
        getSupportDelegate().replaceFragment(new SignInFragment(),false);
    }

    @Override
    public void onSignUpFailure() {
        Mini.showToast("账号已被注册！");
    }

    private void changeTheme(){
        mTheme = R.style.AppTheme;
        recreate();
    }

    @Override
    public void launcherFinish(LauncherFlags flags) {
        switch (flags) {
            case FINISH_SIGN:
                getSupportDelegate().replaceFragment(new EcBottomFragment(),false);
                changeTheme();
                break;
            case FINISH_NOT_SIGN:
                getSupportDelegate().replaceFragment(new SignInFragment(),false);
                changeTheme();
                break;
            default:
                break;
        }
    }
}

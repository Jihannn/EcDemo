package com.jihan.moni_ec.sign;

import com.jihan.mini_core.app.AccountManager;

/**
 * @author Jihan
 * @date 2019/8/13
 */
public class SignHandler {

    public static void onSignUp(ISignListener signListener) {
        signListener.onSignUpSuccess();
    }

    public static void onSignIn(ISignListener signListener) {
        AccountManager.setSignInState(true);
        signListener.onSignInSuccess();
    }
}

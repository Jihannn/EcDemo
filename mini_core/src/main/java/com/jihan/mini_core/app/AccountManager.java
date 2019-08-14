package com.jihan.mini_core.app;

import com.jihan.mini_core.util.MiniPreference;

/**
 * @author Jihan
 * @date 2019/8/14
 */
public class AccountManager {

    private enum UserTags {
        IS_SIGN_IN
    }

    public static void setSignInState(boolean state) {
        MiniPreference.setAppFlag(UserTags.IS_SIGN_IN.name(), state);
    }

    private static boolean isSignIn() {
        return MiniPreference.getAppFlag(UserTags.IS_SIGN_IN.name());
    }

    public static void checkSignIn(IUserChecker checker){
        if(isSignIn()) {
            checker.isSignIn();
        }else{
            checker.isNotSignIn();
        }
    }
}

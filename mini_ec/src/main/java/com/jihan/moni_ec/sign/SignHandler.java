package com.jihan.moni_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.app.AccountManager;
import com.jihan.mini_core.ui.loader.MiniLoader;
import com.jihan.mini_core.util.MiniLogger;
import com.jihan.moni_ec.database.DatabaseManager;
import com.jihan.moni_ec.database.UserProfile;
import com.jihan.moni_ec.database.UserProfileDao;

import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/13
 */
public class SignHandler {

    public static void onSignUp(UserProfile user, ISignListener signListener) {
        UserProfileDao userDao = DatabaseManager.getInstance().getUserDao();
        final List<UserProfile> profiles = userDao.queryRaw("where account = ?", user.getAccount());
        if (!profiles.isEmpty()) {
            signListener.onSignUpFailure();
            return;
        }
        userDao.insert(user);
        signListener.onSignUpSuccess();
    }

    public static void onSignIn(UserProfile user, ISignListener signListener) {
        UserProfileDao userDao = DatabaseManager.getInstance().getUserDao();
        final List<UserProfile> profiles = userDao.queryBuilder()
                .where(UserProfileDao.Properties.Account.eq(user.getAccount()))
                .where(UserProfileDao.Properties.PassWord.eq(user.getPassWord()))
                .build().list();
        if (profiles.isEmpty()) {
            signListener.onSignInFailure();
            return;
        }
        AccountManager.setSignInState(true);
        signListener.onSignInSuccess();
    }
}

package com.jihan.moni_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.app.AccountManager;
import com.jihan.mini_core.ui.loader.MiniLoader;
import com.jihan.moni_ec.database.DatabaseManager;
import com.jihan.moni_ec.database.UserProfile;

/**
 * @author Jihan
 * @date 2019/8/13
 */
public class SignHandler {

    public static void onSignUp(String response, ISignListener signListener) {
        JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        long userId = profileJson.getLong("userId");
        String name = profileJson.getString("name");
        String avatar = profileJson.getString("avatar");
        String gender = profileJson.getString("gender");
        String address = profileJson.getString("address");

        UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insertOrReplace(userProfile);

        MiniLoader.stopLoading();
        signListener.onSignUpSuccess();
    }

    public static void onSignIn(String response, ISignListener signListener) {
        JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        long userId = profileJson.getLong("userId");
        String name = profileJson.getString("name");
        String avatar = profileJson.getString("avatar");
        String gender = profileJson.getString("gender");
        String address = profileJson.getString("address");

        UserProfile userProfile = new UserProfile(userId, name, avatar, gender, address);

        //TODO 信息查询

        MiniLoader.stopLoading();
        AccountManager.setSignInState(true);
        signListener.onSignInSuccess();
    }
}

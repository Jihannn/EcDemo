package com.jihan.moni_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.activities.ProxyActivity;
import com.jihan.mini_core.app.IUserChecker;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.RestClientBuilder;
import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;
import com.jihan.moni_ec.configs.ApiConfigs;
import com.jihan.moni_ec.database.UserProfile;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Jihan
 * @date 2019/8/13
 */
public class SignInFragment extends MiniDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEtEmail;

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mEtPassWord;

    private ISignListener mListener;
    private String mAccount;
    private String mPassWord;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url(ApiConfigs.LOGIN)
                    .params("username", mAccount)
                    .params("password", mPassWord)
                    .success(new ISuccess() {
                        @Override
                        public void success(String response) {
                            JSONObject data = JSONObject.parseObject(response);
                            Integer errorCode = data.getInteger("errorCode");
                            String errorMsg = data.getString("errorMsg");
                            if (errorCode != 0) {
                                Mini.showToast("code : " + errorCode + "\nmsg : " + errorMsg);
                            } else {
                                SignHandler.onSignIn(mListener);
                            }
                        }
                    })
                    .loader(getContext())
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void toSignUp() {
        getSupportDelegate().replaceFragment(new SignUpFragment(), false);
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void toWechat() {
        Mini.showToast("微信登录");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ProxyActivity) {
            mListener = (ISignListener) activity;
        }
    }

    private boolean checkForm() {
        final String account = mEtEmail.getText().toString();
        final String passWord = mEtPassWord.getText().toString();

        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(passWord)) {
            Mini.showToast("输入信息不能为空");
            return false;
        }

        mAccount = account;
        mPassWord = passWord;
        return true;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}

package com.jihan.moni_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.activities.ProxyActivity;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
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
public class SignUpFragment extends MiniDelegate {

    @BindView(R2.id.edit_sign_up_account)
    TextInputEditText mEtAccount;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mEtPassWord;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mEtRePassWord;

    private ISignListener mListener;
    private String mAccount = null;
    private String mPassWord = null;
    private String mRePassWord = null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url(ApiConfigs.REGISTER)
                    .params("username", mAccount)
                    .params("password", mPassWord)
                    .params("repassword", mRePassWord)
                    .success(new ISuccess() {
                        @Override
                        public void success(String response) {
                            JSONObject data = JSONObject.parseObject(response);
                            Integer errorCode = data.getInteger("errorCode");
                            String errorMsg = data.getString("errorMsg");
                            if (errorCode != 0) {
                                Mini.showToast("code : " + errorCode + "\nmsg : " + errorMsg);
                            } else {
                                SignHandler.onSignUp(mListener);
                            }
                        }
                    })
                    .loader(getContext())
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void toSignIn() {
        getSupportDelegate().replaceFragment(new SignInFragment(), false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ProxyActivity) {
            mListener = (ISignListener) activity;
        }
    }

    private boolean checkForm() {
        final String account = mEtAccount.getText().toString();
        final String passWord = mEtPassWord.getText().toString();
        final String rePassWord = mEtRePassWord.getText().toString();

        if (TextUtils.isEmpty(account) ||
                TextUtils.isEmpty(passWord) ||
                TextUtils.isEmpty(rePassWord)) {
            Mini.showToast("输入信息不能为空");
            return false;
        }

        if (!passWord.equals(rePassWord)) {
            Mini.showToast("两次密码不相同");
            return false;
        }

        mAccount = account;
        mPassWord = passWord;
        mRePassWord = rePassWord;

        return true;
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}

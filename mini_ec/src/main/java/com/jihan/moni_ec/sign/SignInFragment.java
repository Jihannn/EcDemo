package com.jihan.moni_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;

import com.jihan.mini_core.activities.ProxyActivity;
import com.jihan.mini_core.app.IUserChecker;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.RestClientBuilder;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;

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

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if(checkForm()){
            RestClient.builder()
                    .url("http://192.168.0.103:8080/sign_up")
                    .loader(getContext())
                    .success(new ISuccess() {
                        @Override
                        public void success(String response) {
                            SignHandler.onSignIn(response,mListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void failure(String msg) {

                        }
                    })
                    .build()
                    .get();
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void toSignUp(){
        replaceFragment(new SignUpFragment(),false);
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void toWechat(){
        //TODO 微信登陆
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ProxyActivity){
            mListener = (ISignListener) activity;
        }
    }

    private boolean checkForm(){
        final String email = mEtEmail.getText().toString();
        final String passWord = mEtPassWord.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(passWord)){
            Mini.showToast("输入信息不能为空");
            return false;
        }

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

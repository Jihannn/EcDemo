package com.jihan.moni_ec.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.jihan.mini_core.app.AccountManager;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.moni_ec.interceptor.SaveCookiesInterceptor;
import com.jihan.moni_ec.sign.SignInFragment;

import retrofit2.http.DELETE;

/**
 * @author Jihan
 * @date 2019/8/22
 */
public class PersonalItemClickListener extends SimpleClickListener {

    private final MiniDelegate DELEGATE;

    public PersonalItemClickListener(MiniDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListBean data = (ListBean) adapter.getData().get(position);

        switch (data.getItemType()) {
            case ItemType.ITEM_NORMAL: {
                MiniDelegate delegate = data.getMiniDelegate();
                if (delegate != null) {
                    DELEGATE.getParentDelegate().getSupportDelegate().start(delegate);
                }
                break;
            }
            case ItemType.ITEM_LOGOUT: {
                DELEGATE.getParentDelegate().getSupportDelegate().replaceFragment(new SignInFragment(),false);
                AccountManager.setSignInState(false);
                SaveCookiesInterceptor.clearCookie();
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}

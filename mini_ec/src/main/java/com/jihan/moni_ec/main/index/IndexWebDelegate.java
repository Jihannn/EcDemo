package com.jihan.moni_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.delegates.web.WebDelegateImpl;
import com.jihan.moni_ec.R;

/**
 * @author Jihan
 * @date 2019/8/29
 */
public class IndexWebDelegate extends MiniDelegate {

    private static final String ARG_URL = "arg_url";
    private String mUrl = null;

    @Override
    public Object setLayout() {
        return R.layout.fragment_index_web;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            Bundle args = getArguments();
            mUrl = args.getString(ARG_URL);
        }
    }

    public static IndexWebDelegate newIntent(String url){
        Bundle args = new Bundle();
        args.putString(ARG_URL,url);
        IndexWebDelegate delegate = new IndexWebDelegate();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if(mUrl != null) {
            final WebDelegateImpl webDelegate = WebDelegateImpl.create(mUrl);
            getSupportDelegate().loadRootFragment(R.id.container_web,webDelegate);
        }
    }
}

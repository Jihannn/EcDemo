package com.jihan.moni_ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.delegates.bottom.BottomItemDelegate;
import com.jihan.mini_core.delegates.web.WebDelegate;
import com.jihan.mini_core.delegates.web.WebDelegateImpl;
import com.jihan.moni_ec.R;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public class DiscoverFragment extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.fragment_tab_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebDelegateImpl webDelegate = WebDelegateImpl.create("index.html");
        webDelegate.setTopDelegate(getParentDelegate());
        getSupportDelegate().loadRootFragment(R.id.container_web,webDelegate);
    }
}

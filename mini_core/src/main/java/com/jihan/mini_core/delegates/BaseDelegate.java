package com.jihan.mini_core.delegates;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.jihan.mini_core.activities.ProxyActivity;
import com.jihan.mini_core.ui.launcher.LauncherFlags;
import com.jihan.mini_core.util.MiniPreference;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by Jihan on 2019/8/9
 */
public abstract class BaseDelegate extends SwipeBackFragment {

    private Unbinder mUnBinder = null;

    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;

        Object layoutView = setLayout();
        if (layoutView instanceof Integer) {
            rootView = inflater.inflate((Integer) layoutView, container, false);
        } else if (layoutView instanceof View) {
            rootView = (View) layoutView;
        }

        if (rootView != null) {
            mUnBinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity) _mActivity;
    }

}

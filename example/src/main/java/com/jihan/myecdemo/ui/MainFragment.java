package com.jihan.myecdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jihan.mini_core.app.delegates.MiniDelegate;
import com.jihan.myecdemo.R;

/**
 * Created by Jihan on 2019/8/9
 */
public class MainFragment extends MiniDelegate {
    @Override
    public Object setLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}

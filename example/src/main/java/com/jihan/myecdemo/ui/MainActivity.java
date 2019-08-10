package com.jihan.myecdemo.ui;

import com.jihan.mini_core.activities.ProxyActivity;
import com.jihan.mini_core.delegates.MiniDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public MiniDelegate setRootDelegate() {
        return new MainFragment();
    }

}

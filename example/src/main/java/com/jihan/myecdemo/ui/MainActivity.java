package com.jihan.myecdemo.ui;



import com.jihan.mini_core.app.activities.ProxyActivity;
import com.jihan.mini_core.app.delegates.MiniDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public MiniDelegate setRootDelegate() {
        return new MainFragment();
    }
}

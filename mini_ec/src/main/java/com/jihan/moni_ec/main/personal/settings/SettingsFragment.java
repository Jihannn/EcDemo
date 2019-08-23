package com.jihan.moni_ec.main.personal.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.widgets.StarLayout;
import com.jihan.moni_ec.R;

/**
 * @author Jihan
 * @date 2019/8/22
 */
public class SettingsFragment extends MiniDelegate {
    @Override
    public Object setLayout() {
        return R.layout.fragment_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        rootView.findViewById(R.id.test_star).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StarLayout star = rootView.findViewById(R.id.my_star);
                Mini.showToast("一共得到"+star.getStarCount());
            }
        });
    }
}

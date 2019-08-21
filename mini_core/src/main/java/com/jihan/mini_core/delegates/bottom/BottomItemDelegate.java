package com.jihan.mini_core.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;

/**
 * @author Jihan
 * @date 2019/8/16
 */
public abstract class BottomItemDelegate extends MiniDelegate implements View.OnKeyListener {

    private long mClickTime = 0;
    private static final int COUNT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mClickTime > COUNT_TIME) {
                mClickTime = System.currentTimeMillis();
                Mini.showToast("再点击一次退出应用");
            } else {
                _mActivity.finish();
            }
            return true;
        }
        return false;
    }

    @Override
    public void post(Runnable runnable) {

    }
}

package com.jihan.moni_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jihan.mini_core.app.AccountManager;
import com.jihan.mini_core.app.IUserChecker;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.ui.launcher.ILauncherFinish;
import com.jihan.mini_core.ui.launcher.LauncherFlags;
import com.jihan.mini_core.ui.launcher.LauncherHolderCretor;
import com.jihan.mini_core.util.MiniPreference;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.sign.SignInFragment;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/12
 */
public class ScrollFragment extends MiniDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mBanner = null;
    private static final ArrayList<Integer> IMAGES = new ArrayList<>();
    private ILauncherFinish mListener;

    private void initScroll() {
        IMAGES.add(R.mipmap.launcher_01);
        IMAGES.add(R.mipmap.launcher_02);
        IMAGES.add(R.mipmap.launcher_03);
        IMAGES.add(R.mipmap.launcher_04);
        IMAGES.add(R.mipmap.launcher_05);
        mBanner.setPages(new LauncherHolderCretor(),IMAGES)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherFinish){
            mListener = (ILauncherFinish) activity;
        }
    }

    @Override
    public Object setLayout() {
        mBanner = new ConvenientBanner<>(getContext());
        return mBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initScroll();
    }

    @Override
    public void onItemClick(int position) {
        if(position == IMAGES.size() - 1){
            MiniPreference.setAppFlag(LauncherFlags.LAUNCH_APP_AGAIN.name(),true);

            AccountManager.checkSignIn(new IUserChecker() {
                @Override
                public void isSignIn() {
                    mListener.launcherFinish(LauncherFlags.FINISH_SIGN);
                }

                @Override
                public void isNotSignIn() {
                    if(mListener != null){
                        mListener.launcherFinish(LauncherFlags.FINISH_NOT_SIGN);
                    }
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
    }
}

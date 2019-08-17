package com.jihan.mini_core.delegates;

/**
 * Created by Jihan on 2019/8/9
 */
public abstract class MiniDelegate extends PermissionsCheckerDelegate {

    public <T extends MiniDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }
}

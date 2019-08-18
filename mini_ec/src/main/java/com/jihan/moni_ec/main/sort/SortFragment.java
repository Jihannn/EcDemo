package com.jihan.moni_ec.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.delegates.bottom.BottomItemDelegate;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.main.sort.content.SortContentFragment;
import com.jihan.moni_ec.main.sort.list.MenuListFragment;

/**
 * @author Jihan
 * @date 2019/8/16
 */
public class SortFragment extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.fragment_tab_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getSupportDelegate().loadRootFragment(R.id.container_list,new MenuListFragment());
        getSupportDelegate().loadRootFragment(R.id.container_content,SortContentFragment.newInstance(1));
    }
}

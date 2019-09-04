package com.jihan.moni_ec.main.collect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.delegates.bottom.BottomItemDelegate;
import com.jihan.mini_core.ui.refresh.PagingBean;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;
import com.jihan.moni_ec.configs.ApiConfigs;

import butterknife.BindView;

/**
 * @author Jihan
 * @date 2019/9/4
 */
public class CollectionFragment extends BottomItemDelegate {

    @BindView(R2.id.rv_collect)
    RecyclerView mRecyclerView;

    private CollectionRefreshHandler mRefreshHandler;

    @Override
    public Object setLayout() {
        return R.layout.fragment_tab_collection;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRefreshHandler = CollectionRefreshHandler.create(this, new PagingBean(), mRecyclerView);
        mRefreshHandler.getFirstCollect(ApiConfigs.COLLECTION_LIST);
        mRefreshHandler.loadMore(ApiConfigs.COLLECTION_LIST);
    }
}

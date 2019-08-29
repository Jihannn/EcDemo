package com.jihan.moni_ec.main.index;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.callback.CallBackManager;
import com.jihan.mini_core.callback.CallBackType;
import com.jihan.mini_core.callback.IGlobalCallBack;
import com.jihan.mini_core.delegates.bottom.BottomItemDelegate;
import com.jihan.mini_core.ui.recycler.BaseDecoration;
import com.jihan.mini_core.ui.refresh.PagingBean;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;
import com.jihan.moni_ec.configs.ApiConfigs;
import com.jihan.moni_ec.main.index.refresh.IndexRefreshHandler;
import com.jihan.moni_ec.main.index.search.SearchFragment;
import com.joanzapata.iconify.widget.IconTextView;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Jihan
 * @date 2019/8/16
 */
public class IndexFragment extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecycleView;
    @BindView(R2.id.toolbar_index)
    Toolbar mToolbar;
    @BindView(R2.id.swl_index)
    SwipeRefreshLayout mSwipeRefresh;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan;
    @BindView(R2.id.icon_index_msg)
    IconTextView mIconMsg;
    @BindView(R2.id.et_index_search)
    AppCompatEditText mEtSearch;

    @OnClick(R2.id.icon_index_scan)
    void onClickScan() {
        CallBackManager.getInstance().setCallBack(CallBackType.SCANNER, new IGlobalCallBack<String>() {
            @Override
            public void execute(String args) {
                Mini.showToast(args);
            }
        });
        startScannerWithCheck(this.getParentDelegate());
    }

    private IndexRefreshHandler mIndexRefreshHandler;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRecycleView();
        initRefresh();
        mIndexRefreshHandler.loadBanner(ApiConfigs.MAIN_BANNER);
        mIndexRefreshHandler.firstPage(ApiConfigs.MAIN_DATAS + "/0/json");
        mIndexRefreshHandler.loadMore(ApiConfigs.MAIN_DATAS);
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_tab_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIndexRefreshHandler = IndexRefreshHandler.create(mSwipeRefresh, new PagingBean(), mRecycleView);

        mEtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    getParentDelegate().getSupportDelegate().start(new SearchFragment());
                }
            }
        });
    }

    private void initRefresh() {
        Context context = getContext();
        if (getContext() != null) {
            mSwipeRefresh.setColorSchemeColors(
                    ContextCompat.getColor(context, android.R.color.holo_blue_light),
                    ContextCompat.getColor(context, android.R.color.holo_orange_light),
                    ContextCompat.getColor(context, android.R.color.holo_red_light)
            );
        }
        mSwipeRefresh.setProgressViewOffset(true, 120, 300);
    }

    private void initRecycleView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecycleView.setLayoutManager(manager);
        mRecycleView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(), R.color.index_divider), 5));
//        mRecycleView.addOnItemTouchListener(IndexItemClickListener.create(getParentDelegate()));
    }
}

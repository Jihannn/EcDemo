package com.jihan.mini_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.IFailure;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.recycler.DataConverter;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.mini_core.ui.recycler.MultipleRecyclerAdapter;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/17
 */
public class SwipeRefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout SWIPE_REFRESH;
    private final PagingBean PAGING_BEAN;
    private final RecyclerView RECYCLER_VIEW;
    private final DataConverter CONVERTER;
    private MultipleRecyclerAdapter mRecyclerAdapter = null;
    private String loadMoreUrl = null;

    private SwipeRefreshHandler(SwipeRefreshLayout swipeRefreshLayout,
                                PagingBean pagingBean,
                                RecyclerView recyclerView,
                                DataConverter converter) {
        this.SWIPE_REFRESH = swipeRefreshLayout;
        this.PAGING_BEAN = pagingBean;
        this.RECYCLER_VIEW = recyclerView;
        this.CONVERTER = converter;
        SWIPE_REFRESH.setOnRefreshListener(this);
    }

    public static SwipeRefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,
                                             PagingBean pagingBean,
                                             RecyclerView recyclerView,
                                             DataConverter converter) {
        return new SwipeRefreshHandler(swipeRefreshLayout, pagingBean, recyclerView, converter);
    }

    private void refresh() {
        SWIPE_REFRESH.setRefreshing(true);
        Mini.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO 下拉刷新网络请求
                SWIPE_REFRESH.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    public void firstPage(String url) {
        PAGING_BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void success(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        PAGING_BEAN.setTotal(jsonObject.getInteger("total"))
                                .setPageSize(jsonObject.getInteger("page_size"))
                                .setPageIndex(1);
                        mRecyclerAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mRecyclerAdapter.setOnLoadMoreListener(SwipeRefreshHandler.this, RECYCLER_VIEW);
                        RECYCLER_VIEW.setAdapter(mRecyclerAdapter);
                        PAGING_BEAN.addIndex();
                    }
                })
                .build()
                .get();
    }

    public void loadMorePage(String url) {
        this.loadMoreUrl = url;
    }

    private void loadMore() {
        if (loadMoreUrl == null) {
            mRecyclerAdapter.loadMoreFail();
            return;
        }
        final int pageSize = PAGING_BEAN.getPageSize();
        final int index = PAGING_BEAN.getPageIndex();
        final int currentCount = PAGING_BEAN.getCurrentCount();
        final int itemTotal = PAGING_BEAN.getTotal();

        if(currentCount < itemTotal && index < pageSize){
            Mini.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestClient.builder()
                            .url(loadMoreUrl)
                            .success(new ISuccess() {
                                @Override
                                public void success(String response) {
                                    PAGING_BEAN.addIndex();
                                    PAGING_BEAN.setCurrentCount(mRecyclerAdapter.getData().size());
                                    mRecyclerAdapter.addData(CONVERTER.setJsonData(response).convert());
                                    mRecyclerAdapter.loadMoreComplete();
                                }
                            })
                            .failure(new IFailure() {
                                @Override
                                public void failure(String msg) {
                                    mRecyclerAdapter.loadMoreFail();
                                    Mini.showToast(msg);
                                }
                            })
                            .build()
                            .get();
                }
            },1000);
        }else{
            mRecyclerAdapter.loadMoreEnd(true);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }
}

package com.jihan.moni_ec.main.index.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.refresh.PagingBean;
import com.jihan.moni_ec.main.index.IndexDataAdapter;
import com.jihan.moni_ec.main.index.banner.BannerDataConvert;
import com.jihan.moni_ec.main.index.data.DataEntity;
import com.jihan.moni_ec.main.index.data.IndexDataConvert;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/27
 */
public class IndexRefreshHandler implements SwipeRefreshLayout.OnRefreshListener {

    private final SwipeRefreshLayout SWIPE_REFRESH;
    private final RecyclerView RECYCLER_VIEW;
    private PagingBean mPageBean;
    private IndexDataAdapter mRecyclerAdapter;
    private boolean isLoadMore = true;

    private String mBannerUrl = null;
    private String mFirstUrl = null;
    private boolean isReCreate = false;

    private IndexRefreshHandler(MiniDelegate delegate,
                                SwipeRefreshLayout swipeRefreshLayout,
                                PagingBean pagingBean,
                                RecyclerView recyclerView) {
        this.SWIPE_REFRESH = swipeRefreshLayout;
        this.mPageBean = pagingBean;
        this.RECYCLER_VIEW = recyclerView;
        SWIPE_REFRESH.setOnRefreshListener(this);
        mRecyclerAdapter = new IndexDataAdapter(delegate);
    }

    public static IndexRefreshHandler create(MiniDelegate delegate,
                                             SwipeRefreshLayout swipeRefreshLayout,
                                             PagingBean pagingBean,
                                             RecyclerView recyclerView) {
        return new IndexRefreshHandler(delegate, swipeRefreshLayout, pagingBean, recyclerView);
    }

    @Override
    public void onRefresh() {
        SWIPE_REFRESH.setRefreshing(true);
        isReCreate = true;
        loadBanner(mBannerUrl);
        firstPage(mFirstUrl);
    }

    public void loadBanner(String url) {
        mBannerUrl = url;
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void success(String response) {
                        if(isReCreate){
                            mRecyclerAdapter.clearBanner();
                        }
                        ArrayList<DataEntity> data = new BannerDataConvert().convert(response);
                        mRecyclerAdapter.addBannerData(data);
                    }
                })
                .error(new IError() {
                    @Override
                    public void error(int code, String msg) {
                        Mini.showToast("code :" + code + "\nmsg :" + msg);
                    }
                })
                .build()
                .get();
    }

    public void firstPage(String url) {
        mFirstUrl = url;
        mPageBean.setDelayed(1000);
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void success(String response) {
                        if(isReCreate){
                            mPageBean = new PagingBean();
                            mRecyclerAdapter.clearData();
                            isReCreate = false;
                            SWIPE_REFRESH.setRefreshing(false);
                        }
                        JSONObject jsonObject = JSON.parseObject(response).getJSONObject("data");
                        mPageBean.setTotal(jsonObject.getInteger("total"))
                                .setPageCount(jsonObject.getInteger("pageCount"))
                                .setSize(jsonObject.getInteger("size"))
                                .setCurPage(1);
                        mPageBean.addIndex();

                        mRecyclerAdapter.addData(new IndexDataConvert().convert(response));
                        RECYCLER_VIEW.setAdapter(mRecyclerAdapter);
                    }
                })
                .error(new IError() {
                    @Override
                    public void error(int code, String msg) {
                        Mini.showToast("code :" + code + "\nmsg :" + msg);
                    }
                })
                .build()
                .get();
    }

    public void loadMore(String url) {
        RECYCLER_VIEW.addOnScrollListener(new ScrollStateListener() {
            @Override
            public void loadMore(int itemCount, int lastItem) {
                final int pageCount = mPageBean.getPageCount();
                final int curPage = mPageBean.getCurPage();
                final int total = mPageBean.getTotal();
                if (curPage <= pageCount && itemCount <= total) {
                    RestClient.builder()
                            .url(url + "/" + (curPage + 1) + "/json")
                            .success(new ISuccess() {
                                @Override
                                public void success(String response) {
                                    Mini.getHandler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            mRecyclerAdapter.addData(new IndexDataConvert().convert(response));
                                            mRecyclerAdapter.notifyItemChanged(lastItem);
                                        }
                                    }, 1000);
                                }
                            })
                            .error(new IError() {
                                @Override
                                public void error(int code, String msg) {
                                    Mini.showToast("code : " + code + "\nmsg : " + msg);
                                }
                            })
                            .build()
                            .get();
                } else {
                    Mini.showToast("没有内容可加载了");
                    if (isLoadMore) {
                        mRecyclerAdapter.setLoadMore(false);
                        mRecyclerAdapter.notifyItemRemoved(lastItem);
                        isLoadMore = false;
                    }
                }
            }
        });
    }


}

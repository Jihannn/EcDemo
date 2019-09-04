package com.jihan.moni_ec.main.collect;

import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.IError;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.refresh.PagingBean;
import com.jihan.moni_ec.main.index.refresh.ScrollStateListener;

public class CollectionRefreshHandler{

    private final PagingBean PAGING_BEAN;
    private final RecyclerView RECYCLER_VIEW;
    private CollectionDataAdapter mRecyclerAdapter;
    private boolean isLoadMore = true;

    private CollectionRefreshHandler(MiniDelegate delegate,
                                PagingBean pagingBean,
                                RecyclerView recyclerView) {
        this.PAGING_BEAN = pagingBean;
        this.RECYCLER_VIEW = recyclerView;
        mRecyclerAdapter = new CollectionDataAdapter(delegate);
    }

    public static CollectionRefreshHandler create(MiniDelegate delegate,
                                             PagingBean pagingBean,
                                             RecyclerView recyclerView) {
        return new CollectionRefreshHandler(delegate, pagingBean, recyclerView);
    }

    public void getFirstCollect(String url) {
        PAGING_BEAN.setDelayed(1000);
        RestClient.builder()
                .url(url+"0/json")
                .success(new ISuccess() {
                    @Override
                    public void success(String response) {
                        JSONObject jsonObject = JSON.parseObject(response).getJSONObject("data");
                        PAGING_BEAN.setTotal(jsonObject.getInteger("total"))
                                .setPageCount(jsonObject.getInteger("pageCount"))
                                .setSize(jsonObject.getInteger("size"))
                                .setCurPage(1);
                        PAGING_BEAN.addIndex();

                        mRecyclerAdapter.addData(new CollectionDataConverter().convert(response));
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
                final int pageCount = PAGING_BEAN.getPageCount();
                final int curPage = PAGING_BEAN.getCurPage();
                final int total = PAGING_BEAN.getTotal();
                if (curPage <= pageCount && itemCount <= total) {
                    RestClient.builder()
                            .url(url + (curPage + 1) + "/json")
                            .success(new ISuccess() {
                                @Override
                                public void success(String response) {
                                    Mini.getHandler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            mRecyclerAdapter.addData(new CollectionDataConverter().convert(response));
                                            mRecyclerAdapter.notifyItemChanged(lastItem);
                                        }
                                    },1000);
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
                    if(isLoadMore) {
                        mRecyclerAdapter.setLoadMore(false);
                        mRecyclerAdapter.notifyItemRemoved(lastItem);
                        isLoadMore = false;
                    }
                }
            }
        });
    }
}

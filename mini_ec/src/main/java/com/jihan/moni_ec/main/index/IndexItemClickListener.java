package com.jihan.moni_ec.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.moni_ec.details.GoodsDetailFragment;

/**
 * @author Jihan
 * @date 2019/8/17
 */
public class IndexItemClickListener extends SimpleClickListener {

    private final MiniDelegate DELEGATE;

    private IndexItemClickListener(MiniDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static IndexItemClickListener create(MiniDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DELEGATE.start(new GoodsDetailFragment());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}

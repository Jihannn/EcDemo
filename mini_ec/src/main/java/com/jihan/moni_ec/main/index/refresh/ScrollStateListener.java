package com.jihan.moni_ec.main.index.refresh;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * @author Jihan
 * @date 2019/8/29
 */
public abstract class ScrollStateListener extends RecyclerView.OnScrollListener {

    private boolean isScroll = true;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mItemCount;
    private int mLastItem;

    public abstract void loadMore(int itemCount,int lastItem);

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if(newState == SCROLL_STATE_DRAGGING || newState == SCROLL_STATE_SETTLING){
            isScroll = true;
        }else{
            isScroll = false;
        }
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(isScroll) {
            if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                mLayoutManager = recyclerView.getLayoutManager();
                mItemCount = mLayoutManager.getItemCount();
                mLastItem = ((LinearLayoutManager) mLayoutManager).findLastCompletelyVisibleItemPosition();
            }

            if (mLastItem == mItemCount - 1) {
                loadMore(mItemCount, mLastItem);
            }
        }
    }
}

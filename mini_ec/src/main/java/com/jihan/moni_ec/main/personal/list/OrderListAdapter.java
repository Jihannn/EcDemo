package com.jihan.moni_ec.main.personal.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.jihan.mini_core.ui.recycler.MultipleFields;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.mini_core.ui.recycler.MultipleRecyclerAdapter;
import com.jihan.mini_core.ui.recycler.MultipleViewHolder;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.main.personal.ItemType;

import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/21
 */
public class OrderListAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = RequestOptions
            .diskCacheStrategyOf(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    protected OrderListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.ORDER_ITEM, R.layout.item_order_list);
    }

    @Override
    protected void convert(@NonNull MultipleViewHolder holder, MultipleItemEntity item) {
        switch (holder.getItemViewType()) {
            case ItemType.ORDER_ITEM: {
                final String thumb = item.getField(MultipleFields.THUMB);
                final String title = item.getField(MultipleFields.TITLE);
                final String time = item.getField(MultipleFields.TIME);
                final float price = item.getField(MultipleFields.PRICE);

                final AppCompatImageView mIvThumb = holder.getView(R.id.image_order_list);
                final AppCompatTextView mTvTitle = holder.getView(R.id.tv_order_list_title);
                final AppCompatTextView mTvPrice = holder.getView(R.id.tv_order_list_price);
                final AppCompatTextView mTvTime = holder.getView(R.id.tv_order_list_time);

                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(mIvThumb);

                mTvTitle.setText(title);
                mTvPrice.setText(String.valueOf(price));
                mTvTime.setText(time);
                break;
            }
            default: {
                break;
            }
        }
        super.convert(holder, item);
    }
}

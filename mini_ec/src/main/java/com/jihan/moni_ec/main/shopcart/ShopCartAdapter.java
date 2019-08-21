package com.jihan.moni_ec.main.shopcart;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.mini_core.ui.recycler.MultipleRecyclerAdapter;
import com.jihan.mini_core.ui.recycler.MultipleViewHolder;
import com.jihan.moni_ec.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/20
 */
class ShopCartAdapter extends MultipleRecyclerAdapter {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    private boolean mIsSelected = false;
    private ItemCountListener mListener = null;
    private float mAllItemTotal = 0;

    ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
        setAllItemTotal(data);
    }

    private void setAllItemTotal(List<MultipleItemEntity> data) {
        for (MultipleItemEntity entity : data) {
            final float price = entity.getField(ShopCartFields.PRICE);
            final int count = entity.getField(ShopCartFields.COUNT);
            float total = price * count;
            mAllItemTotal += total;
        }
    }

    public void setAllSelected(boolean isSelected) {
        mIsSelected = isSelected;
    }

    public void setListener(ItemCountListener listener) {
        this.mListener = listener;
    }

    public float getAllItemCount() {
        return mAllItemTotal;
    }

    @Override
    protected void convert(@NonNull MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM: {
                final String thumb = item.getField(ShopCartFields.THUMB);
                final String title = item.getField(ShopCartFields.TITLE);
                final String desc = item.getField(ShopCartFields.DESC);
                final float price = item.getField(ShopCartFields.PRICE);
                final int count = item.getField(ShopCartFields.COUNT);

                final AppCompatImageView mIvThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView mTvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView mTvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView mTvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final AppCompatTextView mTvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView mIcSelected = holder.getView(R.id.icon_item_shop_cart);
                final IconTextView mIcMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView mIcPlus = holder.getView(R.id.icon_item_plus);

                item.setField(ShopCartFields.IS_SELECTED, mIsSelected);

                mTvTitle.setText(title);
                mTvDesc.setText(desc);
                mTvPrice.setText(String.valueOf(price));
                mTvCount.setText(String.valueOf(count));

                if (mIsSelected) {
                    mIcSelected.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                } else {
                    mIcSelected.setTextColor(Color.GRAY);
                }

                mIcSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean isSelected = item.getField(ShopCartFields.IS_SELECTED);
                        if (isSelected) {
                            mIcSelected.setTextColor(Color.GRAY);
                            item.setField(ShopCartFields.IS_SELECTED, false);
                        } else {
                            mIcSelected.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                            item.setField(ShopCartFields.IS_SELECTED, true);
                        }
                    }
                });

                mIcMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = item.getField(ShopCartFields.COUNT);
                        if (count > 1) {
                            --count;
                            mTvCount.setText(String.valueOf(count));
                            item.setField(ShopCartFields.COUNT,count);
                            mAllItemTotal -= price;
                            if (mListener != null) {
                                mListener.itemCountChange(price);
                            }
                        }
                    }
                });

                mIcPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int count = item.getField(ShopCartFields.COUNT);
                        if (count < 99) {
                            ++count;
                            mTvCount.setText(String.valueOf(count));
                            item.setField(ShopCartFields.COUNT,count);
                            mAllItemTotal += price;
                            if (mListener != null) {
                                mListener.itemCountChange(price);
                            }
                        }
                    }
                });

                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(mIvThumb);
                break;
            }
            default: {
                break;
            }
        }
    }
}

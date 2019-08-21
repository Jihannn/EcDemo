package com.jihan.moni_ec.main.shopcart;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ViewStubCompat;
import android.view.View;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.bottom.BottomItemDelegate;
import com.jihan.mini_core.net.RestClient;
import com.jihan.mini_core.net.callback.ISuccess;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Jihan
 * @date 2019/8/20
 */
public class ShopCartFragment extends BottomItemDelegate implements ISuccess,ItemCountListener{

    private ShopCartAdapter mShopCartAdapter = null;
    private int mCurrentCount = 0;
    private boolean isStubInflate = false;

    @BindView(R2.id.rv_shop_cart)
    RecyclerView mRecyclerView = null;

    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView mIcAllSelected = null;

    @BindView(R2.id.stub_no_item)
    ViewStubCompat mViewStub = null;

    @BindView(R2.id.tv_shop_cart_total_price)
    AppCompatTextView mAllItemTotal = null;

    @OnClick(R2.id.icon_shop_cart_select_all)
    void onClickSelectedAll() {
        if (mShopCartAdapter != null) {
            final int tag = (int) mIcAllSelected.getTag();
            if (tag == 1) {
                mIcAllSelected.setTextColor(Color.GRAY);
                mShopCartAdapter.setAllSelected(false);
                mIcAllSelected.setTag(0);
            } else {
                mIcAllSelected.setTextColor(ContextCompat.getColor(getContext(), R.color.app_main));
                mShopCartAdapter.setAllSelected(true);
                mIcAllSelected.setTag(1);
            }
            mShopCartAdapter.notifyItemRangeChanged(0, mShopCartAdapter.getItemCount());
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_remove_selected)
    void onClickDelete() {
        if (mShopCartAdapter != null) {
            final List<MultipleItemEntity> data = mShopCartAdapter.getData();
            final List<MultipleItemEntity> deleteList = new ArrayList<>();
            for (MultipleItemEntity entity : data) {
                boolean isSelected = entity.getField(ShopCartFields.IS_SELECTED);
                if (isSelected) {
                    deleteList.add(entity);
                }
            }
            //TODO 逻辑错误
            for (MultipleItemEntity entity : deleteList) {
                final int position = entity.getField(ShopCartFields.POSITION);
                int removePosition = position - mCurrentCount;
                if (removePosition <= mShopCartAdapter.getItemCount()) {
                    mShopCartAdapter.remove(removePosition);
                    mCurrentCount++;
                    mShopCartAdapter.notifyItemRangeChanged(removePosition, mShopCartAdapter.getItemCount());
                }
            }
            checkItemCount();
        }
    }

    @OnClick(R2.id.tv_top_shop_cart_clear)
    void onClickClear() {
        if (mShopCartAdapter != null) {
            mShopCartAdapter.getData().clear();
            mShopCartAdapter.notifyDataSetChanged();
            checkItemCount();
        }
    }

    @SuppressLint("RestrictedApi")
    private void checkItemCount() {
        int itemCount = mShopCartAdapter.getItemCount();
        if (itemCount == 0) {
            if (!isStubInflate) {
                View stubView = mViewStub.inflate();
                AppCompatTextView tvGoBuy = stubView.findViewById(R.id.stub_tv_go_shop);
                tvGoBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mini.showToast("快去购物吧!");
                    }
                });
                isStubInflate = true;
            }
            mRecyclerView.setVisibility(View.GONE);
            mViewStub.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mViewStub.setVisibility(View.GONE);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_tab_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mIcAllSelected.setTag(0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("cart")
                .loader(getContext())
                .success(this)
                .build()
                .get();
    }

    @Override
    public void success(String response) {
        ArrayList<MultipleItemEntity> entityArrayList = new CartDataConvert().setJsonData(response).convert();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mShopCartAdapter = new ShopCartAdapter(entityArrayList);
        mRecyclerView.setAdapter(mShopCartAdapter);
        mShopCartAdapter.setListener(this);
        mAllItemTotal.setText(String.valueOf(mShopCartAdapter.getAllItemCount()));
        checkItemCount();
    }

    @Override
    public void itemCountChange(float itemTotal) {
        mAllItemTotal.setText(String.valueOf(mShopCartAdapter.getAllItemCount()));
    }
}

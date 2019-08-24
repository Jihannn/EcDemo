package com.jihan.moni_ec.main.index.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;

import com.jihan.mini_core.ui.recycler.ItemType;
import com.jihan.mini_core.ui.recycler.MultipleFields;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.mini_core.ui.recycler.MultipleRecyclerAdapter;
import com.jihan.mini_core.ui.recycler.MultipleViewHolder;
import com.jihan.moni_ec.R;

import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/24
 */
public class SearchAdapter extends MultipleRecyclerAdapter {

    protected SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(@NonNull MultipleViewHolder holder, MultipleItemEntity item) {
        super.convert(holder, item);
        switch (holder.getItemViewType()) {
            case ItemType.SEARCH: {
                final String history = item.getField(MultipleFields.TEXT);
                final AppCompatTextView tvHistory = holder.getView(R.id.tv_search_item);
                tvHistory.setText(history);
                break;
            }
            default: {
                break;
            }
        }
    }
}

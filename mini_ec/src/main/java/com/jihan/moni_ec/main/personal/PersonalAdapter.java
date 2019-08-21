package com.jihan.moni_ec.main.personal;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jihan.moni_ec.R;

import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/21
 */
public class PersonalAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public PersonalAdapter(List<ListBean> data) {
        super(data);
        addItemType(ItemType.NORMAL_ITEM, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, ListBean item) {
        switch (holder.getItemViewType()) {
            case ItemType.NORMAL_ITEM:{
                holder.setText(R.id.tv_arrow_text,item.getText());
                holder.setText(R.id.tv_arrow_value,item.getValue());
                break;
            }
            default:
                break;
        }

    }
}

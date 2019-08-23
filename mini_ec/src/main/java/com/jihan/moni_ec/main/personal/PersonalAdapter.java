package com.jihan.moni_ec.main.personal;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jihan.moni_ec.R;

import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/21
 */
public class PersonalAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public PersonalAdapter(List<ListBean> data) {
        super(data);
        addItemType(ItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ItemType.ITEM_AVATAR, R.layout.arrow_item_avatar);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, ListBean item) {
        switch (holder.getItemViewType()) {
            case ItemType.ITEM_NORMAL: {
                holder.setText(R.id.tv_arrow_text, item.getText());
                holder.setText(R.id.tv_arrow_value, item.getValue());
                break;
            }
            case ItemType.ITEM_AVATAR: {
                Glide.with(mContext)
                        .load(item.getImageUrl())
                        .apply(OPTIONS)
                        .into((ImageView) holder.getView(R.id.img_arrow_avatar));
            }
            default:
                break;
        }

    }
}

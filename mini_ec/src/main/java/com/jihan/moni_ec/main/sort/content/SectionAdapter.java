package com.jihan.moni_ec.main.sort.content;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jihan.moni_ec.R;

import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/18
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();

    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isIsMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SectionBean item) {
        final int goodsId = item.t.getGoodsId();
        final String goodsName = item.t.getGoodsName();
        final String goodsThumb = item.t.getGoodsThumb();
        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv,goodsName);
        final AppCompatImageView goodsImageView = helper.getView(R.id.iv);
        Glide.with(mContext)
                .load(goodsThumb)
                .apply(OPTIONS)
                .into(goodsImageView);
    }
}

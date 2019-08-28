package com.jihan.moni_ec.main.index.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

public class BannerHolderCreator implements CBViewHolderCreator<BannerHolder> {
    @Override
    public BannerHolder createHolder() {
        return new BannerHolder();
    }
}

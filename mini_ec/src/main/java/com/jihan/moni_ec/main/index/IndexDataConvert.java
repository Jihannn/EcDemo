package com.jihan.moni_ec.main.index;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.ui.recycler.DataConverter;
import com.jihan.mini_core.ui.recycler.ItemType;
import com.jihan.mini_core.ui.recycler.MultipleEntityBuilder;
import com.jihan.mini_core.ui.recycler.MultipleFields;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/17
 */
public class IndexDataConvert extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSONObject.parseObject(getJsonData()).getJSONArray("data");
        final ArrayList<MultipleItemEntity> entitys = new ArrayList<>();
        final int size = dataArray.size();

        for (int i = 0; i < size; i++) {
            JSONObject data = dataArray.getJSONObject(i);
            final int goodsId = data.getInteger("goodsId");
            final int spanSize = data.getInteger("spanSize");
            final String text = data.getString("text");
            final String imageUrl = data.getString("imageUrl");
            final JSONArray banners = data.getJSONArray("banners");

            int type = 0;
            final ArrayList<String> bannerImage = new ArrayList<>();
            if (text != null && imageUrl != null) {
                type = ItemType.TEXT_IMAGE;
            } else if (imageUrl != null) {
                type = ItemType.IMAGE;
            } else if (text != null) {
                type = ItemType.TEXT;
            } else if (banners != null) {
                type = ItemType.BANNER;
                int bannerSize = banners.size();
                for (int j = 0; j < bannerSize; j++) {
                    bannerImage.add(banners.getString(j));
                }
            }

            MultipleItemEntity itemEntity = MultipleEntityBuilder.builder()
                    .setItemType(type)
                    .setField(MultipleFields.ID, goodsId)
                    .setField(MultipleFields.SPAN_SIZE, spanSize)
                    .setField(MultipleFields.TEXT, text)
                    .setField(MultipleFields.IMAGE_URL, imageUrl)
                    .setField(MultipleFields.BANNERS, bannerImage)
                    .build();
            entitys.add(itemEntity);
        }

        return entitys;
    }
}

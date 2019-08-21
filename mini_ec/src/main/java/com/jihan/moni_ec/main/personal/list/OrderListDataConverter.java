package com.jihan.moni_ec.main.personal.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.ui.recycler.DataConverter;
import com.jihan.mini_core.ui.recycler.MultipleEntityBuilder;
import com.jihan.mini_core.ui.recycler.MultipleFields;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.moni_ec.main.personal.ItemType;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/21
 */
public class OrderListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();
        final JSONArray dataList = JSON.parseObject(getJsonData()).getJSONArray("data");
        int size = dataList.size();
        for (int i = 0; i < size; i++) {
            JSONObject data = dataList.getJSONObject(i);
            final int id = data.getInteger("id");
            final String thumb = data.getString("thumb");
            final String title = data.getString("title");
            final String time = data.getString("time");
            final float price = data.getFloat("price");

            MultipleItemEntity itemEntity = MultipleEntityBuilder.builder()
                    .setItemType(ItemType.ORDER_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.THUMB, thumb)
                    .setField(MultipleFields.TITLE, title)
                    .setField(MultipleFields.TIME, time)
                    .setField(MultipleFields.PRICE, price)
                    .build();
            ENTITYS.add(itemEntity);
        }

        return ENTITYS;
    }
}

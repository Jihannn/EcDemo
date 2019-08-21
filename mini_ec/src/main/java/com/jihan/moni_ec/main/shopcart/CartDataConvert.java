package com.jihan.moni_ec.main.shopcart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.ui.recycler.DataConverter;
import com.jihan.mini_core.ui.recycler.MultipleEntityBuilder;
import com.jihan.mini_core.ui.recycler.MultipleFields;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;


import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/20
 */
public class CartDataConvert extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();

        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("title");
            final String desc = data.getString("desc");
            final String thumb = data.getString("thumb");
            final int count = data.getInteger("count");
            final float price = data.getFloat("price");

            MultipleItemEntity itemEntity = MultipleEntityBuilder.builder()
                    .setItemType(ShopCartItemType.SHOP_CART_ITEM)
                    .setField(ShopCartFields.ID, id)
                    .setField(ShopCartFields.TITLE, title)
                    .setField(ShopCartFields.DESC, desc)
                    .setField(ShopCartFields.THUMB, thumb)
                    .setField(ShopCartFields.COUNT, count)
                    .setField(ShopCartFields.PRICE, price)
                    .setField(ShopCartFields.IS_SELECTED,false)
                    .setField(ShopCartFields.POSITION,i)
                    .build();

            dataList.add(itemEntity);
        }
        return dataList;
    }
}

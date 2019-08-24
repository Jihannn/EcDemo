package com.jihan.moni_ec.main.index.search;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jihan.mini_core.ui.recycler.DataConverter;
import com.jihan.mini_core.ui.recycler.ItemType;
import com.jihan.mini_core.ui.recycler.MultipleEntityBuilder;
import com.jihan.mini_core.ui.recycler.MultipleFields;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.mini_core.util.MiniPreference;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/24
 */
public class SearchDataConverter extends DataConverter {

    public static final String SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();

        final String jsonData = MiniPreference.getCustomAppProfile(SEARCH_HISTORY);
        if (!TextUtils.isEmpty(jsonData)) {
            final JSONArray dataArray = JSON.parseArray(jsonData);
            final int size = dataArray.size();

            for (int i = 0; i < size; i++) {
                String data = dataArray.getString(i);
                MultipleItemEntity itemEntity = MultipleEntityBuilder.builder()
                        .setItemType(ItemType.SEARCH)
                        .setField(MultipleFields.TEXT, data)
                        .build();
                dataList.add(itemEntity);
            }

        }
        return dataList;
    }
}

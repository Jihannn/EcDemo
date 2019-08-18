package com.jihan.moni_ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/18
 */
public class SortContentConverter {

    final List<SectionBean> convert(String json){
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");

        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            final SectionBean sectionTitleBean = new SectionBean(true, title);;
            sectionTitleBean.setId(id);
            sectionTitleBean.setIsMore(true);
            dataList.add(sectionTitleBean);

            final JSONArray goods = data.getJSONArray("goods");
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodId = contentItem.getInteger("goods_id");
                final String goodName = contentItem.getString("goods_name");
                final String goodThumb = contentItem.getString("goods_thumb");

                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodId);
                itemEntity.setGoodsName(goodName);
                itemEntity.setGoodsThumb(goodThumb);

                dataList.add(new SectionBean(itemEntity));
            }
        }

        return dataList;
    }
}

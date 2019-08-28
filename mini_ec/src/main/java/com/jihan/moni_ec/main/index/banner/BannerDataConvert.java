package com.jihan.moni_ec.main.index.banner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jihan.mini_core.ui.recycler.DataConverter;
import com.jihan.mini_core.ui.recycler.ItemType;
import com.jihan.mini_core.ui.recycler.MultipleEntityBuilder;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.moni_ec.main.index.data.DataEntity;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/27
 */
public class BannerDataConvert {

    public ArrayList<DataEntity> convert(String json) {
        final JSONArray dataArray = JSONObject.parseObject(json).getJSONArray("data");
        final ArrayList<DataEntity> datas = new ArrayList<>();
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String imagePath = data.getString("imagePath");
            final String url = data.getString("url");
            final DataEntity item = new DataEntity();
            item.setBannerImagePath(imagePath)
                    .setBannerOrArticle(DataEntity.BANNER)
                    .setBannerUrl(url);
            datas.add(item);
        }

        return datas;
    }
}

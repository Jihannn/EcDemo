package com.jihan.moni_ec.main.collect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jihan.moni_ec.main.index.data.DataEntity;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/9/4
 */
public class CollectionDataConverter {


    public ArrayList<DataEntity> convert(String json) {

        final JSONArray jsonArray = JSON.parseObject(json).getJSONObject("data").getJSONArray("datas");
        final int size = jsonArray.size();
        final ArrayList<DataEntity> ENTITY = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            final JSONObject data = jsonArray.getJSONObject(i);

            final String author = data.getString("author");
            final String title = data.getString("title");
            final int chapterId = data.getInteger("chapterId");
            final String chapterName = data.getString("chapterName");
            final String link = data.getString("link");
            final String niceDate = data.getString("niceDate");
            final int id = data.getInteger("id");
            final DataEntity item = new DataEntity();

            item.setBannerOrArticle(DataEntity.ARTICLE)
                    .setAuthor(author)
                    .setTitle(title)
                    .setChapterId(chapterId)
                    .setChapterName(chapterName)
                    .setLink(link)
                    .setNiceDate(niceDate)
                    .setId(id);

            ENTITY.add(item);
        }
        return ENTITY;
    }
}

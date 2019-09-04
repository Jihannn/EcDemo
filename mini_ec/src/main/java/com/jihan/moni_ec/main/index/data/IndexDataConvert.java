package com.jihan.moni_ec.main.index.data;

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
public class IndexDataConvert {

    public ArrayList<DataEntity> convert(String json) {

        final JSONArray dataArray = JSONObject.parseObject(json).getJSONObject("data").getJSONArray("datas");
        final ArrayList<DataEntity> entitys = new ArrayList<>();
        final int size = dataArray.size();

        for (int i = 0; i < size; i++) {
            JSONObject data = dataArray.getJSONObject(i);

            final String author = data.getString("author");
            final String title = data.getString("title");
            final int superChapterId = data.getInteger("superChapterId");
            final String superChapterName = data.getString("superChapterName");
            final int chapterId = data.getInteger("chapterId");
            final String chapterName = data.getString("chapterName");
            final String link = data.getString("link");
            final String niceDate = data.getString("niceDate");
            final int id = data.getInteger("id");
            final boolean collect = data.getBoolean("collect");
            final DataEntity item = new DataEntity();

            item.setBannerOrArticle(DataEntity.ARTICLE)
                    .setAuthor(author)
                    .setTitle(title)
                    .setSuperChapterId(superChapterId)
                    .setSuperChapterName(superChapterName)
                    .setChapterId(chapterId)
                    .setChapterName(chapterName)
                    .setLink(link)
                    .setNiceDate(niceDate)
                    .setId(id)
                    .setCollect(collect);

            entitys.add(item);
        }
        return entitys;
    }
}

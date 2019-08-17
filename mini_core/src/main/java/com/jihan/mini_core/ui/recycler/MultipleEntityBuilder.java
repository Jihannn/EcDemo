package com.jihan.mini_core.ui.recycler;

import java.util.LinkedHashMap;

/**
 * @author Jihan
 * @date 2019/8/17
 */
public class MultipleEntityBuilder {

    private final LinkedHashMap<Object,Object> FIELDS = new LinkedHashMap<>();

    MultipleEntityBuilder() {
        FIELDS.clear();
    }

    public static MultipleEntityBuilder builder(){
        return new MultipleEntityBuilder();
    }

    public final MultipleEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFields.ITEM_TYPE,itemType);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }

    public final MultipleEntityBuilder setFields(LinkedHashMap<Object,Object> map){
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build(){
        return new MultipleItemEntity(FIELDS);
    }
}

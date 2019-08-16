package com.jihan.mini_core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * @author Jihan
 * @date 2019/8/16
 */
public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public ItemBuilder addItem(BottomTabBean tab,BottomItemDelegate item){
        ITEMS.put(tab,item);
        return this;
    }

    public ItemBuilder addItem(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }
}

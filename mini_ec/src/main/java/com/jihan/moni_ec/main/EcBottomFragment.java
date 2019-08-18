package com.jihan.moni_ec.main;

import com.jihan.mini_core.delegates.bottom.BaseBottomDelegate;
import com.jihan.mini_core.delegates.bottom.BottomItemDelegate;
import com.jihan.mini_core.delegates.bottom.BottomTabBean;
import com.jihan.mini_core.delegates.bottom.ItemBuilder;
import com.jihan.moni_ec.main.index.IndexFragment;
import com.jihan.moni_ec.main.sort.SortFragment;

import java.util.LinkedHashMap;

/**
 * @author Jihan
 * @date 2019/8/16
 */
public class EcBottomFragment extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"),new IndexFragment());
        items.put(new BottomTabBean("{fa-sort}","分类"),new SortFragment());
        items.put(new BottomTabBean("{fa-compass}","发现"),new IndexFragment());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new IndexFragment());
        items.put(new BottomTabBean("{fa-user}","我的"),new IndexFragment());
        return builder.addItem(items).build();
    }

    @Override
    public int setIndexTab() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return 0;
    }
}

package com.jihan.mini_core.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * @author Jihan
 * @date 2019/8/17
 */
public class BaseDecoration extends DividerItemDecoration {

    private final int COLOR;
    private final int SIZE;

    private BaseDecoration(@ColorInt int color,int size){
        this.COLOR = color;
        this.SIZE = size;
        setDividerLookup(new DividerLookUpImpl());
    }

    public static BaseDecoration create(@ColorInt int color,int size){
        return new BaseDecoration(color,size);
    }

    private class DividerLookUpImpl implements DividerLookup{

        @Override
        public Divider getVerticalDivider(int position) {
            return new Divider.Builder()
                    .color(COLOR)
                    .size(SIZE)
                    .build();
        }

        @Override
        public Divider getHorizontalDivider(int position) {
            return new Divider.Builder()
                    .color(COLOR)
                    .size(SIZE)
                    .build();
        }
    }
}

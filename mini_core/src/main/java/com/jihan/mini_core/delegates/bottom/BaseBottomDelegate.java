package com.jihan.mini_core.delegates.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jihan.mini_core.R;
import com.jihan.mini_core.R2;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Jihan
 * @date 2019/8/16
 */
public abstract class BaseBottomDelegate extends MiniDelegate implements View.OnClickListener {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BottomItemDelegate> ITEMS_FRAGMENT = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    private int mCurrentTab = 0;
    private int mIndexTab = 0;
    private int mClickColor = Color.RED;

    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    public abstract int setIndexTab();

    @ColorInt
    public abstract int setClickColor();

    @BindView(R2.id.ll_bottom_tab_bean)
    LinearLayout mBottomBar;

    @Override
    public Object setLayout() {
        return R.layout.bottom_item_delegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexTab = setIndexTab();
        if (setClickColor() != 0) {
            mClickColor = setClickColor();
        }
        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, BottomItemDelegate> item : ITEMS.entrySet()) {
            TAB_BEANS.add(item.getKey());
            ITEMS_FRAGMENT.add(item.getValue());
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        int size = ITEMS.size();
        for (int i = 0; i < size; i++) {
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_icon_title, mBottomBar);

            final RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);

            final BottomTabBean tabBean = TAB_BEANS.get(i);
            IconTextView icon = (IconTextView) item.getChildAt(0);
            TextView title = (TextView) item.getChildAt(1);
            icon.setText(tabBean.getIcon());
            title.setText(tabBean.getTitle());
            if (i == mIndexTab) {
                icon.setTextColor(mClickColor);
                title.setTextColor(mClickColor);
            }
        }
        final SupportFragment[] delegateArray = ITEMS_FRAGMENT.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.fl_root_fragment, mIndexTab, delegateArray);
    }

    private void resetColor() {
        int count = mBottomBar.getChildCount();
        for (int i = 0; i < count; i++) {
            RelativeLayout item = (RelativeLayout) mBottomBar.getChildAt(i);
            IconTextView icon = (IconTextView) item.getChildAt(0);
            icon.setTextColor(Color.GRAY);
            TextView title = (TextView) item.getChildAt(1);
            title.setTextColor(Color.GRAY);
        }
    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        resetColor();

        RelativeLayout item = (RelativeLayout) v;
        IconTextView icon = (IconTextView) item.getChildAt(0);
        icon.setTextColor(mClickColor);
        TextView title = (TextView) item.getChildAt(1);
        title.setTextColor(mClickColor);

        showHideFragment(ITEMS_FRAGMENT.get(index), ITEMS_FRAGMENT.get(mCurrentTab));
        mCurrentTab = index;
    }
}

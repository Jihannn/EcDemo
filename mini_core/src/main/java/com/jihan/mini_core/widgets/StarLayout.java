package com.jihan.mini_core.widgets;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.jihan.mini_core.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jihan
 * @date 2019/8/22
 */
public class StarLayout extends LinearLayoutCompat implements View.OnClickListener {

    private static final List<IconTextView> STARS = new ArrayList<>();

    private static final CharSequence STAR_NORMAL = "{fa-star-o}";
    private static final CharSequence STAR_SELECT = "{fa-star}";
    private static final int STAR_COUNT = 5;

    public StarLayout(Context context) {
        this(context, null);
    }

    public StarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        for (int i = 0; i < STAR_COUNT; i++) {
            final IconTextView star = new IconTextView(getContext());
            final LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            star.setLayoutParams(params);
            star.setGravity(Gravity.CENTER);
            star.setText(STAR_SELECT);
            star.setTextSize(30);
            star.setTextColor(Color.RED);
            star.setTag(R.id.star_position, i);
            star.setTag(R.id.star_is_select, true);
            star.setOnClickListener(this);
            STARS.add(star);
            this.addView(star);
        }
    }

    private void onClickClose(int position) {
        for (int i = position + 1; i < STAR_COUNT; i++) {
            final IconTextView view = STARS.get(i);
            view.setText(STAR_NORMAL);
            view.setTextColor(Color.GRAY);
            view.setTag(R.id.star_is_select, false);
        }
    }

    private void onClickOpen(int position) {
        for (int i = 0; i <= position; i++) {
            final IconTextView view = STARS.get(i);
            view.setText(STAR_SELECT);
            view.setTextColor(Color.RED);
            view.setTag(R.id.star_is_select, true);
        }
    }

    @Override
    public void onClick(View v) {
        final int position = (int) v.getTag(R.id.star_position);
        final boolean isSelected = (boolean) v.getTag(R.id.star_is_select);
        if (isSelected) {
            onClickClose(position);
        } else {
            onClickOpen(position);
        }
    }

    public int getStarCount() {
        int count = 0;
        for (int i = 0; i < STAR_COUNT; i++) {
            IconTextView view = STARS.get(i);
            boolean isSelected = (boolean) view.getTag(R.id.star_is_select);
            count += isSelected ? 1 : 0;
        }
        return count;
    }
}

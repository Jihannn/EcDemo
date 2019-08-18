package com.jihan.moni_ec.main.sort.list;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.ui.recycler.ItemType;
import com.jihan.mini_core.ui.recycler.MultipleFields;
import com.jihan.mini_core.ui.recycler.MultipleItemEntity;
import com.jihan.mini_core.ui.recycler.MultipleRecyclerAdapter;
import com.jihan.mini_core.ui.recycler.MultipleViewHolder;
import com.jihan.moni_ec.R;
import com.jihan.moni_ec.main.sort.SortFragment;
import com.jihan.moni_ec.main.sort.content.SortContentFragment;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * @author Jihan
 * @date 2019/8/18
 */
public class SortMenuRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortFragment FRAGMENT;
    private int mPrePosition = 0;

    public SortMenuRecyclerAdapter(List<MultipleItemEntity> data, SortFragment sortFragment) {
        super(data);
        this.FRAGMENT = sortFragment;
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu);
    }

    @Override
    protected void convert(@NonNull MultipleViewHolder holder, MultipleItemEntity item) {
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST: {
                final String text = item.getField(MultipleFields.TEXT);
                final boolean isClicked = item.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                name.setText(text);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if(mPrePosition != currentPosition){
                            getData().get(mPrePosition).setField(MultipleFields.TAG,false);
                            notifyItemChanged(mPrePosition);

                            getData().get(currentPosition).setField(MultipleFields.TAG,true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });
                if(!isClicked){
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));
                }else{
                    line.setVisibility(View.VISIBLE);
                    line.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_main));
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                break;
            }
            default: {
                break;
            }
        }
    }

    private void showContent(int contentId){
        SortContentFragment fragment = SortContentFragment.newInstance(contentId);
        switchContent(fragment);
    }

    private void switchContent(SortContentFragment fragment){
        MiniDelegate contentFragment = FRAGMENT.findChildFragment(SortContentFragment.class);
        if(contentFragment != null){
            contentFragment.replaceFragment(fragment,false);
        }
    }
}

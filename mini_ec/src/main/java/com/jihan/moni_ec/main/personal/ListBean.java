package com.jihan.moni_ec.main.personal;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jihan.mini_core.delegates.MiniDelegate;

/**
 * @author Jihan
 * @date 2019/8/21
 */
public class ListBean implements MultiItemEntity {

    private int itemType = 0;
    private String imageUrl = null;
    private String text = null;
    private String value = null;
    private int id = 0;
    private MiniDelegate miniDelegate = null;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;

    public ListBean(int itemType, String imageUrl, String text, String value, int id, MiniDelegate miniDelegate, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.itemType = itemType;
        this.imageUrl = imageUrl;
        this.text = text;
        this.value = value;
        this.id = id;
        this.miniDelegate = miniDelegate;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public String getImageUrl() {
        if (imageUrl == null) {
            return "";
        }
        return imageUrl;
    }

    public String getText() {
        if (text == null) {
            return "";
        }
        return text;
    }

    public String getValue() {
        if (value == null) {
            return "";
        }
        return value;
    }

    public int getId() {
        return id;
    }

    public MiniDelegate getMiniDelegate() {
        return miniDelegate;
    }

    public CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener() {
        return onCheckedChangeListener;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public static final class Builder {

        private int itemType = 0;
        private String imageUrl = null;
        private String text = null;
        private String value = null;
        private int id = 0;
        private MiniDelegate miniDelegate = null;
        private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = null;


        public Builder setItemType(int itemType) {
            this.itemType = itemType;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setMiniDelegate(MiniDelegate miniDelegate) {
            this.miniDelegate = miniDelegate;
            return this;
        }

        public Builder setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.onCheckedChangeListener = onCheckedChangeListener;
            return this;
        }

        public ListBean build() {
            return new ListBean(itemType, imageUrl, text, value, id, miniDelegate, onCheckedChangeListener);
        }
    }
}

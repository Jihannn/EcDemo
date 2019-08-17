package com.jihan.mini_core.ui.recycler;

import java.util.ArrayList;

/**
 * @author Jihan
 * @date 2019/8/17
 */
public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITYS = new ArrayList<>();
    private String mJsonData = null;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String data){
        mJsonData = data;
        return this;
    }

    protected String getJsonData(){
        if(mJsonData == null || mJsonData.isEmpty()){
            throw new NullPointerException("json data is null!");
        }
        return mJsonData;
    }
}

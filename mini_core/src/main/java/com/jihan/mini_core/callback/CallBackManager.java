package com.jihan.mini_core.callback;

import java.util.HashMap;

/**
 * @author Jihan
 * @date 2019/8/23
 */
public class CallBackManager {

    private static final HashMap<Object, IGlobalCallBack> MAP = new HashMap<>();

    private static final class Holder{
        private static final CallBackManager INSTANCE = new CallBackManager();
    }

    public static CallBackManager getInstance(){
        return Holder.INSTANCE;
    }

    public CallBackManager setCallBack(Object tag, IGlobalCallBack callBack){
        MAP.put(tag,callBack);
        return this;
    }

    public IGlobalCallBack getCallBack(Object tag){
        return MAP.get(tag);
    }
}
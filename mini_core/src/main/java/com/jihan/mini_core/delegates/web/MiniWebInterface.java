package com.jihan.mini_core.delegates.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.jihan.mini_core.delegates.web.event.Event;
import com.jihan.mini_core.delegates.web.event.EventManager;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public class MiniWebInterface {
    private final WebDelegate DELEGATE;

    private MiniWebInterface(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    public static MiniWebInterface create(WebDelegate delegate){
        return new MiniWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params){
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if(event != null){
            event.setAction(action);
            event.setContext(DELEGATE.getContext());
            event.setDelegate(DELEGATE);
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }
}

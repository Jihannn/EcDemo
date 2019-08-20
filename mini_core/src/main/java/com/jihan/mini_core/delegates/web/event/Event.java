package com.jihan.mini_core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.jihan.mini_core.delegates.web.WebDelegate;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public abstract class Event implements IEvent {
    private Context context = null;
    private String action = null;
    private WebDelegate delegate = null;
    private String url = null;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public WebDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(WebDelegate delegate) {
        this.delegate = delegate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

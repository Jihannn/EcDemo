package com.jihan.myecdemo.event;

import android.webkit.WebView;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.web.event.Event;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Mini.showToast(params);
        if (getAction().equals("test")){
            WebView webView = getDelegate().getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}

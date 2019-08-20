package com.jihan.mini_core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jihan.mini_core.delegates.web.client.IPageLoader;
import com.jihan.mini_core.delegates.web.client.WebChromeClientImpl;
import com.jihan.mini_core.delegates.web.client.WebViewClientImpl;
import com.jihan.mini_core.delegates.web.route.RouteKeys;
import com.jihan.mini_core.delegates.web.route.Router;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public class WebDelegateImpl extends WebDelegate {

    private IPageLoader mListener = null;

    public void setListener(IPageLoader listener){
        this.mListener = listener;
    }

    public static WebDelegateImpl create(String url) {
        Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if(getUrl() != null){
            Router.getInstance().loadPage(this,getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return WebViewInitializer.createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webViewClient.setListener(mListener);
        return webViewClient;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}

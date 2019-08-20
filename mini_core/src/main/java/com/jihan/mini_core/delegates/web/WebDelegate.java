package com.jihan.mini_core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.jihan.mini_core.app.ConfigType;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.MiniDelegate;
import com.jihan.mini_core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public abstract class WebDelegate extends MiniDelegate implements IWebViewInitializer{

    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mURL = null;
    private boolean mIsWebViewAvailable = true;
    private MiniDelegate mTopDelegate = null;
    
    public WebDelegate() {
    }
    
    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            mURL = args.getString(RouteKeys.URL.name());
        }
        initWebView();
    }
    
    @SuppressLint("JavascriptInterface")
    public void initWebView(){
        if(mWebView != null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }else{
            final IWebViewInitializer initializer = setInitializer();
            if(initializer != null) {
                final WeakReference<WebView> reference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = initializer.initWebView(reference.get());
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                mWebView.addJavascriptInterface(MiniWebInterface.create(this), Mini.getMiniConfigs(ConfigType.JAVASCRIPT_INTERFACE));
                mIsWebViewAvailable = true;
            }else{
                throw new NullPointerException("initializer is null");
            }
        }
    }

    public void setTopDelegate(MiniDelegate delegate){
        mTopDelegate = delegate;
    }

    public MiniDelegate getTopDelegate(){
        return mTopDelegate == null ? this : mTopDelegate;
    }

    public WebView getWebView(){
        if(mWebView == null){
            throw new NullPointerException("WebView is null");
        }else{
            return mIsWebViewAvailable ? mWebView : null;
        }
    }

    public String getUrl(){
        if(mURL == null){
            throw new NullPointerException("URL is null");
        }else{
             return mIsWebViewAvailable ? mURL : null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mWebView != null){
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mWebView != null){
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mWebView != null){
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}


package com.jihan.mini_core.delegates.web.client;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jihan.mini_core.app.ConfigType;
import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.delegates.web.WebDelegate;
import com.jihan.mini_core.delegates.web.route.Router;
import com.jihan.mini_core.ui.loader.MiniLoader;
import com.jihan.mini_core.util.MiniPreference;

import retrofit2.http.DELETE;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebDelegate DELEGATE;
    private IPageLoader mListener = null;

    public void setListener(IPageLoader listener){
        this.mListener = listener;
    }

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }

    public void syncCookie(){
        CookieManager manager = CookieManager.getInstance();

        String webHost = Mini.getMiniConfigs(ConfigType.WEB_HOST);
        if(webHost != null){
            if(manager.hasCookies()){
                String cookieStr = manager.getCookie(webHost);
                if(TextUtils.isEmpty(cookieStr)){
                    MiniPreference.addCustomAppProfile("cookie",cookieStr);
                }
            }
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(mListener != null){
            mListener.pageStart();
        }
        MiniLoader.showLoading(DELEGATE.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        syncCookie();
        if(mListener != null){
            mListener.pageFinish();
        }
        MiniLoader.stopLoading();
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        return super.shouldInterceptRequest(view, request);
    }
}

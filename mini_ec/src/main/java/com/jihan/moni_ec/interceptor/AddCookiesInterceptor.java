package com.jihan.moni_ec.interceptor;

import android.text.TextUtils;

import com.jihan.mini_core.util.MiniPreference;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Jihan
 * @date 2019/8/28
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        String cookie = getCookie(request.url().toString(), request.url().host());
        if (!TextUtils.isEmpty(cookie)) {
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }

    private String getCookie(String url, String domain) {
        String cookies = MiniPreference.getCookies(url);
        if (!TextUtils.isEmpty(url) && !TextUtils.isEmpty(cookies)) {
            return cookies;
        }
        cookies = MiniPreference.getCookies(domain);
        if (!TextUtils.isEmpty(domain) && !TextUtils.isEmpty(cookies)) {
            return cookies;
        }

        return null;
    }
}

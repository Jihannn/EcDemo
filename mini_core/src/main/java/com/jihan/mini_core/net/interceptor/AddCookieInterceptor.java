package com.jihan.mini_core.net.interceptor;

import com.jihan.mini_core.app.Mini;
import com.jihan.mini_core.util.MiniPreference;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Jihan
 * @date 2019/8/19
 */
public class AddCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        Observable
                .just(MiniPreference.getCustomAppProfile("cookie"))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String cookie) throws Exception {
                        Mini.showToast(cookie);
                        builder.addHeader("cookie", cookie);
                    }
                });
        return chain.proceed(builder.build());
    }
}

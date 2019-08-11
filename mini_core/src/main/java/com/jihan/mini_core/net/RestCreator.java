package com.jihan.mini_core.net;

import com.jihan.mini_core.app.ConfigType;
import com.jihan.mini_core.app.Mini;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Jihan on 2019/8/9
 */
public class RestCreator {

    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    public static final class OkHttpClientHolder {
        private static final int TIME_OUT = 10;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private static List<Interceptor> INTERCEPTORS = (ArrayList<Interceptor>) Mini.getConfiguration().getMiniConfigs(ConfigType.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor(){
            if(INTERCEPTORS != null && !INTERCEPTORS.isEmpty()){
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        public static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    public static final class RetrofitHolder {
        private static String BASE_URL = Mini.getConfiguration().getMiniConfigs(ConfigType.API_HOST);
        public static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClientHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public static final class RestServiceHolder {
        public static final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }
}

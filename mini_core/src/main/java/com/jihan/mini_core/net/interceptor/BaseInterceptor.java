package com.jihan.mini_core.net.interceptor;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;

/**
 * @author Jihan
 * @date 2019/8/11
 */
public abstract class BaseInterceptor implements Interceptor {

    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        HttpUrl url = chain.request().url();
        int size = url.querySize();
        LinkedHashMap<String,String> urlParameters = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            urlParameters.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return urlParameters;
    }

    protected String getUrlParameters(Chain chain,String key){
        return getUrlParameters(chain).get(key);
    }

    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        FormBody formBody = (FormBody) chain.request().body();
        int size = formBody.size();
        LinkedHashMap<String,String> bodyParameters = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            bodyParameters.put(formBody.name(i),formBody.value(i));
        }
        return bodyParameters;
    }

    protected String getBodyParameters(Chain chain,String key){
        return getBodyParameters(chain).get(key);
    }
}

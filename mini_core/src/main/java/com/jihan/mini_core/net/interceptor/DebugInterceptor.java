package com.jihan.mini_core.net.interceptor;

import android.support.annotation.RawRes;

import com.jihan.mini_core.util.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Jihan
 * @date 2019/8/11
 */
public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int debugRaw) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRaw;
    }

    private Response debugResponse(Chain chain, @RawRes int rawId){
        String json = FileUtil.getRawFile(rawId);
        return getResponse(chain,json);
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"),json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String url = chain.request().url().toString();
        if(url.contains(DEBUG_URL)){
            return debugResponse(chain,DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}

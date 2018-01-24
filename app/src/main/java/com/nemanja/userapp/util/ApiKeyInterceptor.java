package com.nemanja.userapp.util;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class ApiKeyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key", "1d963d7e34014ee2bafffa360b3a5398")
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}

package com.vivy.docsearch.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This okhttp interceptor is responsible for adding the common query parameters and headers
 * for every service calls
 */
@Singleton
public class RequestInterceptor implements Interceptor {
    private String host;
    private String scheme;

    @Inject
    public RequestInterceptor() {
        // Intentionally blank
    }

    public void setInterceptor(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        scheme = httpUrl.scheme();
        host = httpUrl.host();
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        if (scheme != null && host != null) {
            HttpUrl newUrl = original.url().newBuilder()
                    .scheme(scheme)
                    .host(host)
                    .build();
            original = original.newBuilder()
                    .url(newUrl)
                    .addHeader("Accept", "application/json")
                    .build();
        }
        return chain.proceed(original);
    }

    public String getHost() {
        return host;
    }

    public String getScheme() {
        return scheme;
    }
}
package com.jeanboy.manager.net;

import com.jeanboy.app.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Next on 2016/7/13.
 */
public class OkHttpHelper {

    private final static int CONNECT_TIMEOUT = 30;

    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
        return mOkHttpClient;
    }
}

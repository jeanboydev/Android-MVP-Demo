package com.jeanboy.app.mvpdemo.component.handler;

import android.annotation.SuppressLint;

import com.jeanboy.app.mvpdemo.BuildConfig;
import com.jeanboy.app.mvpdemo.component.handler.converters.FastJsonConverterFactory;
import com.jeanboy.lib.common.manager.net.NetManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by jeanboy on 2017/5/17.
 */

public class OkHttpManager {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private final static int CONNECT_TIMEOUT = 30;


    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static OkHttpManager instance = new OkHttpManager();
    }

    public static OkHttpManager getInstance() {
        return OkHttpManager.SingletonHolder.instance;
    }

    private OkHttpManager() {
    }

    public <T> T create(Class<T> clazz) {
        return getRetrofit().create(clazz);
    }

    /*------------------------配置环境------------------------------*/

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            if (mOkHttpClient == null) {
                mOkHttpClient = getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ?
                HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
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

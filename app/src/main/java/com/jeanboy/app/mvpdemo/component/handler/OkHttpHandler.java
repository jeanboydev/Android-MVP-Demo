package com.jeanboy.app.mvpdemo.component.handler;

import android.content.Context;
import android.text.TextUtils;

import com.jeanboy.lib.common.BuildConfig;
import com.jeanboy.lib.common.manager.net.RequestCallback;
import com.jeanboy.lib.common.manager.net.NetHandler;
import com.jeanboy.lib.common.manager.net.converters.FastJsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by jeanboy on 2017/2/10.
 */

public class OkHttpHandler implements NetHandler {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;
    private final static int CONNECT_TIMEOUT = 30;

    @Override
    public void init(Context context) {
        mRetrofit = getRetrofit(getBaseUrl());
    }

    @Override
    public String getBaseUrl() {
        return null;
    }

    @Override
    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

    @Override
    public <T> void doBack(Call<T> call, final RequestCallback<T> callback) {
        call.enqueue(new retrofit2.Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    callback.success(response);
                } else {
                    String msg = null;
                    try {
                        msg = response.errorBody().string();// TODO:处理自定义错误信息
                    } catch (IOException e) {
                        callback.error(e.getMessage());
                    }
                    if (TextUtils.isEmpty(msg)) {
                        msg = response.message();
                    }
                    callback.error(msg);
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                callback.error(t.getMessage());
            }
        });
    }

    @Override
    public <T> void doSyncBack(Call<T> call, RequestCallback<T> callback) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                callback.success(response);
            } else {
                String msg = null;
                try {
                    msg = response.errorBody().string();// TODO:处理自定义错误信息
                } catch (IOException e) {
                    callback.error(e.getMessage());
                }
                if (TextUtils.isEmpty(msg)) {
                    msg = response.message();
                }
                callback.error(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.error(e.getMessage());
        }
    }


    /*------------------------配置环境------------------------------*/

    private Retrofit getRetrofit(String baseUrl) {
        if (mRetrofit == null) {
            if (mOkHttpClient == null) {
                mOkHttpClient = getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(FastJsonConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    private OkHttpClient getOkHttpClient() {
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

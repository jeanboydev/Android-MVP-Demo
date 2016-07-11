package com.jeanboy.manager.net;

import com.jeanboy.app.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by Next on 2016/7/4.
 */
public class NetManager {

    private final static int CONNECT_TIMEOUT = 30;

    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient httpClient;

    private Retrofit retrofit;

    private static NetManager instance;

    public static NetManager getInstance() {
        if (instance == null) {
            instance = new NetManager();
        }
        return instance;
    }

    public void init(String baseUrl) {
        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
//                .addNetworkInterceptor(mTokenInterceptor)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
//                .addCallAdapterFactory()
//                  .addConverterFactory()
                .build();
    }
}

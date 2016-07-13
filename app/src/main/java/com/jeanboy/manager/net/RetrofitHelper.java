package com.jeanboy.manager.net;

import com.jeanboy.manager.net.retrofit.converters.FastJsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Next on 2016/7/13.
 */
public class RetrofitHelper {


    private static Retrofit mRetrofit;

    private static OkHttpClient httpClient;

    public static Retrofit getRetrofit(String baseUrl) {
        if (mRetrofit == null) {
            if (httpClient == null) {
                httpClient = OkHttpHelper.getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(FastJsonConverterFactory.create())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(httpClient)
                    .build();
        }
        return mRetrofit;
    }
}

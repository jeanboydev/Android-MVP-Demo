package com.jeanboy.manager.net;

import retrofit2.Retrofit;

/**
 * Created by Next on 2016/7/4.
 */
public class NetManager {


    private static Retrofit mRetrofit;

    private static NetManager instance = null;

    private NetManager() {
    }

    public static NetManager getInstance() {
        if (instance == null) {
            synchronized (NetManager.class) {
                if (instance == null) {
                    instance = new NetManager();
                }
            }
        }
        return instance;
    }

    public void init(String baseUrl) {
        mRetrofit = RetrofitHelper.getRetrofit(baseUrl);
    }


    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}

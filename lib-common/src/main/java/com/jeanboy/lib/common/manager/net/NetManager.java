package com.jeanboy.lib.common.manager.net;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/2/10.
 */

public class NetManager {

    private Context context;

    private NetHandler netHandler;

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static NetManager instance = new NetManager();
    }

    public static NetManager getInstance() {
        return SingletonHolder.instance;
    }

    private NetManager() {
    }

    public void build(@NonNull Context context, @NonNull NetHandler netHandler) {
        this.context = context.getApplicationContext();
        this.netHandler = netHandler;
        this.netHandler.init(this.context);
    }

    public <T> T create(Class<T> clazz) {
        return this.netHandler.create(clazz);
    }

    public <T> void doBack(Call<T> call, RequestCallback<T> callback) {
        this.netHandler.doBack(call, callback);
    }

    public <T> void doSyncBack(Call<T> call, RequestCallback<T> callback) {
        this.netHandler.doSyncBack(call, callback);
    }
}

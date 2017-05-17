package com.jeanboy.lib.common.manager.net;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

/**
 * Created by jeanboy on 2017/2/10.
 */

public class NetManager {

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static NetManager instance = new NetManager();
    }

    public static NetManager getInstance() {
        return SingletonHolder.instance;
    }

    private NetManager() {
    }

    public <P, T> void doBack(@NonNull NetHandler<P, T> handler,
                              @NonNull RequestParams<P> request, RequestCallback<ResponseData<T>> callback) {
        Preconditions.checkNotNull(handler);
        Preconditions.checkNotNull(request);
        handler.doBack(request, callback);
    }

    public <P, T> void doSync(@NonNull NetHandler<P, T> handler,
                              @NonNull RequestParams<P> request, RequestCallback<ResponseData<T>> callback) {
        Preconditions.checkNotNull(handler);
        Preconditions.checkNotNull(request);
        handler.doSync(request, callback);
    }
}

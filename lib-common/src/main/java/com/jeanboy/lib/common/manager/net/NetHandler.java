package com.jeanboy.lib.common.manager.net;

/**
 * Created by jeanboy on 2017/2/10.
 */

public abstract class NetHandler<P extends NetHandler.RequestParams, B extends NetHandler.ResponseData> {

    public abstract String getBaseUrl();

    public abstract <T> T create(Class<T> clazz);

    public abstract void doBack(P requestValues, RequestCallback<B> callback);

    public abstract void doSyncBack(P requestValues, RequestCallback<B> callback);

    public interface RequestParams {
    }

    public interface ResponseData {
    }

}

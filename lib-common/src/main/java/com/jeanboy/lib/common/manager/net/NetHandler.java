package com.jeanboy.lib.common.manager.net;

import android.content.Context;

import retrofit2.Call;

/**
 * Created by jeanboy on 2017/2/10.
 */

public interface NetHandler {

    void init(Context context);

    String getBaseUrl();

    /*根据使用的网络库修改以下方法参数*/

    <T> T create(Class<T> clazz);

    <T> void doBack(Call<T> call, final RequestCallback<T> callback);

    <T> void doSyncBack(Call<T> call, RequestCallback<T> callback);
}

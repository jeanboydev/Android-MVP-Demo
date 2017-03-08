package com.jeanboy.lib.common.manager.net;

import retrofit2.Response;

/**
 * Created by Next on 2016/7/13.
 */
public interface RequestCallback<T> {

    void success(Response<T> response);

    void error(String msg);
}

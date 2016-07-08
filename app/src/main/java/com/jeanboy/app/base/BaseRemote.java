package com.jeanboy.app.base;

import java.util.List;

/**
 * Created by Next on 2016/7/8.
 */
public interface BaseRemote {

    interface BaseBack {

        void success();

        void error(String msg);
    }

    interface GetBack<T> {

        void success(T t);

        void error(String msg);
    }

    interface LoadBack<T> {

        void success(List<T> t);

        void error(String msg);
    }
}

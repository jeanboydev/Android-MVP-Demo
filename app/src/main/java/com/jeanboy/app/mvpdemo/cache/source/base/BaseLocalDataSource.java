package com.jeanboy.app.mvpdemo.cache.source.base;

import com.jeanboy.app.mvpdemo.cache.source.callback.SourceCallback;

import java.util.List;

/**
 * Created by jeanboy on 2017/3/13.
 */

public interface BaseLocalDataSource<T> {

    void save(T t);

    void get(Long id, SourceCallback<T> callback);

    void getAll(SourceCallback<List<T>> callback);

    void delete(T t);

    void deleteAll();
}

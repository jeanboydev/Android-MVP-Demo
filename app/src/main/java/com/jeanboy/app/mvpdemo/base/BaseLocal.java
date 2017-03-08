package com.jeanboy.app.mvpdemo.base;

import android.support.annotation.NonNull;

/**
 * Created by Next on 2016/7/5.
 */
public interface BaseLocal<T> {

    boolean save(@NonNull T t);

    boolean delete(@NonNull String id);

    boolean clear();

    boolean update(@NonNull T t);

    T get(@NonNull String id);

//    List<T> load(Page page);

}

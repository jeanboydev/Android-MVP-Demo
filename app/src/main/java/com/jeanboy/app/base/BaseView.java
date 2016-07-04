package com.jeanboy.app.base;

/**
 * Created by Next on 2016/7/4.
 */
public interface BaseView<T> {

    void setPresenter(T presenter);

    void toast(String msg);

    void setLoadingIndicator(boolean active);
}

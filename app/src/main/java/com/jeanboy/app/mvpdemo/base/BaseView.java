package com.jeanboy.app.mvpdemo.base;

/**
 * Created by Next on 2016/7/4.
 */
public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}
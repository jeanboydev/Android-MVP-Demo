package com.jeanboy.app.mvpdemo.domain.base;

/**
 * Created by Next on 2016/7/4.
 */
public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}

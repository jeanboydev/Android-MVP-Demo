package com.jeanboy.app.mvpdemo.domain.base;

import android.content.Context;

/**
 * Created by Next on 2016/7/4.
 */
public interface BaseView {

    Context getContext();

    void showMessage(String msg);

    void showProgress(String msg);

    void dismissProgress();

}
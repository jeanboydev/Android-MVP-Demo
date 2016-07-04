package com.jeanboy.app.base;

import android.app.Application;

import com.jeanboy.common.crash.CrashHandler;

/**
 * Created by Next on 2016/7/4.
 */
public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    public void init() {
        CrashHandler.getInstance().init(this);//初始化crash
    }
}

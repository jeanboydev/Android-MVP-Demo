package com.jeanboy.app.base;

import android.app.Application;

import com.jeanboy.app.config.AppConfig;
import com.jeanboy.common.crash.CrashHandler;
import com.jeanboy.manager.net.NetManager;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Next on 2016/7/4.
 */
public class MainApplication extends Application {

    private static MainApplication instance;

    public static MainApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
    }

    /**
     * MainApplication的初始化建议放到Splash里面
     */
    public void init() {
        CrashHandler.getInstance().init(this);//初始化crash
        FlowManager.init(new FlowConfig.Builder(this).build());//初始化DBFlow
        NetManager.getInstance().init(AppConfig.SERVER_HOST);
    }
}

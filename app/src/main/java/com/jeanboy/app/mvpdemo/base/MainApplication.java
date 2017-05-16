package com.jeanboy.app.mvpdemo.base;

import android.app.Application;
import android.content.Context;

import com.jeanboy.app.mvpdemo.component.handler.GlideHandler;
import com.jeanboy.app.mvpdemo.component.handler.GreenDaoHandler;
import com.jeanboy.app.mvpdemo.component.handler.OkHttpHandler;
import com.jeanboy.lib.common.manager.database.DBManager;
import com.jeanboy.lib.common.manager.images.ImagesManager;
import com.jeanboy.lib.common.manager.net.NetManager;

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
     * 初始化一些工具，耗时操作放到splash中
     */
    public void init(Context context) {
//        //初始化crash
//        CrashHandler.getInstance().init(context.getApplicationContext());
        //数据库
        DBManager.getInstance().build(context.getApplicationContext(), new GreenDaoHandler());
        //图片
        ImagesManager.getInstance().build(context.getApplicationContext(), new GlideHandler());
        //网络
        NetManager.getInstance().build(new OkHttpHandler());
    }


}

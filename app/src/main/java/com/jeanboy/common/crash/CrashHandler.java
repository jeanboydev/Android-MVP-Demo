package com.jeanboy.common.crash;

import android.content.Context;
import android.content.Intent;

import com.jeanboy.app.ui.action.SplashActivity;

/**
 * Created by Next on 2016/7/4.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final String TAG = CrashHandler.class.getSimpleName();
    private static CrashHandler instance = new CrashHandler();
    private Context mContext;
    private Thread.UncaughtExceptionHandler mExceptionHandler;

    public CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return instance;
    }

    public void init(Context mContext) {
        this.mContext = mContext;
        mExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        System.exit(0);
        Intent intent = new Intent(mContext, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

}

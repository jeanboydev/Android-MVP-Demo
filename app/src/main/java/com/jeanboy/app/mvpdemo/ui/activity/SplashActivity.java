package com.jeanboy.app.mvpdemo.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.widget.TextView;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseActivity;
import com.jeanboy.app.mvpdemo.base.MainApplication;
import com.jeanboy.lib.common.utils.AppUtil;

import butterknife.BindView;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.tv_version)
    TextView tv_version;

    private static final int DELAY_MILLIS = 1500;
    private final Handler mHideHandler = new Handler();
    private final Runnable mJumpRunnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            startAwesomeActivity(new Intent(SplashActivity.this, MainActivity.class));
            SplashActivity.this.finish();
        }
    };


    @Override
    public Class getTag(Class clazz) {
        return SplashActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_slpash;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        tv_version.setText(AppUtil.getVersionName(this));
    }

    @Override
    public void initData() {
        MainApplication.getInstance().init(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        delayedHide(DELAY_MILLIS);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mJumpRunnable);
        mHideHandler.postDelayed(mJumpRunnable, delayMillis);
    }

}

package com.jeanboy.app.ui.action;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseActivity;
import com.jeanboy.app.tasks.contract.UserContract;
import com.jeanboy.app.tasks.presenter.UserPresenter;
import com.jeanboy.app.ui.action.login.LoginActivity;


public class MainActivity extends BaseActivity implements UserContract.View {


    private UserContract.Presenter mPresenter;

    @Override
    public Class getTag(Class clazz) {
        return MainActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupView() {
        setSwipeBackEnable(false);
        setTitle("首页");
        mPresenter = new UserPresenter(this);
    }


    @Override
    public void initData() {

    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void toast(String msg) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }


    public void doWork(View v) {
        startActivity(new Intent(this, LoginActivity.class));

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void doAnim(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, v, "robot");
        startActivity(intent, options.toBundle());
    }
}

package com.jeanboy.app.ui.action;

import android.content.Intent;
import android.support.v4.util.Pair;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseActivity;
import com.jeanboy.app.tasks.contract.UserContract;
import com.jeanboy.app.tasks.presenter.UserPresenter;
import com.jeanboy.app.ui.action.info.ListActivity;
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


    public void toList(View v) {
        startAwesomeActivity(new Intent(this, ListActivity.class));
    }


    public void doWork(View v) {
        startAwesomeActivity(new Intent(this, LoginActivity.class));
    }

    public void doAnim(View v) {
        startAwesomeActivity(new Intent(this, LoginActivity.class), Pair.create(v, "robot"));
    }
}

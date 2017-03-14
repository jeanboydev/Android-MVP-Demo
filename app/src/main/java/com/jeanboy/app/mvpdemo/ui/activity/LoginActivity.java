package com.jeanboy.app.mvpdemo.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseActivity;
import com.jeanboy.app.mvpdemo.cache.database.model.UserModel;
import com.jeanboy.app.mvpdemo.domain.module.login.LoginContract;
import com.jeanboy.app.mvpdemo.domain.module.login.LoginPresenter;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;

    private LoginPresenter loginPresenter;

    @Override
    public Class getTag(Class clazz) {
        return LoginActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle("登录").homeAsUp();

        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @Override
    public void initData() {
        loginPresenter.getUserCache();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showProgress(String msg) {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void onLoginSuccessful(UserModel userModel) {
        // TODO: 2017/3/14 登陆成功处理
    }

    @Override
    public void onLoginError() {
        // TODO: 2017/3/14 登陆失败处理
    }

    @Override
    public void showUserCache(UserModel userModel) {
        // TODO: 2017/3/14 显示登录过的用户信息
    }


    public void toLogin(View v) {
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) return;
        loginPresenter.toLogin(username,password);
    }

}

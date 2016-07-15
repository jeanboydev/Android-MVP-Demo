package com.jeanboy.app.ui.action.login;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseActivity;
import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.app.tasks.contract.UserContract;
import com.jeanboy.app.tasks.presenter.UserPresenter;
import com.jeanboy.manager.net.RequestCallback;

import retrofit2.Response;

public class LoginActivity extends BaseActivity implements UserContract.View {


    private UserContract.Presenter mPresenter;

    @Override
    public Class getTag(Class clazz) {
        return LoginActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void setupView() {
        mPresenter = new UserPresenter(this);
        setTitle("登录");
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDestroy() {
        finishAfterTransition();
        super.onDestroy();
    }

    public void toLogin(View v) {
        mPresenter.logIn("", "", new RequestCallback<UserBean>() {
            @Override
            public void success(Response<UserBean> response) {

            }

            @Override
            public void error(String msg) {

            }
        });
    }

    public void getInfo(String id) {
        mPresenter.getInfo(id, new RequestCallback<UserBean>() {
            @Override
            public void success(Response<UserBean> response) {
            }

            @Override
            public void error(String msg) {

            }
        });
    }


}

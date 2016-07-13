package com.jeanboy.app.ui.action.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.app.tasks.contract.UserContract;
import com.jeanboy.app.tasks.presenter.UserPresenter;
import com.jeanboy.manager.net.RequestCallback;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements UserContract.View {


    private UserContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new UserPresenter(this);
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


    @Override
    public void showDialog(String msg) {

    }


    @Override
    public void toast(String msg) {

    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }
}

package com.jeanboy.app.ui.action.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.next.demo.R;
import com.jeanboy.app.base.BaseRemote;
import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.app.tasks.contract.UserContract;
import com.jeanboy.app.tasks.presenter.UserPresenter;

public class LoginActivity extends AppCompatActivity implements UserContract.View {


    private UserContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new UserPresenter(this);
    }

    public void toLogin(View v) {
        mPresenter.logIn("", "", new BaseRemote.GetBack<UserBean>() {
            @Override
            public void success(UserBean userBean) {

            }

            @Override
            public void error(String msg) {

            }
        });
    }

    public void getInfo(String id) {
        mPresenter.getInfo(id, new BaseRemote.GetBack<UserBean>() {
            @Override
            public void success(UserBean userBean) {

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

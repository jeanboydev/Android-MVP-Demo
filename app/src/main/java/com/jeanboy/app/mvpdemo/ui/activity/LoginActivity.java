package com.jeanboy.app.mvpdemo.ui.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseActivity;
import com.jeanboy.app.mvpdemo.common.utils.ToastUtil;
import com.jeanboy.app.mvpdemo.tasks.contract.UserContract;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements UserContract.View {


    private UserContract.Presenter mPresenter;


    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;


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
//        mPresenter = new UserPresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void showDialog(String msg) {

    }


    @Override
    public void toast(String msg) {
        ToastUtil.toast(this, msg);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }


//    public void toLogin(View v) {
//        String username = et_username.getText().toString().trim();
//        String password = et_password.getText().toString().trim();
//        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) return;
//
//        mPresenter.logIn(username, password, new RequestCallback<TokenBean>() {
//            @Override
//            public void success(Response<TokenBean> response) {
//                TokenBean tokenBean = response.body();
//                Log.e(TAG, "==success===" + JSON.toJSONString(tokenBean));
//            }
//
//            @Override
//            public void error(String msg) {
//                Log.e(TAG, "==error===" + msg);
//            }
//        });
//    }
//
//    public void getInfo(String id) {
//        mPresenter.getInfo(id, new RequestCallback<UserBean>() {
//            @Override
//            public void success(Response<UserBean> response) {
//            }
//
//            @Override
//            public void error(String msg) {
//
//            }
//        });
//    }


}

package com.jeanboy.app.ui.action;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.jeanboy.app.R;
import com.jeanboy.app.model.bean.UserBean;
import com.jeanboy.app.tasks.contract.UserContract;
import com.jeanboy.app.tasks.presenter.UserPresenter;
import com.jeanboy.manager.net.RequestCallback;

import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements UserContract.View {


    private UserContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new UserPresenter(this);

    }

    public void doWork(View v) {
//        Call<UserBean> call = ApiManager.getInstance().userApi.getInfo("33");
//        call.enqueue(new Callback<UserBean>() {
//            @Override
//            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
//                if (response.code() >= 400 && response.code() <= 599) {
//                    Log.e("===============", JSON.toJSONString(response.code()));
//                    Log.e("===============", JSON.toJSONString(response.message()));
//                    Log.e("===============", JSON.toJSONString(response.body()));
//
//                    try {
//                        Log.e("===============", response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    Log.e("===============", JSON.toJSONString(response.toString()));
//                } else {
//                    Log.e("===============", JSON.toJSONString(response.code()));
//                    Log.e("===============", JSON.toJSONString(response.message()));
//                    Log.e("===============", JSON.toJSONString(response.body()));
//                    Log.e("===============", JSON.toJSONString(response.errorBody()));
//                    Log.e("===============", JSON.toJSONString(response.toString()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserBean> call, Throwable t) {
//                Log.e("----------------", JSON.toJSONString(t));
//            }
//        });

        mPresenter.logIn("18633664213", "123456", new RequestCallback<UserBean>() {
            @Override
            public void success(Response<UserBean> response) {
                Log.e("===============", JSON.toJSONString(response));
            }

            @Override
            public void error(String msg) {
                Log.e("----------------", msg);
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

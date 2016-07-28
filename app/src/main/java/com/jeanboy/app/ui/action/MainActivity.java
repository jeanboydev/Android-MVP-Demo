package com.jeanboy.app.ui.action;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
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
        startActivity(this,new Intent(this, ListActivity.class));
    }

    public void startActivity(Activity context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(context).toBundle());
        } else {
            context.startActivity(intent);
        }
    }


    public void doWork(View v) {
        startActivity(new Intent(this, LoginActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }

    public void doAnim(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// Android 5.0 使用转场动画
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, v, "robot");
            startActivity(intent, options.toBundle());
        } else {//让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
            startActivity(intent, options.toBundle());
        }
    }
}

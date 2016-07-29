package com.jeanboy.app.ui.action;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.util.Pair;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseActivity;
import com.jeanboy.app.ui.action.info.ListActivity;
import com.jeanboy.app.ui.action.login.LoginActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;


public class MainActivity extends BaseActivity {


    private BottomBar bottomBar;

    @Override
    public Class getTag(Class clazz) {
        return MainActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setTitle("首页");
        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItems(R.menu.bottombar_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        bottomBar.mapColorForTab(0, "#FF9800");
        bottomBar.mapColorForTab(1, 0xFF5D4037);
        bottomBar.mapColorForTab(2, "#7B1FA2");
        bottomBar.mapColorForTab(3, "#FF5252");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
    }

    @Override
    public void initData() {

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

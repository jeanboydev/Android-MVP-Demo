package com.jeanboy.app.mvpdemo.ui.activity;

import android.os.Bundle;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseActivity;

public class InfoActivity extends BaseActivity {

    @Override
    public Class getTag(Class clazz) {
        return InfoActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle("详情").homeAsUp().isTranslucent();
    }

    @Override
    public void initData() {

    }
}

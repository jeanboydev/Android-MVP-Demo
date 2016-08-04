package com.jeanboy.app.ui.action.info;

import android.os.Bundle;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseActivity;

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
        setTitle("详情").homeAsUp().isTransparent();
    }

    @Override
    public void initData() {

    }
}

package com.jeanboy.app.mvpdemo.ui.fragment;


import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseFragment;


public class SettingsFragment extends BaseFragment {


    public SettingsFragment() {
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void setupArguments(Bundle args) {

    }

    @Override
    public void setupView(View view, Bundle savedInstanceState) {
        setToolsBarTitle("设置").isDefault();
    }

    @Override
    public void initData() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isDefault();
        }
    }

}

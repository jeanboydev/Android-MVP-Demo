package com.jeanboy.app.ui.action.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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

    }

    @Override
    public void initData() {

    }

}

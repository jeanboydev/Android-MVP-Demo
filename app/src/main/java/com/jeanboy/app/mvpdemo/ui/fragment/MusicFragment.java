package com.jeanboy.app.mvpdemo.ui.fragment;


import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseFragment;

public class MusicFragment extends BaseFragment {

    public MusicFragment() {
    }

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public void setupArguments(Bundle args) {
    }

    @Override
    public void setupView(View view, Bundle savedInstanceState) {
        setToolsBarTitle("音乐").isDefault();

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

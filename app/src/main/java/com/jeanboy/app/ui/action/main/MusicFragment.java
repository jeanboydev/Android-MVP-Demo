package com.jeanboy.app.ui.action.main;


import android.os.Bundle;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseFragment;


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

    }

    @Override
    public void initData() {

    }

}

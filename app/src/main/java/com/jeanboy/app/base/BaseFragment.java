package com.jeanboy.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeanboy.app.R;
import com.jeanboy.common.utils.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by Next on 2016/8/2.
 */
public abstract class BaseFragment extends Fragment {

    private Toolbar toolbar;


    public abstract int getLayoutId();

    public abstract void setupArguments(Bundle args);

    public abstract void setupView(View view, Bundle savedInstanceState);

    public abstract void initData();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            setupArguments(getArguments());
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupToolbar(view);
        setupView(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void setupToolbar(View view) {
        if (toolbar == null) {
            toolbar = (Toolbar) view.findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle("");
                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            }
        }
    }

    public BaseFragment setTitle(String title) {
        if (toolbar != null) {
            TextView titleView = (TextView) toolbar.findViewById(R.id.toolbar_title);
            if (titleView != null) {
                titleView.setText(title);
            }
        }
        return this;
    }

    public BaseFragment hasMenu() {
        setHasOptionsMenu(true);
        return this;
    }

    public BaseFragment isTransparent() {
        StatusBarUtil.setTranslucent(getActivity());
        return this;
    }
}

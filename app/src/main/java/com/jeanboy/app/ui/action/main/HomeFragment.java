package com.jeanboy.app.ui.action.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseFragment;
import com.jeanboy.app.ui.action.info.InfoActivity;
import com.jeanboy.app.ui.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    public static final String PARAM1 = "param1";

    private String param1;

    @BindView(R.id.list_container)
    RecyclerView list_container;


    private List<String> dataList;
    private ListAdapter listAdapter;

    public HomeFragment() {
    }


    public static HomeFragment newInstance(Bundle args) {
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void setupArguments(Bundle args) {
        param1 = args.getString(PARAM1, null);
    }

    @Override
    public void setupView(View view, Bundle savedInstanceState) {
        setTitle("首页");

        dataList = new ArrayList<>();
        listAdapter = new ListAdapter(dataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list_container.setLayoutManager(layoutManager);
        list_container.setHasFixedSize(true);
        list_container.setAdapter(listAdapter);
        list_container.setItemAnimator(new DefaultItemAnimator());

        listAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                ((MainActivity) getActivity()).startAwesomeActivity(new Intent(getActivity(), InfoActivity.class), Pair.create(v, "info_thumb"));
            }
        });
    }

    @Override
    public void initData() {
        for (int i = 0; i < 20; i++) {
            dataList.add(String.valueOf(i));
        }
        listAdapter.notifyDataSetChanged();
    }


}

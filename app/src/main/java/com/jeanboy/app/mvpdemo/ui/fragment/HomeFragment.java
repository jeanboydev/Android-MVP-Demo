package com.jeanboy.app.mvpdemo.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseFragment;
import com.jeanboy.app.mvpdemo.ui.activity.InfoActivity;
import com.jeanboy.app.mvpdemo.ui.activity.MainActivity;
import com.jeanboy.app.mvpdemo.ui.adapter.ListAdapter;
import com.jeanboy.lib.common.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.lib.common.adapter.recyclerview.RecyclerBaseAdapter;
import com.jeanboy.lib.common.adapter.recyclerview.decoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.list_container)
    RecyclerView list_container;

    private List<String> dataList;
    private ListAdapter listAdapter;

    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void setupArguments(Bundle args) {
    }

    @Override
    public void setupView(View view, Bundle savedInstanceState) {
        setToolsBarTitle("首页").isTranslucent().addToolbarMarginTop();

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(), ((MainActivity) getActivity()).getDrawerLayout(),
                getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();

        dataList = new ArrayList<>();
        listAdapter = new ListAdapter(dataList, R.layout.item_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        list_container.setAdapter(listAdapter);
        list_container.setLayoutManager(layoutManager);
        list_container.addItemDecoration(new SpaceItemDecoration(getContext().getResources().getDimensionPixelSize(R.dimen.recycler_item_space)));

        listAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, BaseViewHolder holder, int position) {
                ((MainActivity) getActivity()).startAwesomeActivity(new Intent(getActivity(), InfoActivity.class), Pair.create(holder.getView(R.id.iv_thumb), "info_thumb"));
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            isTranslucent().addToolbarMarginTop();
        }
    }

    @Override
    public void initData() {
        for (int i = 0; i < 20; i++) {
            dataList.add(String.valueOf(i));
        }
        listAdapter.notifyDataSetChanged();
    }

}

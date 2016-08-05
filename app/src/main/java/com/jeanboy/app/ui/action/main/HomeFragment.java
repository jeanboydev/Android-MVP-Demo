package com.jeanboy.app.ui.action.main;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseFragment;
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


    private LoadMoreHelper loadMoreHelper;
    private LoadMoreListener loadMoreListener;

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
        listAdapter = new ListAdapter(getActivity(), dataList, R.layout.item_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        loadMoreHelper = new LoadMoreHelper(list_container, layoutManager, listAdapter);

        loadMoreListener = new LoadMoreListener<String>(list_container, dataList, getActivity()) {
            @Override
            public void loadMore() {
                initData();
            }
        };
        loadMoreListener.setTotal(50);

        loadMoreHelper.addLoadMoreListener(loadMoreListener);

    }

    @Override
    public void initData() {
        for (int i = 0; i < 20; i++) {
            dataList.add(String.valueOf(i));
        }
        listAdapter.notifyDataSetChanged();
    }


}

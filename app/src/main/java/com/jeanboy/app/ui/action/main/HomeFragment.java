package com.jeanboy.app.ui.action.main;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseFragment;
import com.jeanboy.app.ui.adapter.ListAdapter;
import com.jeanboy.recyclerviewhelper.RecyclerViewHelper;

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


    private RecyclerViewHelper loadMoreHelper;


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

        loadMoreHelper = RecyclerViewHelper.build(getActivity(), list_container, layoutManager, listAdapter);

        loadMoreHelper.addLoadMoreListener(new RecyclerViewHelper.LoadMoreCallback() {
            @Override
            public void loadMore() {
                loadNext();
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

    public void loadNext() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            dataList.add(String.valueOf(i));
                        }
                        loadMoreHelper.loadComplete();
                        loadMoreHelper.notifyDataSetChanged();
                        loadMoreHelper.hasNext(true);
                    }
                });
            }
        }).start();
    }


}

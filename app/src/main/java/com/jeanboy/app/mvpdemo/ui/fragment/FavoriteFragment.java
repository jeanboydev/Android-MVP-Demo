package com.jeanboy.app.mvpdemo.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseFragment;
import com.jeanboy.app.mvpdemo.ui.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class FavoriteFragment extends BaseFragment {

    @BindView(R.id.list_container)
    RecyclerView list_container;


    private List<String> dataList=new ArrayList<>();
    private ListAdapter listAdapter;

    public FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_favorite;
    }

    @Override
    public void setupArguments(Bundle args) {

    }

    @Override
    public void setupView(View view, Bundle savedInstanceState) {
        setToolsBarTitle("最爱").hasMenu().isDefault();

        dataList = new ArrayList<>();
        listAdapter = new ListAdapter( dataList, R.layout.item_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        list_container.setLayoutManager(layoutManager);
        list_container.setHasFixedSize(true);
        list_container.setAdapter(listAdapter);
        list_container.setItemAnimator(new DefaultItemAnimator());

//        listAdapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, ViewHolder holder, int position) {
//                ((MainActivity) getActivity()).startAwesomeActivity(new Intent(getActivity(), InfoActivity.class), Pair.create(holder.getView(R.id.iv_thumb), "info_thumb"));
//            }
//        });
    }

    @Override
    public void initData() {
        for (int i = 0; i < 20; i++) {
            dataList.add(String.valueOf(i));
        }
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_more, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            isDefault();
        }
    }
}

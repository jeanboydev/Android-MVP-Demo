package com.jeanboy.app.mvpdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseActivity;
import com.jeanboy.app.mvpdemo.ui.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ListActivity extends BaseActivity {

    @BindView(R.id.list)
    RecyclerView list;

    private List<String> dataList;
    private ListAdapter listAdapter;

    @Override
    public Class getTag(Class clazz) {
        return ListActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_list;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        setToolsBarTitle("列表").homeAsUp().isTranslucent().setToolbarTopMargin();

        dataList = new ArrayList<>();
        listAdapter = new ListAdapter(dataList, R.layout.item_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        list.setHasFixedSize(true);
//        list.setAdapter(listAdapter);
        list.setItemAnimator(new DefaultItemAnimator());

//        listAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                startAwesomeActivity(new Intent(ListActivity.this, InfoActivity.class), Pair.create(v, "info_thumb"));
//            }
//        });
    }

    @Override
    public void initData() {

        for (int i = 0; i < 50; i++) {
            dataList.add(String.valueOf(i));
        }
        listAdapter.notifyDataSetChanged();
    }


}

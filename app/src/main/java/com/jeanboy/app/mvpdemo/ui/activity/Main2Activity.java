package com.jeanboy.app.mvpdemo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.ui.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.list_container)
    RecyclerView list_container;


    private List<String> dataList;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        dataList = new ArrayList<>();
        listAdapter = new ListAdapter(dataList, R.layout.item_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        list_container.setAdapter(listAdapter);
        list_container.setLayoutManager(layoutManager);

        for (int i = 0; i < 20; i++) {
            dataList.add(String.valueOf(i));
        }
        listAdapter.notifyDataSetChanged();
    }


    public static class SettingsActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_settings);
        }
    }
}

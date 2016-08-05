package com.jeanboy.app.ui.action.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;

/**
 * Created by Next on 2016/8/5.
 */
public class LoadMoreHelper<T> {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private RecyclerView.Adapter adapter;

    private HeaderAndFooterRecyclerViewAdapter recyclerViewAdapter;

    public LoadMoreHelper(RecyclerView recyclerView, LinearLayoutManager layoutManager, RecyclerView.Adapter adapter) {
        this.recyclerView = recyclerView;
        this.layoutManager = layoutManager;
        this.adapter = adapter;

        setup();
    }

    private void setup() {
        recyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    public void addLoadMoreListener(@NonNull LoadMoreListener loadMoreListener) {
        recyclerView.addOnScrollListener(loadMoreListener);
    }
}

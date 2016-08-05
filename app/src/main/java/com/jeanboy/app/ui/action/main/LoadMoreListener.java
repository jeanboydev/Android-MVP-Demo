package com.jeanboy.app.ui.action.main;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cundong.recyclerview.EndlessRecyclerOnScrollListener;
import com.cundong.utils.RecyclerViewStateUtils;
import com.cundong.widget.LoadingFooter;

import java.util.List;

/**
 * Created by Next on 2016/8/5.
 */
public abstract class LoadMoreListener<T> extends EndlessRecyclerOnScrollListener {

    private RecyclerView recyclerView;
    private List<T> dataList;

    private int total = 0;
    private int pageSize = 10;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    private Activity activity;

    public LoadMoreListener(RecyclerView recyclerView, List<T> dataList, Activity activity) {
        this.recyclerView = recyclerView;
        this.dataList = dataList;
        this.activity = activity;
    }

    @Override
    public void onLoadNextPage(View view) {
        super.onLoadNextPage(view);

        LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(recyclerView);
        if (state == LoadingFooter.State.Loading) {
            return;
        }

        if (dataList.size() < total) {
            // loading more
            RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, pageSize, LoadingFooter.State.Loading, null);
            loadMore();
        } else {
            //the end
            RecyclerViewStateUtils.setFooterViewState(activity, recyclerView, pageSize, LoadingFooter.State.TheEnd, null);
        }

    }

    public abstract void loadMore();
}

package com.jeanboy.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by Next on 2016/7/26.
 */
public class ListNormalAdapter<T> extends RecyclerView.Adapter<ViewHolder> {


    private Context context;
    private List<T> dataList;
    private int layoutId;
    private LayoutInflater inflater;

    public ListNormalAdapter(Context context, List<T> dataList, int layoutId) {
        this.context = context;
        this.dataList = dataList;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = inflater.inflate(layoutId, parent, false);
        Holder holder = new Holder(viewItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class Holder extends ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}

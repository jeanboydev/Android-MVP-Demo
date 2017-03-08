package com.jeanboy.app.mvpdemo.ui.adapter;

import android.support.annotation.NonNull;

import com.jeanboy.lib.common.adapter.recyclerview.BaseViewHolder;
import com.jeanboy.lib.common.adapter.recyclerview.RecyclerBaseAdapter;

import java.util.List;

/**
 * Created by Next on 2016/7/26.
 */
public class ListAdapter extends RecyclerBaseAdapter<String> {


    public ListAdapter(@NonNull List<String> dataList, int itemLayoutId) {
        super(dataList, itemLayoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, String string, int position) {

    }
}

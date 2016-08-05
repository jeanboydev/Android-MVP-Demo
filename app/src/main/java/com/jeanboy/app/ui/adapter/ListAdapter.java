package com.jeanboy.app.ui.adapter;

import android.content.Context;

import com.jeanboy.common.adapter.recyclerview.CommonAdapter;
import com.jeanboy.common.adapter.recyclerview.ViewHolder;

import java.util.List;

/**
 * Created by Next on 2016/7/26.
 */
public class ListAdapter extends CommonAdapter<String> {


    public ListAdapter(Context context, List<String> dataList, int layoutId) {
        super(context, dataList, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, String s, int position) {

    }
}

package com.jeanboy.lib.common.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by next on 2016/3/7.
 */
public abstract class ListBaseAdapter<T> extends android.widget.BaseAdapter {
    protected Context context;
    protected List<T> dataList;
    protected LayoutInflater inflater;
    protected int layoutId;

    public ListBaseAdapter(Context context, List<T> dataList, int layoutId) {
        this.context = context;
        this.dataList = dataList;
        this.layoutId = layoutId;
        inflater = LayoutInflater.from(context);
    }

    public void setDatasList(List<T> dataList){
        if(this.dataList == dataList){
            notifyDataSetChanged();
            return;
        }

        if(this.dataList != null){
            this.dataList = null;
        }
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    /**
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return dataList.size();
    }

    /**
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    /**
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * @see android.widget.Adapter#getView(int, View, ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(context, convertView, parent, layoutId, position);
        convert(holder, getItem(position), position);
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t, int position);
}

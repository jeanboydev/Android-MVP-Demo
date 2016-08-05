package com.jeanboy.common.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Next on 2016/8/5.
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private List<T> dataList;
    private int layoutId;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public CommonAdapter(Context context, List<T> dataList, int layoutId) {
        this.context = context;
        this.dataList = dataList;
        this.layoutId = layoutId;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = ViewHolder.createViewHolder(context, parent, layoutId);
        setListener(parent, holder, viewType);
        return null;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, dataList.get(position), holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        int itemCount = dataList.size();
        return itemCount;
    }

    public void setListener(final ViewGroup parent, final ViewHolder viewHolder, int viewType) {
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(v, viewHolder, position);
                }
            }
        });

    }

    public abstract void convert(ViewHolder holder, T t, int position);

    public interface OnItemClickListener {
        void onItemClick(View view, RecyclerView.ViewHolder holder, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

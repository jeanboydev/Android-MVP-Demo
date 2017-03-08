package com.jeanboy.lib.common.adapter.listview;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeanboy.lib.common.manager.images.ImageConfig;
import com.jeanboy.lib.common.manager.images.ImagesManager;


/**
 * Created by next on 2016/3/7.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;


    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<>();
        this.mPosition = position;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView,
                                 ViewGroup parent, int layoutId, int position) {
        if (null == convertView) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过viewId获取view
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public ViewHolder setImagePath(int viewId, Context context, String url) {
        ImageView iv = getView(viewId);
        // TODO: 2016/7/8 need image loader
        ImagesManager.getInstance().load(iv, url);
        return this;
    }

    public ViewHolder setImageAvatarPath(int viewId, Context context, String url) {
        ImageView iv = getView(viewId);
        // TODO: 2016/7/8 need image loader
        ImagesManager.getInstance().load(iv, url, new ImageConfig().setSize(48, 48));
        return this;
    }

    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

}

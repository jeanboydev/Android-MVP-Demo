package com.jeanboy.app.mvpdemo.component.handler;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.jeanboy.lib.common.manager.images.ImageConfig;
import com.jeanboy.lib.common.manager.images.ImageHandler;

import java.io.File;

/**
 * Created by jeanboy on 2016/12/14.
 */

public class GlideHandler implements ImageHandler {

    private Context context;
    private RequestManager requestManager;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    private RequestManager getManager() {
        if (requestManager == null) {
            requestManager = Glide.with(context);
        }
        return requestManager;
    }

    @Override
    public void load(ImageView imageView, String url) {
        getManager().load(url).centerCrop().into(imageView);
    }

    @Override
    public void load(ImageView imageView, File file) {
        getManager().load(file).centerCrop().into(imageView);
    }

    @Override
    public void load(ImageView imageView, Uri uri) {
        getManager().load(uri).centerCrop().into(imageView);
    }

    @Override
    public void load(ImageView imageView, String url, ImageConfig config) {
        if (config == null) {
            load(imageView, url);
        } else {
            DrawableRequestBuilder requestBuilder = getManager().load(url).centerCrop();
            if (config.getPlaceholder() > 0) {
                requestBuilder.placeholder(config.getPlaceholder());//加载中图片
            }
            if (config.getError() > 0) {
                requestBuilder.error(config.getError());//加载失败图片
            }
            if (config.getMaxWidth() > 0 && config.getMaxHeight() > 0) {
                requestBuilder.override(config.getMaxWidth(), config.getMaxHeight());
            }
            requestBuilder.into(imageView);
        }
    }

    @Override
    public void load(ImageView imageView, File file, ImageConfig config) {
        if (config == null) {
            load(imageView, file);
        } else {
            DrawableRequestBuilder requestBuilder = getManager().load(file).centerCrop();
            if (config.getPlaceholder() > 0) {
                requestBuilder.placeholder(config.getPlaceholder());//加载中图片
            }
            if (config.getError() > 0) {
                requestBuilder.error(config.getError());//加载失败图片
            }
            if (config.getMaxWidth() > 0 && config.getMaxHeight() > 0) {
                requestBuilder.override(config.getMaxWidth(), config.getMaxHeight());
            }
            requestBuilder.into(imageView);
        }
    }

    @Override
    public void load(ImageView imageView, Uri uri, ImageConfig config) {
        if (config == null) {
            load(imageView, uri);
        } else {
            DrawableRequestBuilder requestBuilder = getManager().load(uri).centerCrop();
            if (config.getPlaceholder() > 0) {
                requestBuilder.placeholder(config.getPlaceholder());//加载中图片
            }
            if (config.getError() > 0) {
                requestBuilder.error(config.getError());//加载失败图片
            }
            if (config.getMaxWidth() > 0 && config.getMaxHeight() > 0) {
                requestBuilder.override(config.getMaxWidth(), config.getMaxHeight());
            }
            requestBuilder.into(imageView);
        }
    }
}

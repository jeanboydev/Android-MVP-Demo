package com.jeanboy.lib.common.manager.images;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Next on 2016/7/4.
 */
public class ImagesManager {

    private Context context;

    private ImageHandler imageHandler;

    private static class SingletonHolder {
        @SuppressLint("StaticFieldLeak")
        private static ImagesManager instance = new ImagesManager();
    }

    private ImagesManager() {
    }

    public static ImagesManager getInstance() {
        return ImagesManager.SingletonHolder.instance;
    }

    public void build(@NonNull Context context, @NonNull ImageHandler imageHandler) {
        this.context = context.getApplicationContext();
        this.imageHandler = imageHandler;
        this.imageHandler.init(this.context);
    }

    public void load(ImageView imageView, String url) {
        imageHandler.load(imageView, url);
    }

    public void load(ImageView imageView, File file) {
        imageHandler.load(imageView, file);
    }

    public void load(ImageView imageView, Uri uri) {
        imageHandler.load(imageView, uri);
    }

    public void load(ImageView imageView, String url, ImageConfig config) {
        imageHandler.load(imageView, url, config);
    }

    public void load(ImageView imageView, File file, ImageConfig config) {
        imageHandler.load(imageView, file, config);
    }

    public void load(ImageView imageView, Uri uri, ImageConfig config) {
        imageHandler.load(imageView, uri, config);
    }
}

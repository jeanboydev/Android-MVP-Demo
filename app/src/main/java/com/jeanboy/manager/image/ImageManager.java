package com.jeanboy.manager.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.io.File;

/**
 * Created by Next on 2016/7/4.
 */
public class ImageManager {

    private static ImageManager instance = null;

    private static RequestManager requestManager;

    private ImageManager() {
    }

    public static ImageManager getInstance() {
        if (instance == null) {
            synchronized (ImageManager.class) {
                if (instance == null) {
                    instance = new ImageManager();
                }
            }
        }
        return instance;
    }


    public void build(Context context) {
        requestManager = Glide.with(context);
    }

    /**
     * 加载原图
     *
     * @param imageView
     * @param url
     */
    public static void load(ImageView imageView, String url) {
        requestManager.load(url)
//                .placeholder()//加载中图片
//                .error()//加载失败图片
                .centerCrop()
                .into(imageView);
    }

    public static void load(ImageView imageView, File file) {
        requestManager.load(file)
//                .placeholder()//加载中图片
//                .error()//加载失败图片
                .centerCrop()
                .into(imageView);
    }


    /**
     * 加载指定尺寸的图片
     *
     * @param imageView
     * @param url
     * @param maxWidth
     * @param maxHeight
     */
    public static void load(ImageView imageView, String url, int maxWidth, int maxHeight) {
        requestManager.load(url)
//                .placeholder()//加载中图片
//                .error()//加载失败图片
                .override(maxWidth, maxHeight)
                .into(imageView);

    }
}

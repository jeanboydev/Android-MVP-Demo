package com.jeanboy.manager.image;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Next on 2016/7/4.
 */
public class ImageManager {

    private static ImageManager instance;

    private static Picasso picasso;

    private ImageManager() {
    }

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }


    public void build(Context context) {
        picasso = Picasso.with(context);
    }

    /**
     * 加载原图
     *
     * @param imageView
     * @param url
     */
    public static void load(ImageView imageView, String url) {
        picasso.load(url)
//                .placeholder()//加载中图片
//                .error()//加载失败图片
                .centerCrop()
                .into(imageView);
    }

    public static void load(ImageView imageView, File file) {
        picasso.load(file)
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
        picasso.load(url)
//                .placeholder()//加载中图片
//                .error()//加载失败图片
                .resize(maxWidth, maxHeight)
                .into(imageView);
    }
}

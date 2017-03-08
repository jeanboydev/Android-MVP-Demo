package com.jeanboy.lib.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.WindowManager;

/**
 * Created by jeanboy on 2017/3/8.
 */

public class StatusBarUtil {

    private final static int SHADOW_COLOR = 0x80000000;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setTranslucent(Activity activity) {
        setTransparent(activity);
        activity.getWindow().setStatusBarColor(SHADOW_COLOR);//设置状态栏颜色
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setTransparent(Activity activity) {
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);//绘制系统栏
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//透明虚拟按键
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);//设置透明
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setColor(Activity activity, int color) {
        activity.getWindow().setStatusBarColor(color);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }
}

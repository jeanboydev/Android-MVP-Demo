package com.jeanboy.lib.common.utils;

import android.content.Context;

/**
 * Created by Next on 2016/7/8.
 */
public class DensityUtil {

    public DensityUtil() {
        throw new AssertionError();
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);

    }
}

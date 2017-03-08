package com.jeanboy.lib.common.utils;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Next on 2016/7/8.
 */
public class ViewUtil {

    public ViewUtil() {
        throw new AssertionError();
    }

    /**
     * 设置View的外边距(Margins)
     *
     * @param view   要设置外边距的View
     * @param left   左侧外边距
     * @param top    顶部外边距
     * @param right  右侧外边距
     * @param bottom 底部外边距
     */
    public static void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view
                    .getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    /**
     * 设置View的左侧外边距
     *
     * @param view 要设置外边距的View
     * @param left 左侧外边距
     */
    public static void setMarginLeft(View view, int left) {
        setMargins(view, left, 0, 0, 0);
    }


    /**
     * 设置View的顶部外边距
     *
     * @param view 要设置外边距的View
     * @param top 顶部外边距
     */
    public static void setMarginTop(View view, int top) {
        setMargins(view, 0, top, 0, 0);
    }


    /**
     * 设置View的右侧外边距
     *
     * @param view 要设置外边距的View
     * @param right 右侧外边距
     */
    public static void setMarginRight(View view, int right) {
        setMargins(view, 0, 0, right, 0);
    }


    /**
     * 设置View的底部外边距
     *
     * @param view 要设置外边距的View
     * @param bottom 底部外边距
     */
    public static void setMarginBottom(View view, int bottom) {
        setMargins(view, 0, 0, 0, bottom);
    }

    /**
     * 设置输入框的光标到末尾
     */
    public static final void setEditTextSelectionToEnd(EditText editText) {
        Editable editable = editText.getEditableText();
        Selection.setSelection((Spannable) editable,
                editable.toString().length());
    }

}

package com.isseiaoki.simplecropview.crop;

import android.app.Dialog;
import android.content.Context;

import com.jeanboy.app.R;

/**
 * Created by Next on 2016/8/3.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.DialogTheme_Loading);
        setContentView(R.layout.dialog_loading);

    }

    @Override
    public void onBackPressed() {
        cancel();
        dismiss();
        super.onBackPressed();
    }
}

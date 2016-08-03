package com.jeanboy.app.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jeanboy.app.R;
import com.jeanboy.common.utils.StatusBarUtil;

import butterknife.ButterKnife;


/**
 * Created by Next on 2016/7/4.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public String TAG;

    private Toolbar toolbar;

    public BaseActivity() {
        TAG = this.getClass().getSimpleName();
    }

    public abstract Class getTag(Class clazz);

    public abstract int getLayoutId();

    public abstract void setupView(Bundle savedInstanceState);

    public abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        TAG = getTag(BaseActivity.class).getSimpleName();
        ButterKnife.bind(this);

        setupToolbar();
        setupStatusBar();
        setupView(savedInstanceState);
        initData();
    }

    protected void setupStatusBar() {
//        StatusBarUtil.setTranslucent(this, getResources().getColor(R.color.colorPrimary));
//        StatusBarUtil.setTranslucent(this);
    }

    /**
     * 初始化Toolbar
     */
    private void setupToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                toolbar.setTitle("");
                setSupportActionBar(toolbar);
            }
        }
    }

    public BaseActivity addMenu(int layout, Toolbar.OnMenuItemClickListener itemClickListener) {
        if (toolbar == null) {
            toolbar.inflateMenu(layout);
            toolbar.setOnMenuItemClickListener(itemClickListener);
        }
        return this;
    }

    public BaseActivity isTransparent() {
        StatusBarUtil.setTranslucent(this);
        return this;
    }

    public BaseActivity setStatusBarColor(int color) {
        StatusBarUtil.setColor(this, color);
        return this;
    }

    /**
     * 设置Toolbar的标题
     *
     * @param title
     * @return
     */
    public BaseActivity setTitle(String title) {
        if (toolbar != null) {
            TextView titleView = (TextView) toolbar.findViewById(R.id.toolbar_title);
            if (titleView != null) {
                titleView.setText(title);
            }
        }
        return this;
    }

    /**
     * 设置Toolbar左上角箭头可用
     *
     * @return
     */
    public BaseActivity homeAsUp() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return this;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onDestroy();
    }



    /**
     * startActivity兼容处理
     *
     * @param intent
     * @param pairs
     */
    public void startAwesomeActivity(Intent intent, Pair<View, String>... pairs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs).toBundle());
        } else {
            startActivity(intent);
        }
    }
}

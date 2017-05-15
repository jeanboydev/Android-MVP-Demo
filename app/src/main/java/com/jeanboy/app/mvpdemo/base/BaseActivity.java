package com.jeanboy.app.mvpdemo.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.lib.common.utils.StatusBarUtil;

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

    public abstract int getLayoutId();

    public abstract void setupView(Bundle savedInstanceState);

    public abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        setupToolbar();
        setupView(savedInstanceState);
        initData();
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

    public Toolbar getToolbar() {
        return toolbar;
    }

    public BaseActivity addMenu(int layout, Toolbar.OnMenuItemClickListener itemClickListener) {
        if (toolbar != null) {
            toolbar.inflateMenu(layout);
            toolbar.setOnMenuItemClickListener(itemClickListener);
        }
        return this;
    }

    /**
     * 设置Toolbar的标题
     *
     * @param title
     * @return
     */
    public BaseActivity setToolsBarTitle(String title) {
        if (toolbar != null) {
            TextView titleView = (TextView) toolbar.findViewById(R.id.toolbar_title);
            if (titleView != null) {
                titleView.setText(title);
            }
        }
        return this;
    }

    public BaseActivity setToolsBarTitle(int titleId) {
        if (toolbar != null && titleId > 0) {
            TextView titleView = (TextView) toolbar.findViewById(R.id.toolbar_title);
            if (titleView != null) {
                titleView.setText(titleId);
            }
        }
        return this;
    }

    public void setToolbarNavigation(int resId, int descId, View.OnClickListener listener) {
        if (toolbar != null) {
            toolbar.setNavigationIcon(resId);
            toolbar.setNavigationContentDescription(descId);
            toolbar.setNavigationOnClickListener(listener);
        }
    }

    /**
     * 向上需要全部滚出屏幕时设置topMargin代替fitsSystemWindows="true"
     * @return
     */
    public BaseActivity setToolbarTopMargin() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (toolbar != null) {
                if (toolbar.getLayoutParams() instanceof AppBarLayout.LayoutParams) {
                    AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
                    params.topMargin = StatusBarUtil.getStatusBarHeight(this);
                    toolbar.setLayoutParams(params);
                }
            }
        }
        return this;
    }

    /**
     * 设置状态栏全透明
     *
     * @return
     */
    public BaseActivity isTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarUtil.setTransparent(this);
        }
        return this;
    }

    /**
     * 设置状态栏半透明
     *
     * @return
     */
    public BaseActivity isTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            StatusBarUtil.setTranslucent(this);
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

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * startActivity兼容处理
     *
     * @param intent
     * @param pairs
     */
    @SafeVarargs
    public final void startAwesomeActivity(Intent intent, Pair<View, String>... pairs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs).toBundle());
        } else {
            startActivity(intent);
        }
    }
}

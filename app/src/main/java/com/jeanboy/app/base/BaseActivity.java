package com.jeanboy.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.jeanboy.app.R;

import butterknife.ButterKnife;


/**
 * Created by Next on 2016/7/4.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public String TAG;

    public Toolbar mToolbar;

    public BaseActivity() {
        TAG = this.getClass().getSimpleName();
    }

    public abstract Class getTag(Class clazz);

    public abstract int getLayoutId();

    public abstract void setupView();

    public abstract void initData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        TAG = getTag(BaseActivity.class).getSimpleName();
        ButterKnife.bind(this);
        setupActionBar();
        setupView();
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initData();
    }

    public void setupActionBar() {
        if (getToolbar() == null) {
            return;
        }
        mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public TextView getToolbarTitleView() {
        if (getToolbar() == null) {
            return null;
        }
        return ((TextView) mToolbar.findViewById(R.id.toolbar_title));
    }

    protected Toolbar getToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
                mToolbar.setTitle("");
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }


    /**
     * tool bar back button operation
     *
     * @param item
     * @return
     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}

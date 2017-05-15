package com.jeanboy.app.mvpdemo.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.RadioGroup;

import com.jeanboy.app.mvpdemo.R;
import com.jeanboy.app.mvpdemo.base.BaseActivity;
import com.jeanboy.app.mvpdemo.ui.fragment.FavoriteFragment;
import com.jeanboy.app.mvpdemo.ui.fragment.HomeFragment;
import com.jeanboy.app.mvpdemo.ui.fragment.MusicFragment;
import com.jeanboy.app.mvpdemo.ui.fragment.SettingsFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    private HomeFragment homeFragment;
    private FavoriteFragment favoriteFragment;
    private MusicFragment musicFragment;
    private SettingsFragment settingsFragment;

    private Fragment[] fragments;

    @BindView(R.id.bottom_menu)
    RadioGroup mBottomMenu;
    @BindView(R.id.bg_view)
    View bgColorView;
    @BindView(R.id.bg_anim_view)
    View bgAnimColorView;


    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView mNavigationView;


    private Map<Integer, Integer> mColorMap = new HashMap<>();

    private int currentTabIndex = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        homeFragment = HomeFragment.newInstance();
        favoriteFragment = FavoriteFragment.newInstance();
        musicFragment = MusicFragment.newInstance();
        settingsFragment = SettingsFragment.newInstance();
        fragments = new Fragment[]{homeFragment, favoriteFragment, musicFragment, settingsFragment};
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragments[currentTabIndex])
                .show(fragments[currentTabIndex]).commitAllowingStateLoss();

        mColorMap.put(0, 0xFF5D4037);
        mColorMap.put(1, 0xFFFF9800);
        mColorMap.put(2, 0xFF7B1FA2);
        mColorMap.put(3, 0xFFFF5252);

        bgColorView.setBackgroundColor(mColorMap.get(currentTabIndex));
        mBottomMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                View child = group.findViewById(checkedId);
                int centerX = (child.getLeft() + child.getRight()) / 2;
                int centerY = (child.getTop() + child.getBottom()) / 2;

                int index = 0;
                int count = group.getChildCount();
                for (int i = 0; i < count; i++) {
                    if (checkedId == group.getChildAt(i).getId()) {
                        index = i;
                    }
                }

                doAnimation(index, centerX, centerY);

                switchTab(index);
            }
        });

        mNavigationView.setNavigationItemSelectedListener(this);

    }

    private void doAnimation(final int position, int centerX, int centerY) {
        Animator animator = ViewAnimationUtils.createCircularReveal(bgAnimColorView, centerX, centerY, 0, mBottomMenu.getWidth());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                bgColorView.setBackgroundColor(mColorMap.get(position));
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                bgAnimColorView.setBackgroundColor(mColorMap.get(position));
            }
        });
        animator.start();
    }

    @Override
    public void initData() {

    }

    public void switchTab(int index) {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded())
                trx.add(R.id.fragment_container, fragments[index]);
            trx.show(fragments[index]).commitAllowingStateLoss();
        }
        currentTabIndex = index;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (musicFragment != null) {
//            musicFragment.onActivityResult(requestCode, resultCode, data);
//        }
//        if (settingsFragment != null) {
//            settingsFragment.onActivityResult(requestCode, resultCode, data);
//        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }
}

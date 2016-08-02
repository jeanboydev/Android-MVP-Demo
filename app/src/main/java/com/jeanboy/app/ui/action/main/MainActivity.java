package com.jeanboy.app.ui.action.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.jeanboy.app.R;
import com.jeanboy.app.base.BaseActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;


public class MainActivity extends BaseActivity {


    private HomeFragment homeFragment;
    private FavoriteFragment favoriteFragment;
    private MusicFragment musicFragment;
    private SettingsFragment settingsFragment;

    private Fragment[] fragments;
    private BottomBar bottomBar;


    private int currentTabIndex;

    @Override
    public Class getTag(Class clazz) {
        return MainActivity.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupView(Bundle savedInstanceState) {
        homeFragment = HomeFragment.newInstance(null);
        favoriteFragment = FavoriteFragment.newInstance();
        musicFragment = MusicFragment.newInstance();
        settingsFragment = SettingsFragment.newInstance();
        fragments = new Fragment[]{homeFragment, favoriteFragment, musicFragment, settingsFragment};
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragments[currentTabIndex])
                .show(fragments[currentTabIndex]).commitAllowingStateLoss();

        bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItems(R.menu.bottombar_menu);
        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                int index = 0;
                switch (menuItemId) {
                    case R.id.bottomBarItem0:
                        index = 0;
                        break;
                    case R.id.bottomBarItem1:
                        index = 1;
                        break;
                    case R.id.bottomBarItem2:
                        index = 2;
                        break;
                    case R.id.bottomBarItem3:
                        index = 3;
                        break;
                }
                switchTab(index);
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        bottomBar.mapColorForTab(0, "#FF9800");
        bottomBar.mapColorForTab(1, 0xFF5D4037);
        bottomBar.mapColorForTab(2, "#7B1FA2");
        bottomBar.mapColorForTab(3, "#FF5252");


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
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


//    public void toList(View v) {
//        startAwesomeActivity(new Intent(this, ListActivity.class));
//    }
//
//
//    public void doWork(View v) {
//        startAwesomeActivity(new Intent(this, LoginActivity.class));
//    }
//
//    public void doAnim(View v) {
//        startAwesomeActivity(new Intent(this, LoginActivity.class), Pair.create(v, "robot"));
//    }
}

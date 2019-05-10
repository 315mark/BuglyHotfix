package com.zkch.bugly;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.zkch.bugly.base.BaseActivity;
import com.zkch.bugly.contract.MainContract;
import com.zkch.bugly.fragment.AboutFragment;
import com.zkch.bugly.fragment.ImageFragment;
import com.zkch.bugly.fragment.NewsFragment;
import com.zkch.bugly.fragment.WeatherFragment;
import com.zkch.bugly.presenter.MainPresenterImpl;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.MainView {

    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.draw_layout)
    DrawerLayout drawLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    ActionBarDrawerToggle mToggle;
    MainContract.MainPresenter mPresenter;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setSupportActionBar(mToolbar);

        mToggle =new ActionBarDrawerToggle(this,drawLayout,mToolbar,R.string.drawer_open,R.string.drawer_close);
        mToggle.syncState();
        drawLayout.addDrawerListener(mToggle);

        mPresenter =new MainPresenterImpl(this);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mPresenter.switchNavigation(menuItem.getItemId());
                menuItem.setChecked(true);
                drawLayout.closeDrawers();
                return true;
            }
        });

        showNews();
    }

    @Override
    public void showNews() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new NewsFragment()).commit();
        mToolbar.setTitle(R.string.navigation_news);
    }

    @Override
    public void showImages() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new ImageFragment()).commit();
        mToolbar.setTitle(R.string.navigation_images);
    }

    @Override
    public void showWeather() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new WeatherFragment()).commit();
        mToolbar.setTitle(R.string.navigation_weather);
    }

    @Override
    public void showAbout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new AboutFragment()).commit();
        mToolbar.setTitle(R.string.navigation_about);
    }
}

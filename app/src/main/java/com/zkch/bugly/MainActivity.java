package com.zkch.bugly;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.zkch.bugly.base.BaseActivity;
import com.zkch.bugly.contract.MainContract;
import com.zkch.bugly.pay.PayActivity;
import com.zkch.bugly.presenter.MainPresenterImpl;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;

public class MainActivity extends BaseActivity/* implements MainContract.MainView*/ {

    @BindView(R.id.navigation)
    NavigationView navigation;
    @BindView(R.id.draw_layout)
    DrawerLayout drawLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    ActionBarDrawerToggle mToggle;
    MainContract.MainPresenter mPresenter;
    NavController navController;

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
        mPresenter =new MainPresenterImpl();
        navController = Navigation.findNavController(MainActivity.this, R.id.newsFragment);
        NavigationUI.setupWithNavController(mToolbar,navController,drawLayout);

        navigation.setNavigationItemSelectedListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.newsFragment:
                        showNews(menuItem);
                        break;

                    case R.id.imageFragment:
                        showImages(menuItem);
                        break;

                    case R.id.weatherFragment:
                       showWeather(menuItem);
                        break;

                    case R.id.aboutFragment:
                        showAbout(menuItem);
                        break;

                    case R.id.pay:
                       //pay(v);
                        break;

                    case R.id.AliPay:
                        AliPay(menuItem);
                        break;

                    case R.id.jokerFragment:
                        showJoker(menuItem);
                        break;

                    case R.layout.fragment_fab :

                        showFab(menuItem);
                        break;

                    default:
                        showNews(menuItem);
                        break;
                }

//                mPresenter.switchNavigation(menuItem.getActionView());
//                menuItem.setChecked(true);
            drawLayout.closeDrawers();
            return true;
        });
    }


    public void showNews(MenuItem view) {
        NavigationUI.onNavDestinationSelected(view, navController);
        mToolbar.setTitle(R.string.navigation_news);
    }


    public void showImages(MenuItem view) {
        NavigationUI.onNavDestinationSelected(view, navController);
        mToolbar.setTitle(R.string.navigation_images);
    }


    public void showWeather(MenuItem view) {

        NavigationUI.onNavDestinationSelected(view, navController);
        mToolbar.setTitle(R.string.navigation_weather);
    }


    public void showAbout(MenuItem view) {
        NavigationUI.onNavDestinationSelected(view, navController);
        mToolbar.setTitle(R.string.navigation_about);
    }

    public void showJoker(MenuItem view) {
        NavigationUI.onNavDestinationSelected(view, navController);
        mToolbar.setTitle(R.string.navigation_joker);
    }

    public void pay(View view) {
        Intent intent =new Intent(this, PayActivity.class);
        startActivity(intent);
    }

    public void AliPay(MenuItem view) {
        NavigationUI.onNavDestinationSelected(view, navController);
        mToolbar.setTitle(R.string.navigation_about);
    }

    public void showFab(MenuItem view) {
        NavigationUI.onNavDestinationSelected(view, navController);
        mToolbar.setTitle(R.string.navigation_about);
    }
}

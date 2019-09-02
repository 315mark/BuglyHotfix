package com.zkch.bugly.presenter;

import android.view.View;

import com.zkch.bugly.R;
import com.zkch.bugly.contract.MainContract;

public class MainPresenterImpl implements MainContract.MainPresenter {

    private MainContract.MainView mainView;

    public MainPresenterImpl(/*MainContract.MainView mainView*/) {
       this.mainView = mainView;
    }

    @Override
    public void switchNavigation(View v) {
        switch (v.getId()) {
            case R.id.newsFragment:
                mainView.showNews(v);
                break;

            case R.id.imageFragment:
                mainView.showImages(v);
                break;

            case R.id.weatherFragment:
                mainView.showWeather(v);
                break;

            case R.id.aboutFragment:
                mainView.showAbout(v);
                break;

            case R.id.pay:
                mainView.pay(v);
                break;

            case R.id.AliPay:
                mainView.AliPay(v);
                break;

            case R.id.jokerFragment:

                break;

            default:
                mainView.showNews(v);
                break;
        }
    }
}

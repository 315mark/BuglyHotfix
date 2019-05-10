package com.zkch.bugly.presenter;

import com.zkch.bugly.R;
import com.zkch.bugly.contract.MainContract;

public class MainPresenterImpl implements MainContract.MainPresenter {

    private MainContract.MainView mainView;

    public MainPresenterImpl(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void switchNavigation(int id) {
        switch (id) {
            case R.id.navigation_item_news:
                mainView.showNews();
                break;

            case R.id.navigation_item_images:
                mainView.showImages();
                break;

            case R.id.navigation_item_weather:
                mainView.showWeather();
                break;

            case R.id.navigation_item_about:
                mainView.showAbout();
                break;

            default:
                mainView.showNews();
                break;
        }
    }
}

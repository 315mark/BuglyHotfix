package com.zkch.bugly.contract;

import com.zkch.bugly.base.BaseView;

/**
 * 契约层  IView IPersenter 都定义到Contract层
 * IView 每次都要声明 showLoading等公共方法
 * Presenter中每次请求时都要调用mView的showLoading等公共方法
 */
public interface MainContract {

   interface MainView extends BaseView{
       void showNews();
       void showImages();
       void showWeather();
       void showAbout();
   }

    interface MainPresenter {

        void switchNavigation(int id);
    }
}

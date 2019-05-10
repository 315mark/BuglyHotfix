package com.zkch.bugly.contract;

import com.zkch.bugly.bean.WeatherBean;

import java.util.List;

public interface WeatherContract {

    interface WeatherView{

        void showProgress();
        void hideProgress();
        void showWeatherLayout();

        void setCity(String city);
        void setToday(String data);
        void setTemperature(String temperature);
        void setWind(String wind);
        void setWeather(String weather);
        void setWeatherImage(int res);
        void setWeatherData(List<WeatherBean> list);
        void showErrorToast(String msg);
    }

    interface WeatherPresenter{
        void loadWeatherData();
    }

}

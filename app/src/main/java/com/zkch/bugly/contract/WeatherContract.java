package com.zkch.bugly.contract;

import android.content.Context;

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

    interface WeatherModel {

        void loadWeatherData(String cityName, LoadWeatherListener listener);

        void loadLocation(Context context, LoadLocationListener listener);

        interface LoadWeatherListener{
            void onSuccess(List<WeatherBean> list);
            void onFailure(String msg, Exception e);
        }

        interface LoadLocationListener{
            void onSuccess(String cityName);
            void onFailure(String msg, Exception e);
        }

    }

}

package com.zkch.bugly.model;

import android.content.Context;

import com.zkch.bugly.bean.WeatherBean;

import java.util.List;

public interface WeatherModel {

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

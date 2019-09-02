package com.zkch.bugly.presenter;

import android.content.Context;
import android.util.Log;

import com.zkch.bugly.bean.WeatherBean;
import com.zkch.bugly.contract.WeatherContract;
import com.zkch.bugly.contract.WeatherContract.WeatherModel;
import com.zkch.bugly.model.WeatherModelImpel;
import com.zkch.bugly.utils.ToolsUtil;
import java.util.List;

public class WeatherPresenterImpl implements WeatherContract.WeatherPresenter , WeatherModel.LoadWeatherListener {

    private WeatherContract.WeatherView mView;
    private WeatherModel model;
    private Context mContext;

    public WeatherPresenterImpl(WeatherContract.WeatherView mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        model = new WeatherModelImpel();
    }

    @Override
    public void loadWeatherData() {
        mView.showProgress();
        if ( !ToolsUtil.isNetworkAvailable(mContext)) {
            mView.hideProgress();
            mView.showErrorToast("无网络");
        }
        WeatherModelImpel.LoadLocationListener listener = new WeatherModel.LoadLocationListener() {
            @Override
            public void onSuccess(String cityName) {
                //定位成功，获取定位城市天气预报
                mView.setCity(cityName);
                model.loadWeatherData(cityName, WeatherPresenterImpl.this);
            }

            @Override
            public void onFailure(String msg, Exception e) {
                mView.showErrorToast("定位失败");
                mView.setCity("深圳");
                model.loadWeatherData("深圳", WeatherPresenterImpl.this);
            }
        };
        model.loadLocation(mContext,listener);
    }

    @Override
    public void onSuccess(List<WeatherBean> list) {
        Log.i(getClass().getName(), "onSuccess: " +list.toString());
        if (list != null && list.size()>0 ) {
            WeatherBean todayWeather =list.remove(0);
            mView.setToday(todayWeather.getDate());
            mView.setTemperature(todayWeather.getTemperature());
            mView.setWeather(todayWeather.getWeather());
            mView.setWind(todayWeather.getWind());
            mView.setWeatherImage(todayWeather.getImageRes());
        }
        mView.setWeatherData(list);
        mView.hideProgress();
        mView.showWeatherLayout();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mView.hideProgress();
        mView.showErrorToast("获取天气数据失败");
    }
}

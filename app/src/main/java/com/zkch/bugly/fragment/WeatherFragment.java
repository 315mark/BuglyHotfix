package com.zkch.bugly.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zkch.bugly.R;
import com.zkch.bugly.base.BaseFragment;
import com.zkch.bugly.bean.WeatherBean;
import com.zkch.bugly.contract.WeatherContract;
import com.zkch.bugly.presenter.WeatherPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class WeatherFragment extends BaseFragment implements WeatherContract.WeatherView {

    @BindView(R.id.city)
    TextView tvCity;
    @BindView(R.id.today)
    TextView tvToday;
    @BindView(R.id.weather_icon)
    ImageView weatherImage;
    @BindView(R.id.weatherTemp)
    TextView tvTemp;
    @BindView(R.id.wind)
    TextView tvWind;
    @BindView(R.id.weather)
    TextView tvWeather;
    @BindView(R.id.weather_content)
    LinearLayout weatherContent;
    @BindView(R.id.weather_layout)
    LinearLayout weatherLayout;
    @BindView(R.id.progress)
    ProgressBar progress;


    WeatherContract.WeatherPresenter mPresenter;


    /**
     * protected  修饰指继承该类可执行方法
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter =new WeatherPresenterImpl(this,getActivity().getApplication());
    }

    @Override
    protected void init() {
        mPresenter.loadWeatherData();
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showWeatherLayout() {
        weatherLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCity(String city) {
        tvCity.setText(city);
    }

    @Override
    public void setToday(String data) {
        tvToday.setText(data);
    }

    @Override
    public void setTemperature(String temperature) {
        tvTemp.setText(temperature);
    }

    @Override
    public void setWind(String wind) {
        tvWind.setText(wind);
    }

    @Override
    public void setWeather(String weather) {
       tvWeather.setText(weather);
    }

    @Override
    public void setWeatherImage(int res) {
        weatherImage.setImageResource(res);
    }

    @Override
    public void setWeatherData(List<WeatherBean> list) {
        List<View> dataList =new ArrayList<>();
        for (WeatherBean bean : list ) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_weather,null,false);
            TextView tv = view.findViewById(R.id.date);
            TextView tvTemp = view.findViewById(R.id.weatherTemp);
            TextView tvWind = view.findViewById(R.id.wind);
            TextView weather = view.findViewById(R.id.weather);
            ImageView icon = view.findViewById(R.id.image);

            tv.setText(bean.getWeek());
            tvTemp.setText(bean.getTemperature());
            tvWind.setText(bean.getWind());
            weather.setText(bean.getWeather());
            icon.setImageResource(bean.getImageRes());
            weatherContent.addView(view);
            dataList.add(view);
        }
    }

    @Override
    public void showErrorToast(String msg) {
        Snackbar.make(getActivity().findViewById(R.id.draw_layout),msg,Snackbar.LENGTH_SHORT).show();
    }

}

package com.zkch.bugly.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.zkch.bugly.api.Urls;
import com.zkch.bugly.bean.WeatherBean;
import com.zkch.bugly.contract.WeatherContract;
import com.zkch.bugly.utils.LogUtils;
import com.zkch.bugly.utils.OkHttpUtils;
import com.zkch.bugly.utils.WeatherJsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class WeatherModelImpel implements WeatherContract.WeatherModel {

    private static final String TAG = "WeatherModelImpel";

    @Override
    public void loadWeatherData(String cityName, final LoadWeatherListener listener) {
        try {
            String url = Urls.WEATHER + URLEncoder.encode(cityName,"utf-8");
            OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
                @Override
                public void onSuccess(String response) {
                    List<WeatherBean> data = WeatherJsonUtils.getWeatherInfo(response);
                    Log.i(TAG, "onSuccess: "  + data.toString());
                    listener.onSuccess(data);
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure("load weather data failure.", e);
                }
            };
            OkHttpUtils.get(url,callback);
        } catch (UnsupportedEncodingException e) {
            LogUtils.e(TAG, "url encode error.", e);
        }
    }

    @Override
    public void loadLocation(Context context, final LoadLocationListener listener) {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION )!= PackageManager.PERMISSION_GRANTED ){
                LogUtils.e(TAG, "location failure.");
                listener.onFailure("location failure.", null);
                return;
            }
        }
        Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location == null) {
            LogUtils.e(TAG, "location failure.");
            listener.onFailure("location failure.", null);
            return;
        }

        double latitude = location.getLatitude();     //经度
        double longitude = location.getLongitude(); //纬度
        String url = getLocationURL(latitude, longitude);
        OkHttpUtils.ResultCallback<String> callback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                String city = WeatherJsonUtils.getCity(response);
                if(TextUtils.isEmpty(city)) {
                    LogUtils.e(TAG, "load location info failure.");
                    listener.onFailure("load location info failure.", null);
                } else {
                    listener.onSuccess(city);
                }
            }

            @Override
            public void onFailure(Exception e) {
                LogUtils.e(TAG, "load location info failure.", e);
                listener.onFailure("load location info failure.", e);
            }
        };
        OkHttpUtils.get(url,callback);
    }

    private String getLocationURL(double latitude, double longitude) {
        StringBuffer sb = new StringBuffer(Urls.INTERFACE_LOCATION);
        sb.append("?output=json").append("&referer=32D45CBEEC107315C553AD1131915D366EEF79B4");
        sb.append("&location=").append(latitude).append(",").append(longitude);
        LogUtils.d(TAG, sb.toString());
        return sb.toString();
    }
}

package com.zkch.bugly.utils;

import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zkch.bugly.R;
import com.zkch.bugly.bean.WeatherBean;

import java.util.ArrayList;
import java.util.List;

public class WeatherJsonUtils {

    /**
     *  从json字符串中获取城市
     * @param json
     * @return
     */
    public static String getCity(String json) {
        JsonParser parser =new JsonParser();
        JsonObject object =parser.parse(json).getAsJsonObject();
        JsonElement status = object.get("status");
        if (status != null  && "OK".equals(status.getAsString())) {
            JsonObject result = object.getAsJsonObject("result");
            if (result != null){
             JsonObject addressComponet =result.getAsJsonObject("addressComponent");
                if (addressComponet != null) {
                    JsonElement cityElement =addressComponet.get("city");
                    if (cityElement != null) {
                        return cityElement.getAsString().replace("市","");
                    }
                }
            }
        }
        return null;
    }

    /**
     *  获取天气信息
     */
    public static List<WeatherBean> getWeatherInfo(String json){
        List<WeatherBean> data = new ArrayList<>();
        if (TextUtils.isEmpty(json)) {
            return data;
        }
        JsonParser parser =new JsonParser();
        JsonObject object =parser.parse(json).getAsJsonObject();
        String status = object.get("status").getAsString();
        if ("1000" .equals(status)) {
            JsonArray array = object.getAsJsonObject("data").getAsJsonArray("forecast");
            for (int i = 0; i < array.size() ; i++) {
                WeatherBean bean =getWeatherFormJson(array.get(i).getAsJsonObject());
                data.add(bean);
            }
        }
        return data;
    }

    private static WeatherBean getWeatherFormJson(JsonObject asJsonObject) {
        String temp = asJsonObject.get("high").getAsString() + " " + asJsonObject.get("low").getAsString();
        String weather = asJsonObject.get("type").getAsString();
        String wind = asJsonObject.get("fengxiang").getAsString();
        String date = asJsonObject.get("date").getAsString();

        WeatherBean weatherBean = new WeatherBean();
        weatherBean.setDate(date);
        weatherBean.setTemperature(temp);
        weatherBean.setWeather(weather);
        weatherBean.setWeek(date.substring(date.length()-3));
        weatherBean.setWind(wind);
        weatherBean.setImageRes(getWeatherImage(weather));
        return weatherBean;
    }

    public static int getWeatherImage(String weather) {
        if (weather.equals("多云") || weather.equals("多云转阴") || weather.equals("多云转晴")) {
            return R.drawable.biz_plugin_weather_duoyun;
        } else if (weather.equals("中雨") || weather.equals("中到大雨")) {
            return R.drawable.biz_plugin_weather_zhongyu;
        } else if (weather.equals("雷阵雨")) {
            return R.drawable.biz_plugin_weather_leizhenyu;
        } else if (weather.equals("阵雨") || weather.equals("阵雨转多云")) {
            return R.drawable.biz_plugin_weather_zhenyu;
        } else if (weather.equals("暴雪")) {
            return R.drawable.biz_plugin_weather_baoxue;
        } else if (weather.equals("暴雨")) {
            return R.drawable.biz_plugin_weather_baoyu;
        } else if (weather.equals("大暴雨")) {
            return R.drawable.biz_plugin_weather_dabaoyu;
        } else if (weather.equals("大雪")) {
            return R.drawable.biz_plugin_weather_daxue;
        } else if (weather.equals("大雨") || weather.equals("大雨转中雨")) {
            return R.drawable.biz_plugin_weather_dayu;
        } else if (weather.equals("雷阵雨冰雹")) {
            return R.drawable.biz_plugin_weather_leizhenyubingbao;
        } else if (weather.equals("晴")) {
            return R.drawable.biz_plugin_weather_qing;
        } else if (weather.equals("沙尘暴")) {
            return R.drawable.biz_plugin_weather_shachenbao;
        } else if (weather.equals("特大暴雨")) {
            return R.drawable.biz_plugin_weather_tedabaoyu;
        } else if (weather.equals("雾") || weather.equals("雾霾")) {
            return R.drawable.biz_plugin_weather_wu;
        } else if (weather.equals("小雪")) {
            return R.drawable.biz_plugin_weather_xiaoxue;
        } else if (weather.equals("小雨")) {
            return R.drawable.biz_plugin_weather_xiaoyu;
        } else if (weather.equals("阴")) {
            return R.drawable.biz_plugin_weather_yin;
        } else if (weather.equals("雨夹雪")) {
            return R.drawable.biz_plugin_weather_yujiaxue;
        } else if (weather.equals("阵雪")) {
            return R.drawable.biz_plugin_weather_zhenxue;
        } else if (weather.equals("中雪")) {
            return R.drawable.biz_plugin_weather_zhongxue;
        } else {
            return R.drawable.biz_plugin_weather_duoyun;
        }
    }
}

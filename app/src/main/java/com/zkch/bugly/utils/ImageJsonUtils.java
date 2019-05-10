package com.zkch.bugly.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zkch.bugly.bean.ImageBean;
import java.util.ArrayList;
import java.util.List;

public class ImageJsonUtils {
    private static final String TAG = "ImageJsonUtils";
    public static List<ImageBean> readJsonImage(String s){
        List<ImageBean> dataList =new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(s).getAsJsonArray();
            for (int i = 1; i < jsonArray.size() ; i++) {
                JsonObject object = jsonArray.get(i).getAsJsonObject();
                ImageBean imageBean = JsonUtils.deserialize(object, ImageBean.class);
                dataList.add(imageBean);
            }
        } catch (Exception e) {
            LogUtils.e( TAG, "readJsonImageBeans error", e);
        }
        return  dataList;
    }
}

package com.zkch.bugly.model;
import com.zkch.bugly.api.Urls;
import com.zkch.bugly.bean.ImageBean;
import com.zkch.bugly.contract.ImageContract;
import com.zkch.bugly.utils.ImageJsonUtils;
import com.zkch.bugly.utils.OkHttpUtils;

import java.util.List;

public class ImageModelImpl implements ImageContract.ImageModel {

    @Override
    public void loadImageList(final ImageContract.OnLoadImageListListener listener) {
        String url = Urls.IMAGES_URL;
        OkHttpUtils.ResultCallback<String> loadImageCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<ImageBean> list = ImageJsonUtils.readJsonImage(response);
                listener.onSuccess(list);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFail("load image list failure.", e);
            }
        };
        OkHttpUtils.get(url,loadImageCallback);
    }
}

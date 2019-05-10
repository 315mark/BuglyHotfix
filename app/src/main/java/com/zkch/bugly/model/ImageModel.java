package com.zkch.bugly.model;

import com.zkch.bugly.contract.ImageContract;

public interface ImageModel {

    void loadImageList(ImageContract.OnLoadImageListListener listener);
}

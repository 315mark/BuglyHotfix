package com.zkch.bugly.contract;

import com.zkch.bugly.bean.ImageBean;

import java.util.List;

public interface ImageContract {

    interface ImageView{
        void addImages(List<ImageBean> list);
        void showProgress();
        void hideProgress();
        void showLoadFailMsg();
    }

    interface ImagePresenter{
        void loadImageList();
    }

    interface OnLoadImageListListener{
        void onSuccess(List<ImageBean> list);
        void onFail(String msg,Exception e);
    }

    interface ImageModel {

        void loadImageList(ImageContract.OnLoadImageListListener listener);
    }
}


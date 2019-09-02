package com.zkch.bugly.presenter;

import com.zkch.bugly.bean.ImageBean;
import com.zkch.bugly.contract.ImageContract;
import com.zkch.bugly.contract.ImageContract.ImageModel;
import com.zkch.bugly.model.ImageModelImpl;
import java.util.List;

public class ImagePresenterImpl implements ImageContract.ImagePresenter, ImageContract.OnLoadImageListListener {

    ImageModel model;
    ImageContract.ImageView mView;

    public ImagePresenterImpl(ImageContract.ImageView mView) {
        this.mView = mView;
        this.model =new ImageModelImpl();
    }

    @Override
    public void loadImageList() {
        mView.showProgress();
        model.loadImageList(this);
    }

    @Override
    public void onSuccess(List<ImageBean> list) {
        mView.addImages(list);
        mView.hideProgress();
    }

    @Override
    public void onFail(String msg, Exception e) {
        mView.hideProgress();
        mView.showLoadFailMsg();
    }
}

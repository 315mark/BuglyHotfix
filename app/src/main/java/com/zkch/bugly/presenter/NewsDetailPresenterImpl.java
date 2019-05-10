package com.zkch.bugly.presenter;

import android.content.Context;

import com.zkch.bugly.bean.NewsDetailBean;
import com.zkch.bugly.contract.NewsContract;
import com.zkch.bugly.model.NewModel;
import com.zkch.bugly.model.NewsModelImpl;

public class NewsDetailPresenterImpl implements NewsContract.NewsDetailPresenter , NewModel.OnLoadNewsDetailListener {
    NewsContract.NewsDetailView mView;
    NewModel mModel;


    public NewsDetailPresenterImpl(NewsContract.NewsDetailView mView) {
        this.mView = mView;
        mModel =new NewsModelImpl();
    }

    @Override
    public void loadNewsDetail(String s) {
        mView.showProgress();
        mModel.loadNewsDetail(s,this);
    }

    @Override
    public void onSuccess(NewsDetailBean list) {
        if (list != null) {
            mView.showNewsDetialContent(list.getBody());
        }
        mView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mView.hideProgress();
    }
}

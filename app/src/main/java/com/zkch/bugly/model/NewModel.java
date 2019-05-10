package com.zkch.bugly.model;

import com.zkch.bugly.bean.NewsBean;
import com.zkch.bugly.bean.NewsDetailBean;

import java.util.List;

public interface NewModel {

    void loadNews(String url, int type, OnLoadNewsListListener listener);

    void loadNewsDetail(String docid, OnLoadNewsDetailListener listener);

    interface  OnLoadNewsDetailListener {
        void onSuccess(NewsDetailBean list);

        void onFailure(String msg, Exception e);
    }

    interface OnLoadNewsListListener {
        void onSuccess(List<NewsBean> data);

        void onFailure(String msg, Exception e);
    }
}

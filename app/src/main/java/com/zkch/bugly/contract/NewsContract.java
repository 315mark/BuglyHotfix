package com.zkch.bugly.contract;

import com.zkch.bugly.bean.NewsBean;
import com.zkch.bugly.bean.NewsDetailBean;

import java.util.List;

public interface NewsContract {

    interface NewsView {

        void showProgress();

        void addNews(List<NewsBean> newsList);

        void hideProgress();

        void showLoadFailMsg();
    }

    interface NewsDetailView {

        void showNewsDetialContent(String newsDetailContent);

        void showProgress();

        void hideProgress();

    }

    interface NewsPresenter{
        void loadNews(int type, int page);
    }

    interface NewsDetailPresenter{
        void loadNewsDetail(String s);
    }

    interface NewModel {

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

}

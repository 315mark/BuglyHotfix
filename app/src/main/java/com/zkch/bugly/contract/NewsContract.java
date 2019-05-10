package com.zkch.bugly.contract;

import com.zkch.bugly.bean.NewsBean;

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

}

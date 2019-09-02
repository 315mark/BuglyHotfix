package com.zkch.bugly.model;

import com.zkch.bugly.api.Urls;
import com.zkch.bugly.bean.NewsBean;
import com.zkch.bugly.bean.NewsDetailBean;
import com.zkch.bugly.contract.NewsContract;
import com.zkch.bugly.fragment.NewsFragment;
import com.zkch.bugly.utils.NewsJsonUtils;
import com.zkch.bugly.utils.OkHttpUtils;
import java.util.List;

public class NewsModelImpl implements NewsContract.NewModel {
    //新闻列表加载
    @Override
    public void loadNews(String url, final int type, final OnLoadNewsListListener listener) {
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<NewsBean> dateList = NewsJsonUtils.readJsonNewsBeans(response.trim(), getId(type));
                listener.onSuccess(dateList);
            }

            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news detail info failure.",e);
            }
        };
        OkHttpUtils.get(url,loadNewsCallback);
    }

    @Override
    public void loadNewsDetail(final String docid, final OnLoadNewsDetailListener listener) {
        String url = getDetailUrl(docid);
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
           /**
              请求成功回调
               @param response
             */
            @Override
            public void onSuccess(String response) {
                NewsDetailBean detailBean = NewsJsonUtils.readJsonNewsDetailBeans(response, docid);
                listener.onSuccess(detailBean);
            }

            /**
             请求失败回调
             @param e
             */
            @Override
            public void onFailure(Exception e) {
                listener.onFailure("load news detail info failure.", e);
            }
        };
        OkHttpUtils.get(url,resultCallback);
    }

    private String getDetailUrl(String docid) {
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docid).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }

    private String getId(int type) {
        String id;
        switch (type){
            case NewsFragment.NEWS_TYPE_TOP:
                id = Urls.TOP_ID;
                break;

            case NewsFragment.NEWS_TYPE_NBA:
                 id = Urls.NBA_ID;
                  break;

            case NewsFragment.NEWS_TYPE_CARS:
                id = Urls.CAR_ID;
                break;

            case NewsFragment.NEWS_TYPE_JOKES:
                id = Urls.JOKE_ID;
                break;
            default:
                id=Urls.TOP_ID;
                break;
        }
        return id;
    }

}

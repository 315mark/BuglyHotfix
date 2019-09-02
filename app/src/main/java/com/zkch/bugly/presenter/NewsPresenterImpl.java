package com.zkch.bugly.presenter;

import com.zkch.bugly.api.Urls;
import com.zkch.bugly.bean.NewsBean;
import com.zkch.bugly.contract.NewsContract;
import com.zkch.bugly.contract.NewsContract.NewModel;
import com.zkch.bugly.fragment.NewsFragment;
import com.zkch.bugly.model.NewsModelImpl;
import com.zkch.bugly.utils.LogUtils;
import java.util.List;

public class NewsPresenterImpl implements NewsContract.NewsPresenter, NewModel.OnLoadNewsListListener {

    NewsContract.NewsView mNewsView;
    NewModel mModel;

    public NewsPresenterImpl(NewsContract.NewsView mNewsView) {
        this.mNewsView = mNewsView;
        this.mModel = new NewsModelImpl();
    }

    @Override
    public void loadNews(int type, int page) {
        String url = getUrl(type, page);
        LogUtils.d(getClass().getName(),url);
        //只有第一页或者刷新的时候才显示进度条
        if (page == 0) {
            mNewsView.showProgress();
        }
        mModel.loadNews(url,type,this);
   }

    /**
     * 根据类别和页面索引创建url
     * @param type
     * @return
     */
    public String getUrl(int type , int page){
        StringBuffer sb =new StringBuffer();
        switch (type) {
            case NewsFragment.NEWS_TYPE_TOP:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                sb.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                sb.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                sb.append(Urls.COMMON_URL).append(Urls.JOKE_ID);
                break;
            default:
                sb.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        sb.append("/").append(page).append(Urls.END_URL);
        return sb.toString();
    }

    @Override
    public void onSuccess(List<NewsBean> data) {
        mNewsView.hideProgress();
        mNewsView.addNews(data);
    }

    @Override
    public void onFailure(String msg, Exception e) {
        mNewsView.hideProgress();
        mNewsView.showLoadFailMsg();
    }
}

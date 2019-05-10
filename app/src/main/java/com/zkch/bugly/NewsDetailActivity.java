package com.zkch.bugly;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.ImageView;
import android.widget.ProgressBar;


import com.zkch.bugly.bean.NewsBean;
import com.zkch.bugly.contract.NewsContract;
import com.zkch.bugly.presenter.NewsDetailPresenterImpl;
import com.zkch.bugly.utils.ImageLoaderUtils;
import com.zkch.bugly.utils.ToolsUtil;

import org.sufficientlysecure.htmltextview.HtmlTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


public class NewsDetailActivity extends SwipeBackActivity implements NewsContract.NewsDetailView {
    @BindView(R.id.iv_inoc)
    ImageView ivInoc;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.htNewsContent)
    HtmlTextView htNewsContent;

    NewsBean NewsDate;
    NewsContract.NewsDetailPresenter mPresenter;
    SwipeBackLayout mSwipBackLayout;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        bind = ButterKnife.bind(this);

//        非继承AppCompatActivity 无法去除ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mSwipBackLayout = getSwipeBackLayout();
        mSwipBackLayout.setEdgeSize(ToolsUtil.getWidthInPx(this));
        mSwipBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        NewsDate = (NewsBean) getIntent().getSerializableExtra("news");
        mToolbarLayout.setTitle(NewsDate.getTitle());
        ImageLoaderUtils.display(this,ivInoc,NewsDate.getImgsrc());
        mPresenter = new NewsDetailPresenterImpl(this);
        mPresenter.loadNewsDetail(NewsDate.getDocid());
    }

    @Override
    public void showNewsDetialContent(String newsDetailContent) {
        htNewsContent.setHtmlFromString(newsDetailContent,new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != Unbinder.EMPTY) {
            bind.unbind();//解除绑定
        }
    }
}

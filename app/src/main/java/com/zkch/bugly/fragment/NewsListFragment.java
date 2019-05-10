package com.zkch.bugly.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zkch.bugly.NewsDetailActivity;
import com.zkch.bugly.R;
import com.zkch.bugly.Urls;
import com.zkch.bugly.adapter.NewsAdapter;
import com.zkch.bugly.base.BaseFragment;
import com.zkch.bugly.bean.NewsBean;
import com.zkch.bugly.contract.NewsContract;
import com.zkch.bugly.presenter.NewsPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class NewsListFragment extends BaseFragment implements NewsContract.NewsView, SwipeRefreshLayout.OnRefreshListener {

    LinearLayoutManager mManager;
    List<NewsBean> mData;
    NewsContract.NewsPresenter mPresenter;
    NewsAdapter mAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private int pageIndex = 0;

    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_newslist;
    }

    @Override
    protected void init() {
        mPresenter = new NewsPresenterImpl(this);
        mType = getArguments().getInt("type");

        //设置下拉刷新进度条颜色
        swipeRefresh.setColorSchemeResources(R.color.primary,
                R.color.primary_dark, R.color.primary_light,
                R.color.accent);
        swipeRefresh.setOnRefreshListener(this);

        //初始化RecyclerView
        recyclerView.setHasFixedSize(true);
        mManager =new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new NewsAdapter();
        mAdapter.setEmptyView(R.layout.empty_img,recyclerView); // 数据为空时加载空
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();
    }

    private RecyclerView.OnScrollListener mOnScrollListener =new RecyclerView.OnScrollListener(){
        //用来标记是否正在向最后一个滑动
        boolean isSlidingToLast = false;

        //最后一个可见的位置
        private int lastVisibleItem;
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //加载更多
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount() ) {
                mPresenter.loadNews(mType, pageIndex + Urls.PAZE_SIZE);
            }
        }
    };

    private NewsAdapter.OnItemClickListener mOnItemClickListener =new NewsAdapter.OnItemClickListener(){

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            NewsBean date = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", date);
            View transitionView = view.findViewById(R.id.ivNews);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            transitionView, getString(R.string.transition_news_img));
            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
        }
    };

    @Override
    public void onRefresh() {
        pageIndex = 0 ;
        if (mData != null) {
            mData.clear();
        }
        mPresenter.loadNews(mType,pageIndex);
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void addNews(List<NewsBean> newsList) {
        if (mData == null) {
            mData =new ArrayList<>();
        }
        mData.addAll(newsList);
        if (pageIndex == 0) {
            mAdapter.addData(mData);
        }else{
            //如果没有更多数据 隐藏foot布局
            if (newsList == null || newsList.size() == 0) {
                mAdapter.setEmptyView(R.layout.empty_img,recyclerView);
            }
            mAdapter.notifyDataSetChanged();

        }
        pageIndex += Urls.PAZE_SIZE;
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        if(pageIndex == 0) {
           // mAdapter.isShowFooter(false);
            mAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? recyclerView.getRootView() : getActivity().findViewById(R.id.draw_layout);
        Snackbar.make(view, getString(R.string.load_fail), Snackbar.LENGTH_SHORT).show();
    }

}

package com.zkch.bugly.fragment;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zkch.bugly.R;
import com.zkch.bugly.adapter.ImageAdapter;
import com.zkch.bugly.base.BaseFragment;
import com.zkch.bugly.bean.ImageBean;
import com.zkch.bugly.contract.ImageContract;
import com.zkch.bugly.presenter.ImagePresenterImpl;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

/**
 * 图片文本混合展示
 */
public class ImageFragment extends BaseFragment implements ImageContract.ImageView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    ImageContract.ImagePresenter mPresenter;
    StaggeredGridLayoutManager gridLayoutManager;
    List<ImageBean> mData;
    ImageAdapter mAdapter;


    private int lastVisibleItem;

    /**
     * protected  修饰指继承该类可执行方法
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image;
    }

    @Override
    protected void init() {
        mPresenter =new ImagePresenterImpl(this);
        swipeRefresh.setColorSchemeResources(R.color.primary,R.color.primary_dark,R.color.primary_light,R.color.accent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadImageList();
            }
        });

        recyclerView.setHasFixedSize(true);
        // 线性布局不好 图片太大
        // manager = new LinearLayoutManager(getActivity());
        //recyclerView.setLayoutManager(manger)
        //设置网格布局
        gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ImageAdapter();

        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem +1 ==mAdapter.getItemCount()) {  //滚动状态闲置
                    //加载更多
                    Snackbar.make(getActivity().findViewById(R.id.draw_layout), getString(R.string.image_hit), Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mPresenter.loadImageList();
    }


    @Override
    public void addImages(List<ImageBean> list) {
        if (mData == null) {
            mData =new ArrayList<>();
        }
        mData.clear();
        mData.addAll(list);
        mAdapter.setNewData(mData);
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg() {
        if (isAdded()) {
            View view =getActivity() == null ? recyclerView.getRootView() : getActivity().findViewById(R.id.draw_layout);
            Snackbar.make(view,getString(R.string.load_fail),Snackbar.LENGTH_SHORT).show();
        }
    }
}

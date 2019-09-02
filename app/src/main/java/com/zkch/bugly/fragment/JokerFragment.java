package com.zkch.bugly.fragment;

import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zkch.bugly.R;
import com.zkch.bugly.adapter.JokerAdapter;
import com.zkch.bugly.base.BaseFragment;
import com.zkch.bugly.bean.Joker;
import com.zkch.bugly.contract.JokerContract;
import com.zkch.bugly.presenter.JokerPresenterImpl;
import java.util.List;
import androidx.navigation.Navigation;
import butterknife.BindView;

public class JokerFragment extends BaseFragment implements JokerContract.JokerView {

    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.recyclerView)
    RecyclerView recycleview;

    JokerContract.JokerPresenter mPresenter;
    JokerAdapter mAdapter;

    /**
     * protected  修饰指继承该类可执行方法
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_joker;
    }

    @Override
    protected void init() {
        mPresenter =new JokerPresenterImpl(this);

        mPresenter.getJokerList("29","1");
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        recycleview.setLayoutManager(manager);
        mAdapter = new JokerAdapter();
        recycleview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle =new Bundle();
                String url= mAdapter.getData().get(position).getVideouri();
                bundle.putString("url",url);
                Navigation.findNavController(view).navigate(R.id.videoFragment,bundle);
            }
        });
    }

    @Override
    public void showJokerList(List<Joker.DataBean> list) {
        progress.setVisibility(View.VISIBLE);
        mAdapter.addData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getJokerListFinish() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void getJokerListErro(String msg) {
        Toast.makeText(mApplication, msg, Toast.LENGTH_SHORT).show();
        Log.i(getTag(), msg);
    }

}

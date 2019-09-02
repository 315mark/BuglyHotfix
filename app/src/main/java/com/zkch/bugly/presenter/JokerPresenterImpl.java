package com.zkch.bugly.presenter;

import android.util.Log;

import com.zkch.bugly.bean.Joker;
import com.zkch.bugly.contract.JokerContract;
import com.zkch.bugly.model.JokerModelImpl;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class JokerPresenterImpl implements JokerContract.JokerPresenter {

    JokerContract.JokerModel mModel;
    JokerContract.JokerView mView;

    public JokerPresenterImpl(JokerContract.JokerView mView) {
        this.mView = mView;
        mModel = new JokerModelImpl();
    }

    @Override
    public void getJokerList(String type, String page) {
        mModel.getJokerList(type, page, new Observer<Joker>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Joker joker) {
                List<Joker.DataBean> data = joker.getData();
                Log.i(getClass().getName(), "onNext: " + data);
                mView.showJokerList(data);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(getClass().getName(), "onError: " + e);
                mView.getJokerListErro(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(getClass().getName(), "onComplete " );
                mView.getJokerListFinish();
            }
        });
    }
}

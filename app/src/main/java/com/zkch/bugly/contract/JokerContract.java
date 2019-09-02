package com.zkch.bugly.contract;

import com.zkch.bugly.bean.Joker;

import java.util.List;

import io.reactivex.Observer;

public interface JokerContract {

    interface JokerView{
        void showJokerList(List<Joker.DataBean> list);
        void getJokerListFinish();
        void getJokerListErro(String msg);

    }

    interface JokerPresenter{
        void getJokerList(String type,String page);
    }

    interface JokerModel{
        void getJokerList(String type, String page, Observer<Joker> observer);
    }
}

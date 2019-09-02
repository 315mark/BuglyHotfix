package com.zkch.bugly.model;

import com.zkch.bugly.api.ApiService;
import com.zkch.bugly.bean.Joker;
import com.zkch.bugly.contract.JokerContract;
import com.zkch.bugly.utils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class JokerModelImpl implements JokerContract.JokerModel {

    @Override
    public void getJokerList(String type, String page, Observer<Joker> observer) {
        ApiService apiService= RetrofitFactory.getJokerApi();
        Observable<Joker> observable= apiService.getJokerList(type,page);
        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(observer);
    }
}

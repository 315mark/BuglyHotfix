package com.zkch.bugly.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.zkch.bugly.MyApp;
import com.zkch.bugly.Speech.TTSUtility;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView{

    protected MyApp mApp;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        bind = ButterKnife.bind(this);
        mApp = (MyApp) getApplication();
       //  TTSUtility.getInstance(this);
        //组件依赖注入全局级别的Application component
        init();
    }

    protected abstract int getLayoutResID();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != Unbinder.EMPTY) {
            bind.unbind();//解除绑定
        }
        // 退出时释放连接
     //  TTSUtility.stopSpeaking();
      // TTSUtility.destroy();
    }


    /**
     * 显示进度对话框
     */
    @Override
    public void showLoading() {

    }

    /**
     * 关闭进度对话框
     */
    @Override
    public void dismissLoading() {

    }

    /**
     * 显示错误信息
     *
     * @param displayMessage
     */
    @Override
    public void showError(String displayMessage) {

    }


}

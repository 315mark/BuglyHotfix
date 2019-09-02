package com.zkch.bugly;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.tencent.bugly.Bugly;


public class MyApp extends Application {
    private static Context mContext;
    // 语音合成对象
    private SpeechSynthesizer mTts;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Bugly.init(this,"81d9f97eee",false);
        // 应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
        // 如在Application中调用初始化，需要在Mainifest中注册该Applicaiton
        // 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
        // 参数间使用半角“,”分隔。
        // 设置你申请的应用appid,请勿在'='与appid之间添加空格及空转义符
        // 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误
        // 以下语句用于设置日志开关（默认开启），设置成false时关闭语音云SDK日志打印
        // Setting.setShowLog(false);

        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5cd91125");
        // 初始化合成对象
      //  TTSUtility.getInstance(this);
        //TTSUtility.setParam();
        // 播放收款成功提示音
        //  TTSUtility.getInstance(getContext()).speaking("编程使我快乐");
        //  TTSUtility.getInstance().release();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);

        //安装tinker
//        Beta.installTinker();
        //设置监听器 补丁包应用成功后杀进程  重启app
      /*  Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String s) {
                *//**补丁包应用成功回调，在这里杀进程，重启app，完成热更新。
                 否则需要等待用户下次自己主动杀进程重启后才能完成更新*//*
                restartApp();
            }

            @Override
            public void onDownloadReceived(long l, long l1) {

            }

            @Override
            public void onDownloadSuccess(String s) {
                Toast.makeText(getApplicationContext(), "onDownloadSuccess "+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String s) {
                Toast.makeText(getApplicationContext(), "onDownloadFailure "+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplySuccess(String s) {
                Toast.makeText(getApplicationContext(), "onApplySuccess "+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String s) {
                Toast.makeText(getApplicationContext(), "onApplyFailure "+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {

            }
        };*/
    }

    public static Context getContext()
    {
        return mContext;
    }

    //重启app
    private void restartApp() {
        Intent intent =new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Process.killProcess(Process.myPid());
    }


}

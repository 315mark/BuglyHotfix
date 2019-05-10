package com.zkch.bugly;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;

public class MyApp extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Bugly.init(this,"81d9f97eee",false);
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

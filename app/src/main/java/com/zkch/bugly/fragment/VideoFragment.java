package com.zkch.bugly.fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.zkch.bugly.R;
import com.zkch.bugly.base.BaseFragment;

import butterknife.BindView;

public class VideoFragment extends BaseFragment {

    @BindView(R.id.video)
    VideoView video;

    /**
     * protected  修饰指继承该类可执行方法
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init() {
        video.setMediaController(new MediaController(getContext()));
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getContext(), "播放完成了", Toast.LENGTH_SHORT).show();
            }
        });
        startPlay();
    }

    private void startPlay(){
        Bundle intent = getArguments();
        String url=intent.getString("url");
        if(null!=url) {
            video.setVideoURI(Uri.parse(url));
            video.start();
        }
    }
}

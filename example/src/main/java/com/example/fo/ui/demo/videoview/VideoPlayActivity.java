package com.example.fo.ui.demo.videoview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.fish.example.R;

import java.io.File;

/**
 * 作者：漆可 on 2016/8/15 10:59
 */
public class VideoPlayActivity extends Activity
{

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(com.xianyu.resource.R.layout.video_activity);

        File videoFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "Download", "a.3gp");

        if (!videoFile.exists())
        {
            Log.e("videoPlayer", "文件:" + videoFile.getAbsolutePath() + "不存在");
        }
        Log.d("videoPlayer", "文件:" + videoFile.getAbsolutePath());


        mVideoView = (VideoView) findViewById(R.id.vv_video);
//        MediaController mediaController=new MediaController(this);
//        mVideoView.setMediaController(mediaController);
        mVideoView.requestFocus();
        mVideoView.setVideoPath(videoFile.getAbsolutePath());

        mVideoView.start();
    }
}

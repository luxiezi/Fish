package com.xianyu.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;

import com.fish.xianyu.R;

/**
 * Created by lu on 2016/8/4.
 */
public  class WelcomeActivity extends Activity {
    Handler handler=new Handler();
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        AnimatorSet valueAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(WelcomeActivity.this,
                R.animator.welcome_anim);
        valueAnimator.start();

        handler.postDelayed(new Runnable() {//使用handler的postDelayed实现延时跳转
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                finish();;
            }
        },2000); //2秒后跳转到主页面
    }
}

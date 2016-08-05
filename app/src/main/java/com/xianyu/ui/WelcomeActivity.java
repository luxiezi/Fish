package com.xianyu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.fish.xianyu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lu on 2016/8/4.
 */
public class WelcomeActivity extends Activity {
    Handler handler = new Handler();
    Animation animation;

    LinearLayout ll_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ButterKnife.bind(this);

        ll_anim = (LinearLayout) findViewById(R.id.ll_bg_welcome);

//        AnimatorSet valueAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(WelcomeActivity.this,
//                R.animator.welcome_anim);
//        valueAnimator.start();

        animation = AnimationUtils.loadAnimation(this, R.anim.welcome_anim);
        animation.setFillAfter(true);
        ll_anim.startAnimation(animation);

        //2秒后跳转到主页面
        startCountdown(3000);
    }

    private void startCountdown(int time) {
        handler.postDelayed(new Runnable() {//使用handler的postDelayed实现延时跳转
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();

            }
        }, time);
    }
}

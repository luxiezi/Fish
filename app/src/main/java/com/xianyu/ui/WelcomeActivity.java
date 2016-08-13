package com.xianyu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
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
    @BindView(R.id.ll_bg_welcome)
    LinearLayout ll_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        ButterKnife.bind(this);

//        //使用XML布局实现动画
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.welcome_anim);
//        animation.setFillAfter(true);  //保留动画最终结果，不还原到动画前
//        ll_bg.startAnimation(animation);

       //java代码实现动画
        AnimationSet animationSet=new AnimationSet(true);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.5f,1);//透明度的变化
//        TranslateAnimation translateAnimation=new TranslateAnimation(   //平移
//                TranslateAnimation.RELATIVE_TO_SELF,0f,
//                TranslateAnimation.RELATIVE_TO_SELF,0.5f,
//                TranslateAnimation.RELATIVE_TO_SELF,0f,
//                TranslateAnimation.RELATIVE_TO_SELF,0.5f
//        );
        ScaleAnimation scaleAnimation=new ScaleAnimation(//缩放
                0.6f,1f,0.6f,1f,
                ScaleAnimation.RELATIVE_TO_SELF,0.5f,
                ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        RotateAnimation rotateAnimation=new RotateAnimation(0,360,//旋转
                RotateAnimation.RELATIVE_TO_SELF,0.5f,
                RotateAnimation.RELATIVE_TO_SELF,0.5f);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
//        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(3000);
        animationSet.setFillAfter(true);//保留动画最终结果，不还原到动画前
        ll_bg.startAnimation(animationSet);

        handler.postDelayed(new Runnable() {//使用handler的postDelayed实现延时跳转
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
                ;
            }
        }, 3000); //3秒后跳转到主页面
    }
}

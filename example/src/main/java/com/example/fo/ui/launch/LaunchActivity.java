package com.example.fo.ui.launch;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.common.base.BaseActivity;
import com.fish.example.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 启动页面
 * 作者：漆可 on 2016/8/5 10:09
 */
public class LaunchActivity extends BaseActivity
{
    @BindView(R.id.ll_bg_welcome)
    LinearLayout ll_bg;

    @Override
    public int getContentView()
    {
        return R.layout.welcome;
    }

    @Override
    public void initView()
    {
        ButterKnife.bind(this);

        startAnimation1();
    }

    private void startAnimation1()
    {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.welcome_anim);
        animation.setFillAfter(true);  //保留动画最终结果，不还原到动画前
        ll_bg.startAnimation(animation);
    }
}

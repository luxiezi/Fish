package com.example.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * activity基类
 */
public abstract class BaseActivity extends FragmentActivity
{

    @Override
    protected void onCreate(@Nullable Bundle bundle)
    {
        super.onCreate(bundle);

        setContentView(getContentView());

        initView();
        registerLister();
    }

    /**
     * 设置监听事件
     */
    public void registerLister()
    {
    }

    /**
     * view的初始化
     */
    public void initView()
    {
    }

    public abstract int getContentView();

}

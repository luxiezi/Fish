package com.example.fo.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;

import com.example.fo.ui.home.fragment.Main_fragment;
import com.fish.example.R;

import java.util.List;

/**
 * User: 漆可
 * Date: 2016-08-13 16:35
 */
public abstract class BaseFragmentActivity extends FragmentActivity
{
    protected FragmentManager mFragmentManager;
    protected int content_id;

    /**
     * 当前显示的页面
     */
    protected Fragment mCurrnetFragment;
    private static final String KEY_CURRENT_FRAGMENT_TAG = "key_current_fragment_tag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        content_id = getContentId();
        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null)
        {
            initFragment(Main_fragment.class);
        } else
        {
            String showTag = savedInstanceState.getString(KEY_CURRENT_FRAGMENT_TAG);

            List<Fragment> fragments = mFragmentManager.getFragments();
            for (Fragment fragment : fragments)
            {
                if (TextUtils.equals(showTag, fragment.getTag()))
                {
                    mFragmentManager.beginTransaction()
                            .show(fragment)
                            .commitAllowingStateLoss();

                    mCurrnetFragment = fragment;
                } else
                {
                    mFragmentManager.beginTransaction()
                            .hide(fragment)
                            .commitAllowingStateLoss();
                }
            }
        }
    }

    protected abstract int getContentViewId();

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_CURRENT_FRAGMENT_TAG, mCurrnetFragment.getTag());
    }

    /**
     * 在页面初始化的时候显示的fragment
     *
     * @param showFragmentClass 需要显示的fragment的字节码
     */
    protected void initFragment(Class<? extends Fragment> showFragmentClass)
    {
        if (showFragmentClass == null)
        {
            return;
        }

        Fragment showFragment = createFragment(showFragmentClass);

        if (showFragment == null)
        {
            Log.e("initFragment", "the showFragment is null");
            return;
        }

        mFragmentManager.beginTransaction()
                .add(content_id, showFragment, showFragmentClass.getName())
                .commitAllowingStateLoss();

        mCurrnetFragment = showFragment;
    }

    //创建fragment
    private Fragment createFragment(Class<? extends Fragment> showFragmentClass)
    {
        Fragment showFragment = null;
        try
        {
            showFragment = showFragmentClass.newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return showFragment;
    }

    /**
     * 切换fragment的显示隐藏
     *
     * @param showFragment 需要显示的页面
     */
    protected void replaceFragment(Fragment showFragment)
    {
        if (showFragment == null
                || showFragment == mCurrnetFragment
                || mCurrnetFragment == null
                )
        {
            return;
        }

        //如果需要显示的fragment已经添加到acitivity
        if (showFragment.isAdded())
        {
            //直接显示隐藏
            mFragmentManager.beginTransaction()
                    .hide(mCurrnetFragment)
                    .show(showFragment)
                    .commitAllowingStateLoss();
        } else
        {
            //先add在隐藏mCurrnetFragment
            mFragmentManager.beginTransaction()
                    .hide(mCurrnetFragment)
                    .add(content_id, showFragment, showFragment.getClass().getName())
                    .commitAllowingStateLoss();
        }

        mCurrnetFragment = showFragment;
    }

    /**
     * 根据字节码获取fragmentMananger队列中的fragment实例
     *
     * @param clazz 字节码
     * @return clazz对应的 fragment实例
     */
    protected Fragment findFragmentByClass(Class<? extends Fragment> clazz)
    {
        //直接去队列中去取
        Fragment fragment = mFragmentManager.findFragmentByTag(clazz.getName());
        if (fragment == null)
        {
            //不存在：创建对象
            fragment = createFragment(clazz);
        }

        return fragment;
    }

    /**
     * 设置fragme容器的id
     *
     * @return fragme容器的id
     */
    protected abstract int getContentId();

}

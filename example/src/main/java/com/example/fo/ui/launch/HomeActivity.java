package com.example.fo.ui.launch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.fo.ui.home.fragment.Car_fragment;
import com.example.fo.ui.home.fragment.Dolla_fragment;
import com.example.fo.ui.home.fragment.Main_fragment;
import com.example.fo.ui.home.fragment.Me_fragment;
import com.example.fo.ui.home.fragment.Search_fragment;
import com.fish.example.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 第一种方法：
 * 所有页面全部创建出来放入activity中，然后只显示一个
 * 控制他们的显示隐藏切换显示
 * <p>
 * User: 漆可
 * Date: 2016-08-13 15:23
 */
public class HomeActivity extends FragmentActivity
{
    @BindView(com.xianyu.resource.R.id.radiogroup_fragment)
    RadioGroup radiogroup_Fragment;

    int content_id = R.id.framelayout;
    private FragmentManager mFragmentManager;

    //当前显示的页面
    private Fragment mCurrnetFragment;
    private static final String KEY_CURRENT_FRAGMENT_TAG = "key_current_fragment_tag";

    private Car_fragment mCar_fragment;
    private Dolla_fragment mDolla_fragment;
    private Main_fragment mMain_fragment;
    private Me_fragment mMe_fragment;
    private Search_fragment mSearch_fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        radiogroup_Fragment.check(R.id.main_rb_fragment);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null)
        {
            initFragment();
        } else
        {
            mMain_fragment = (Main_fragment) mFragmentManager.findFragmentByTag(Main_fragment.class.getName());
            mMe_fragment = (Me_fragment) mFragmentManager.findFragmentByTag(Me_fragment.class.getName());
            mCar_fragment = (Car_fragment) mFragmentManager.findFragmentByTag(Car_fragment.class.getName());
            mSearch_fragment = (Search_fragment) mFragmentManager.findFragmentByTag(Search_fragment.class.getName());
            mDolla_fragment = (Dolla_fragment) mFragmentManager.findFragmentByTag(Dolla_fragment.class.getName());

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


        setListener();
    }

    private void initFragment()
    {
        mCar_fragment = new Car_fragment();
        mDolla_fragment = new Dolla_fragment();
        mMain_fragment = new Main_fragment();
        mMe_fragment = new Me_fragment();
        mSearch_fragment = new Search_fragment();

        mFragmentManager.beginTransaction()
                .add(content_id, mMain_fragment, Main_fragment.class.getName())
                .add(content_id, mCar_fragment, Car_fragment.class.getName()).hide(mCar_fragment)
                .add(content_id, mDolla_fragment, Dolla_fragment.class.getName()).hide(mDolla_fragment)
                .add(content_id, mMe_fragment, Me_fragment.class.getName()).hide(mMe_fragment)
                .add(content_id, mSearch_fragment, Search_fragment.class.getName()).hide(mSearch_fragment)
                .commit();

        mCurrnetFragment = mMain_fragment;
    }

    private void setListener()
    {
        radiogroup_Fragment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                Fragment showFragment = null;
                switch (checkedId)
                {
                    case R.id.main_rb_fragment: //主页

                        showFragment = mMain_fragment;

                        break;
                    case R.id.search_rb_fragment: //分类

                        showFragment = mSearch_fragment;

                        break;
                    case R.id.dolla_rb_fragment: //福利

                        showFragment = mDolla_fragment;

                        break;
                    case R.id.car_rb_fragment: //购物车

                        showFragment = mCar_fragment;

                        break;
                    case R.id.me_rb_fragment: //我的

                        showFragment = mMe_fragment;

                        break;
                }

                replaceFragment(showFragment);
            }
        });

    }

    private void replaceFragment(Fragment showFragment)
    {
        if (showFragment == null
                || showFragment == mCurrnetFragment
                || mCurrnetFragment == null
                )
        {
            return;
        }

        mFragmentManager.beginTransaction()
                .hide(mCurrnetFragment)
                .show(showFragment)
                .commitAllowingStateLoss();

        mCurrnetFragment = showFragment;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CURRENT_FRAGMENT_TAG, mCurrnetFragment.getTag());
    }
}

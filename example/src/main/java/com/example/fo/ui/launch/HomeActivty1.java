package com.example.fo.ui.launch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;

import com.example.fo.base.BaseFragmentActivity;
import com.example.fo.ui.home.fragment.Car_fragment;
import com.example.fo.ui.home.fragment.Dolla_fragment;
import com.example.fo.ui.home.fragment.Main_fragment;
import com.example.fo.ui.home.fragment.Me_fragment;
import com.example.fo.ui.home.fragment.Search_fragment;
import com.fish.example.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * User: 漆可
 * Date: 2016-08-13 16:27
 */
public class HomeActivty1 extends BaseFragmentActivity
{
    @BindView(com.xianyu.resource.R.id.radiogroup_fragment)
    RadioGroup radiogroup_Fragment;

    private static final String KEY_CURRENT_FRAGMENT_TAG = "key_current_fragment_tag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        radiogroup_Fragment.check(R.id.main_rb_fragment);

        setListener();
    }

    @Override
    protected int getContentViewId()
    {
        return R.layout.activity_main;
    }

    private void setListener()
    {
        radiogroup_Fragment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                Class<? extends Fragment> showFragmentClass = null;
                switch (checkedId)
                {
                    case R.id.main_rb_fragment: //主页

                        showFragmentClass = Main_fragment.class;

                        break;
                    case R.id.search_rb_fragment: //分类

                        showFragmentClass = Search_fragment.class;

                        break;
                    case R.id.dolla_rb_fragment: //福利

                        showFragmentClass = Dolla_fragment.class;

                        break;
                    case R.id.car_rb_fragment: //购物车

                        showFragmentClass = Car_fragment.class;

                        break;
                    case R.id.me_rb_fragment: //我的

                        showFragmentClass = Me_fragment.class;

                        break;
                }

                Fragment showFragment = findFragmentByClass(showFragmentClass);

                replaceFragment(showFragment);
            }
        });
    }

    @Override
    protected int getContentId()
    {
        return R.id.framelayout;
    }
}

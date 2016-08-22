package com.xianyu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.xianyu.resource.R;
import com.xianyu.ui.main_fragment.Main_it_fragment;
import com.xianyu.ui.main_fragment.Main_life_fragment;
import com.xianyu.ui.main_fragment.Main_makeup_fragment;
import com.xianyu.ui.main_fragment.Main_man_fragment;
import com.xianyu.ui.main_fragment.Main_mother_fragment;
import com.xianyu.ui.main_fragment.Main_new_fragment;
import com.xianyu.ui.main_fragment.Main_recre_fragment;
import com.xianyu.ui.main_fragment.Main_select_fragment;
import com.xianyu.ui.main_fragment.Main_shoes_fragment;
import com.xianyu.ui.main_fragment.Main_sports_fragment;
import com.xianyu.ui.main_fragment.Main_women_fragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lu on 2016/8/2.
 */
public class Main_fragment extends Fragment {

    FragmentManager fragmentManager;
    int content_id;

    @BindView(R.id.main_framelayout)
    FrameLayout main_Framelayout;
    @BindView(R.id.main_radiogroup_fragment)
    RadioGroup main_radiogroup_fragment;

    /**
     * 当前显示的页面
     */
    protected Fragment mCurrnetFragment;
    private static final String MAIN_KEY_CURRENT_FRAGMENT_TAG = "key_current_fragment_tag";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_layout, container, false);
        ButterKnife.bind(this, view);

        content_id=getContentId();

        fragmentManager=getChildFragmentManager();
        if (savedInstanceState==null){

            initFragment(Main_select_fragment.class);

        }else {

            List<Fragment> fragments=fragmentManager.getFragments();

            String showTag=savedInstanceState.getString(MAIN_KEY_CURRENT_FRAGMENT_TAG);

            for(Fragment fragment: fragments){
                if (TextUtils.equals(showTag,fragment.getTag())){

                    fragmentManager.beginTransaction()
                            .show(fragment)
                            .commitAllowingStateLoss();

                    mCurrnetFragment=fragment;

                }else{
                    fragmentManager.beginTransaction()
                            .hide(fragment)
                            .commitAllowingStateLoss();
                }
            }

        }

        main_radiogroup_fragment= (RadioGroup) view.findViewById(R.id.main_radiogroup_fragment);
        main_radiogroup_fragment.check(R.id.select_btn);
        setListener();

        return view;
    }

    private void setListener() {
        main_radiogroup_fragment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            Class< ? extends Fragment> showFragmentClass=null;

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId){
                    case R.id.select_btn://精选
                        showFragmentClass=Main_select_fragment.class;
                        break;
                    case R.id.new_btn://上新
                        showFragmentClass=Main_new_fragment.class;
                        break;
                    case R.id.women_btn://丽人街
                        showFragmentClass=Main_women_fragment.class;
                        break;
                    case R.id.life_btn://生活馆
                        showFragmentClass=Main_life_fragment.class;
                        break;
                    case R.id.man_btn://男人志
                        showFragmentClass=Main_man_fragment.class;
                        break;
                    case R.id.it_btn://数码馆
                        showFragmentClass=Main_it_fragment.class;
                        break;
                    case R.id.sports_btn://爱运动
                        showFragmentClass=Main_sports_fragment.class;
                        break;
                    case R.id.makeup_btn://聚美妆
                        showFragmentClass=Main_makeup_fragment.class;
                        break;
                    case R.id.mother_btn://妈妈说
                        showFragmentClass=Main_mother_fragment.class;
                        break;
                    case R.id.shoes_btn://鞋包馆
                        showFragmentClass=Main_shoes_fragment.class;
                        break;
                    case R.id.recre_btn://文娱馆
                        showFragmentClass=Main_recre_fragment.class;
                        break;
                }

                Fragment showFragment=findFragmentByClass(showFragmentClass);
                replaceFragment(showFragment);

            }
        });

    }

    private void replaceFragment(Fragment showFragment) {

        if (showFragment==null
                ||showFragment==mCurrnetFragment
                ||mCurrnetFragment==null){
            return;
        }

        if (showFragment.isAdded()){

            fragmentManager.beginTransaction()
                    .hide(mCurrnetFragment)
                    .show(showFragment)
                    .commitAllowingStateLoss();
        }else{

            fragmentManager.beginTransaction()
                    .add(content_id,showFragment,showFragment.getClass().getName())
                    .commitAllowingStateLoss();
        }

        mCurrnetFragment=showFragment;

    }

    private Fragment findFragmentByClass(Class<? extends Fragment> clazz) {

       Fragment showFragment=fragmentManager.findFragmentByTag(clazz.getName());

        if (showFragment==null){

            showFragment=createFragment(clazz);
        }
        return showFragment;
    }

    private void initFragment(Class< ? extends Fragment> showFragmentClass) {
        if (showFragmentClass==null){
            return;
        }
        Fragment showFragment=createFragment(showFragmentClass);

        if (showFragment==null){

            Log.e("initFragment","showFragment is null !");
        }

        fragmentManager.beginTransaction()
                .add(content_id,showFragment,showFragmentClass.getName())
                .commitAllowingStateLoss();

        mCurrnetFragment=showFragment;
    }

    private Fragment createFragment(Class<? extends Fragment> showFragmentClass) {

        Fragment showFragment=null;
        try {

            showFragment=showFragmentClass.newInstance();

        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return showFragment;
    }

    private int getContentId() {
        return R.id.main_framelayout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(MAIN_KEY_CURRENT_FRAGMENT_TAG,mCurrnetFragment.getTag());

    }
}

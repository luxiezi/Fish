package com.xianyu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.xianyu.adapter.ViewPagerAdapter;
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
import com.xianyu.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lu on 2016/8/30.
 */
public class Main_fragment extends Fragment implements Runnable{

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
    private  int currentId=R.id.select_btn;

    private ViewPager viewPager;
    private List<ImageView> views;
    private List<View> dotviews;
    LinearLayout dotcontaint;
    int olddotindex = 0;
    private int currentItem;
    private boolean loop = true;

    private MyScrollView mScrollView;
    private View tv_float;
    private int mTitleTopAndHeight;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loop=true;
        View view = inflater.inflate(R.layout.srolltitle_layout, container, false);
        ButterKnife.bind(this, view);

        ScrollViewSetting(view);

        content_id=getContentId();

        fragmentManager=getChildFragmentManager();
        if (savedInstanceState==null){

            initFragment(Main_select_fragment.class);

        }else {

            getFragmentFromSaved(savedInstanceState);

        }

        main_radiogroup_fragment= (RadioGroup) view.findViewById(R.id.main_radiogroup_fragment);
//        main_radiogroup_fragment.check(R.id.select_btn);
        main_radiogroup_fragment.check(currentId);
        setListener(main_radiogroup_fragment);

        //轮播图
        Carousel(view);
        isThread(loop);

        return view;
    }

    private void getFragmentFromSaved(Bundle savedInstanceState) {
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

    private void ScrollViewSetting(View view) {
        tv_float = view.findViewById(R.id.top_layout_flaoat);
        final View tv_title = view.findViewById(R.id.top_layout_mindle);

        int measuredHeightAndState = tv_title.getMeasuredHeightAndState();
        int measuredWidthAndState = tv_title.getMeasuredWidthAndState();
        tv_title.measure(measuredWidthAndState, measuredHeightAndState);

        mTitleTopAndHeight = tv_title.getTop() + tv_title.getHeight();

        tv_title.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                mTitleTopAndHeight = tv_title.getTop();
                Log.d("top", mTitleTopAndHeight + "");
                tv_title.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        tv_float.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                mScrollView.onTouchEvent(event);
                return false;
            }
        });

        mScrollView = (MyScrollView) view.findViewById(R.id.sv_main);

        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener()
        {
            @Override
            public void onScrollChanged()
            {
                int scrollY = mScrollView.getScrollY();
                if (scrollY <= mTitleTopAndHeight)
                {
                    tv_float.setVisibility(View.INVISIBLE);
                    tv_title.setVisibility(View.VISIBLE);
                } else
                {

                    tv_float.setVisibility(View.VISIBLE);
                    tv_title.setVisibility(View.INVISIBLE);

                    main_radiogroup_fragment= (RadioGroup) tv_float.findViewById(R.id.main_radiogroup_fragment);
                    main_radiogroup_fragment.check(currentId);
                    setListener(main_radiogroup_fragment);

                }
            }
        });
    }

    //轮播图设置
    private void Carousel(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.vp);
        views = new ArrayList<ImageView>();

        ImageView view1 = new ImageView(getActivity());
        Glide.with(this)
                .load("http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg")
                .into(view1);
        ImageView view2 = new ImageView(getActivity());
        Glide.with(this)
                .load("http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg")
                .into(view2);
        ImageView view3 = new ImageView(getActivity());
        Glide.with(this)
                .load("http://pic18.nipic.com/20111215/577405_080531548148_2.jpg")
                .into(view3);
        ImageView view4 = new ImageView(getActivity());
        Glide.with(this)
                .load("http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg")
                .into(view4);
        ImageView view5 = new ImageView(getActivity());
        Glide.with(this)
                .load("http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg")
                .into(view5);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);//只放两张图会报错

        dotcontaint = (LinearLayout) view.findViewById(R.id.dotcontaint);
        getDotList();

        dotviews.get(0).setBackgroundResource(R.drawable.dot_focus);
        viewPager.setOffscreenPageLimit(0);

        viewPager.setAdapter(new ViewPagerAdapter(views));
        viewPager.setCurrentItem(0);//
    }

    private void getDotList() {
        // TODO Auto-generated method stub
        dotviews = new ArrayList<View>();
        //循环图片数组，创建对应数量的dot
        for(int i=0;i<views.size();i++){
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.dots_layout, null);//加载布局
            View dot = view.findViewById(R.id.dotView);//得到布局中的dot点组件
            //收集dot
            dotviews.add(dot);
            //把布局添加到线性布局
            dotcontaint.addView(view);
        }

    }

    private void setListener(RadioGroup main_radiogroup_fragment) {

        main_radiogroup_fragment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            Class< ? extends Fragment> showFragmentClass=null;

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId){
                    case R.id.select_btn://精选
                        showFragmentClass=Main_select_fragment.class;
                        currentId=R.id.select_btn;
                        break;
                    case R.id.new_btn://上新
                        showFragmentClass=Main_new_fragment.class;
                        currentId=R.id.new_btn;
                        break;
                    case R.id.women_btn://丽人街
                        showFragmentClass=Main_women_fragment.class;
                        currentId=R.id.women_btn;
                        break;
                    case R.id.life_btn://生活馆
                        showFragmentClass=Main_life_fragment.class;
                        currentId=R.id.life_btn;
                        break;
                    case R.id.man_btn://男人志
                        showFragmentClass=Main_man_fragment.class;
                        currentId=R.id.man_btn;
                        break;
                    case R.id.it_btn://数码馆
                        showFragmentClass=Main_it_fragment.class;
                        currentId=R.id.it_btn;
                        break;
                    case R.id.sports_btn://爱运动
                        showFragmentClass=Main_sports_fragment.class;
                        currentId=R.id.sports_btn;
                        break;
                    case R.id.makeup_btn://聚美妆
                        showFragmentClass=Main_makeup_fragment.class;
                        currentId=R.id.makeup_btn;
                        break;
                    case R.id.mother_btn://妈妈说
                        showFragmentClass=Main_mother_fragment.class;
                        currentId=R.id.mother_btn;
                        break;
                    case R.id.shoes_btn://鞋包馆
                        showFragmentClass=Main_shoes_fragment.class;
                        currentId=R.id.shoes_btn;
                        break;
                    case R.id.recre_btn://文娱馆
                        showFragmentClass=Main_recre_fragment.class;
                        currentId=R.id.recre_btn;
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
                    .hide(mCurrnetFragment)
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

    @Override
    public void onPause() {
        super.onPause();
        loop=false;
    }

    @Override
    public void onResume() {
        super.onResume();
        loop=true;
    }


    public void isThread(boolean loop){
        if (loop){
            new Thread(this).start();
        }
    }
    @Override
    public void run() {
        while (loop)
        {
            try {

                Thread.sleep(2000);
                if (getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
//                                viewPager.setOffscreenPageLimit(0);

                            currentItem = viewPager.getCurrentItem();

                            viewPager.setCurrentItem(++currentItem);

                            //切换选中的圆点
                            dotviews.get(olddotindex).setBackgroundResource(R.drawable.dot_nomal);//设置上次选中的圆点为不选中
                            dotviews.get(currentItem % views.size()).setBackgroundResource(R.drawable.dot_focus);//设置当前选中的圆点为选中

                            olddotindex = currentItem % views.size();
                        }
                    });
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
    }
}

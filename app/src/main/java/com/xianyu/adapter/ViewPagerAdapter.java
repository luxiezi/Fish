package com.xianyu.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by lu on 2016/8/22.
 */
public class ViewPagerAdapter extends PagerAdapter{

    private List<ImageView> list;

    public ViewPagerAdapter(List<ImageView> views)
    {
        // TODO Auto-generated constructor stub
        list = views;
    }

    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
//        return list.size();
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        // TODO Auto-generated method stub

        ImageView view = list.get(position % list.size());
//        Log.i("position_remove","----------"+position);
        container.removeView(view);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        // TODO Auto-generated method stub

        ImageView view = list.get(position % list.size());

        //解决只有两张图时的问题，但是图片显示会只出现一下就消失
//        ViewGroup parent = (ViewGroup) view.getParent();
//
//        if (parent != null) {
//
//            parent.removeView(parent.getChildAt(position % list.size()-1));
//
//        }
//
        container.addView(view);
//        Log.i("position_added","----------"+position);

        return view;
    }
}

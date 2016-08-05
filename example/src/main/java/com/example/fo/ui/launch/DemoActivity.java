package com.example.fo.ui.launch;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.example.common.base.BaseActivity;
import com.example.fo.adapter.DemoAdapter;
import com.example.fo.bean.RateInfo;
import com.fish.example.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：漆可 on 2016/8/5 15:42
 */
public class DemoActivity extends BaseActivity
{

    private ListView mListView;

    @Override
    public int getContentView()
    {
        return R.layout.demo_activity;
    }

    @Override
    public void initView()
    {
        super.initView();

        mListView = (ListView) findViewById(R.id.lv_demo);
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle)
    {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(bundle);

        //模拟数据
        List<RateInfo> mRateInfos = new ArrayList<>();
        mRateInfos.add(new RateInfo("货币名称", "现汇买入价", "现钞买入价", "现汇卖出价", "现钞卖出价", "折算价", "发布时间"));

        mRateInfos.add(new RateInfo("美元", "50.21", "10.01", "20.22", "30.22", "20.98", "2016-8-4 14:40:10"));
        mRateInfos.add(new RateInfo("外星币", "50.21", "10.01", "20.22", "", "20.98", "2016-8-4 14:40:10"));
        mRateInfos.add(new RateInfo("测试数据", "", "10.01", "20.22", "30.22", "20.98", "2016-8-4 14:40:10"));
        mRateInfos.add(new RateInfo("测试超长超长文字", "50.21", "10.01", "20.22", "30.22", "20.98", "2016-8-4 14:40:10"));
        mRateInfos.add(new RateInfo("短", "50.21", "10.01", "20.22", "", "20.98", "2016-8-4 14:40:10"));

        DemoAdapter mAdapter = new DemoAdapter(mRateInfos);

        mListView.setAdapter(mAdapter);
    }
}

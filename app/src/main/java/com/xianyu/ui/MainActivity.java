package com.xianyu.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.xianyu.resource.R;
import com.xianyu.ui.fragment.Car_fragment;
import com.xianyu.ui.fragment.Dolla_fragment;
import com.xianyu.ui.fragment.Main_fragment;
import com.xianyu.ui.fragment.Me_fragment;
import com.xianyu.ui.fragment.Search_fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {
    private String TAG = "MainActivity";
    @BindView(R.id.radiogroup_fragment)
    RadioGroup radiogroup_Fragment;
    @BindView(R.id.main_rb_fragment)
    RadioButton main_rb_ragment;
    @BindView(R.id.search_rb_fragment)
    RadioButton search_rb_fragment;
    @BindView(R.id.dolla_rb_fragment)
    RadioButton dolla_rb_fragment;
    @BindView(R.id.car_rb_fragment)
    RadioButton car_rb_fragment;
    @BindView(R.id.me_rb_fragment)
    RadioButton me_rb_fragment;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;

    Main_fragment mainTabFragment;
    Search_fragment searchTabFragment;
    Me_fragment meTabFragment;
    Dolla_fragment dollaTabFragment;
    Car_fragment carTabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
//        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
        showFragment(R.id.main_rb_fragment);
        radiogroup_Fragment.setOnCheckedChangeListener(this);
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (index) {
            case R.id.main_rb_fragment:
                if (mainTabFragment == null) {
                    mainTabFragment = new Main_fragment();
                    ft.add(R.id.framelayout, mainTabFragment);
                } else {
                    ft.replace(R.id.framelayout, mainTabFragment);
                }
                break;
            case R.id.search_rb_fragment:
                if (searchTabFragment == null)
                    searchTabFragment = new Search_fragment();
                ft.replace(R.id.framelayout, searchTabFragment);
                break;
            case R.id.dolla_rb_fragment:
                if (dollaTabFragment == null)
                    dollaTabFragment = new Dolla_fragment();
                ft.replace(R.id.framelayout, dollaTabFragment);
                break;
            case R.id.car_rb_fragment:
                if (carTabFragment == null)
                    carTabFragment = new Car_fragment();
                ft.replace(R.id.framelayout, carTabFragment);
                break;
            case R.id.me_rb_fragment:
                if (meTabFragment == null)
                    meTabFragment = new Me_fragment();
                ft.replace(R.id.framelayout, meTabFragment);
                break;
        }
        ft.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        showFragment(checkedId);
    }
}

package com.fish;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.fish.fragment.Car_fragment;
import com.fish.fragment.Dolla_fragment;
import com.fish.fragment.Main_fragment;
import com.fish.fragment.Me_fragment;
import com.fish.fragment.Search_fragment;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener {

    private String TAG="MainActivity";
    Main_fragment mainTabFragment;
    Search_fragment searchTabFragment;
    Me_fragment meTabFragment;
    Dolla_fragment dollaTabFragment;
    Car_fragment carTabFragment;
    RadioGroup radioGroup;
    RadioButton main_rb;
    RadioButton search_rb;
    RadioButton dolla_rb;
    RadioButton car_rb;
    RadioButton me_rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView() {
        radioGroup= (RadioGroup) findViewById(R.id.radiogroup_fragment);
        main_rb= (RadioButton) findViewById(R.id.main_rb_fragment);
        search_rb= (RadioButton) findViewById(R.id.search_rb_fragment);
        dolla_rb= (RadioButton) findViewById(R.id.dolla_rb_fragment);
        car_rb= (RadioButton) findViewById(R.id.car_rb_fragment);
        me_rb= (RadioButton) findViewById(R.id.me_rb_fragment);

//        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
        showFragment(R.id.main_rb_fragment);
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (index){
            case R.id.main_rb_fragment:
                if (mainTabFragment==null) {
                    mainTabFragment = new Main_fragment();
                    ft.add(R.id.framelayout, mainTabFragment);
                }else{
                    ft.replace(R.id.framelayout, mainTabFragment);
                }
                break;
            case R.id.search_rb_fragment:
                if (searchTabFragment==null)
                    searchTabFragment=new Search_fragment();
                ft.replace(R.id.framelayout,searchTabFragment);
                break;
            case R.id.dolla_rb_fragment:
                if (dollaTabFragment==null)
                    dollaTabFragment=new Dolla_fragment();
                ft.replace(R.id.framelayout,dollaTabFragment);
                break;
            case R.id.car_rb_fragment:
                if (carTabFragment==null)
                    carTabFragment=new Car_fragment();
                ft.replace(R.id.framelayout,carTabFragment);
                break;
            case R.id.me_rb_fragment:
                if (meTabFragment==null)
                    meTabFragment=new Me_fragment();
                ft.replace(R.id.framelayout,meTabFragment);
                break;
        }
        ft.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        showFragment(checkedId);
    }
}

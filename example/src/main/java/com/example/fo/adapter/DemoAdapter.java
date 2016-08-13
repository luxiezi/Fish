package com.example.fo.adapter;

import android.widget.TextView;

import com.example.common.base.CommAdapter;
import com.example.common.base.ViewHolder;
import com.example.fo.bean.RateInfo;
import com.fish.example.R;

import java.util.List;

/**
 * 作者：漆可 on 2016/8/5 15:52
 */
public class DemoAdapter extends CommAdapter<RateInfo>
{
    public DemoAdapter(List<RateInfo> listDate)
    {
        super(listDate);
    }

    @Override
    protected void setUi(ViewHolder holder, int position)
    {
        RateInfo item = getItem(position);

        TextView tv_name =  holder.getItemView(R.id.Name);
        tv_name.setText(item.getName());

        TextView tv_rate =  holder.getItemView(R.id.BuyingRate);
        tv_rate.setText(item.getBuyingRate());

        TextView tv_price =  holder.getItemView(R.id.BuyingPrice);
        tv_price.setText(item.getBuyingPrice());

        TextView tv_sell_rate =  holder.getItemView(R.id.SellingRate);
        tv_sell_rate.setText(item.getSellingRate());

        TextView tv_sell_price =  holder.getItemView(R.id.SellingPrice);
        tv_sell_price.setText(item.getSellingPrice());


        TextView tv_discount_price =  holder.getItemView(R.id.DiscountPrice);
        tv_discount_price.setText(item.getDiscountPrice());

        TextView tv_time =  holder.getItemView(R.id.PublishTime);
        tv_time.setText(item.getPublishTime());
    }

    @Override
    protected int getLayoutResource()
    {
        return R.layout.demo_item;
    }
}

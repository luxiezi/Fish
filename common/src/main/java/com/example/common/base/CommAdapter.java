package com.example.common.base;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 通用适配器
 * BaseCommAdapter.java Created on: 2016-8-5
 * @author漆可
 * @param <T>
 *            需要填充的数据
 */
public abstract class CommAdapter<T> extends BaseAdapter
{

    protected List<T> mListDate;
    protected SimpleDateFormat mDateFormat;

    @SuppressLint("SimpleDateFormat")
    public CommAdapter(List<T> listDate)
    {
        super();
        this.mListDate = listDate;

        mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public int getCount()
    {
        return mListDate == null ? 0 : mListDate.size();
    }

    @Override
    public T getItem(int position)
    {
        return mListDate.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = ViewHolder.getInstance(convertView, getLayoutResource(),
                parent.getContext());

        setUi(holder, position);

        return holder.getConvertView();
    }

    /**
     * 设置UI
     * 
     * @param holder
     * @param position
     */
    protected abstract void setUi(ViewHolder holder, int position);

    /**
     * 获取布局文件的id
     * 
     * @return
     */
    protected abstract int getLayoutResource();

}

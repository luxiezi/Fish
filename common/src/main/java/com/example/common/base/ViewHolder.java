package com.example.common.base;


import android.content.Context;
import android.util.SparseArray;
import android.view.View;

public class ViewHolder
{

    private View mConvertView;

    @SuppressWarnings("unused") private Context mContext;

    private SparseArray<View> mViews;

    public ViewHolder()
    {
        super();
    }

    private ViewHolder(int layoutResource, Context context)
    {
        mConvertView = View.inflate(context, layoutResource, null);
        mConvertView.setTag(this);

        mViews = new SparseArray<View>();

        this.mContext = context;
    }

    public static ViewHolder getInstance(View convertView, int layoutResource, Context context)
    {
        if (convertView == null)
        {
            return new ViewHolder(layoutResource, context);
        } else
        {
            return (ViewHolder) convertView.getTag();
        }
    }

    /**
     * 获取根条目view
     * @return
     */
    public View getConvertView()
    {
        return mConvertView;
    }

    /**
     * 获取根目录的view
     * @param resourceId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getItemView(int resourceId)
    {
        T view = (T) mViews.get(resourceId);
        if (view == null)
        {
            view = (T) mConvertView.findViewById(resourceId);
            mViews.put(resourceId, view);
        }

        return view;
    }
}

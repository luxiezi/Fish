package com.example.fo.http;

import android.text.TextUtils;
import android.util.Log;

import com.example.fo.bean.Result.BaseResult;
import com.example.fo.bean.request.BaseRequest;
import com.example.fo.bean.request.CategoryRequest;
import com.example.fo.utils.JsonUtils;

/**
 * User: 漆可
 * Date: 2016-08-27 18:14
 */
public class HttpHelper
{
    private final Class<BaseResult> mRequestClazz;
    private String mUrl;
    private BaseRequest mRequest;
    private CallBack mCallBack;

    public interface CallBack
    {
        void onSuccess(BaseResult result);

        void onFail(String error);
    }

    public HttpHelper(String url, Class resultClazz, CallBack callBack)
    {
        this.mUrl = url;
        this.mCallBack = callBack;
        this.mRequestClazz = resultClazz;
    }

    public void call(final CategoryRequest request)
    {

        this.mRequest = request;

        Log.d("", "finalUrl = " + mUrl);
        Log.d("", "request = " + request.toString());

        OkhttptUtils.getInstance().get(mUrl, request, new com.example.fo.http.CallBack()
        {
            @Override
            public void onSuccess(String result)
            {
                doResult(result, mCallBack);
            }

            @Override
            public void onFail(String error)
            {
                if (mCallBack != null)
                {
                    mCallBack.onFail(error);
                }
            }
        });
    }

    private void doResult(String result, CallBack callBack)
    {
        BaseResult baseResult = JsonUtils.praseObject(result, mRequestClazz);

        /**
         * TODO:统一处理数据
         */


        if (baseResult == null)
        {
            mCallBack.onFail("数据解析失败");
            return;
        }
        mCallBack.onSuccess(baseResult);

        //        {
        //            code:1001,
        //                    mess:请求成功
        //            body:
        //        }

        //        BaseResult baseResult = JsonUtils.praseObject(result, BaseResult.class);
        //
        //        if (baseResult == null)
        //        {
        //            callBack.onFail("解析失败");
        //
        //            return;
        //        }
        //
        //        //请求传
        //        if (TextUtils.equals("1001", baseResult.code))
        //        {
        //            callBack.onSuccess(baseResult);
        //        }
    }



}

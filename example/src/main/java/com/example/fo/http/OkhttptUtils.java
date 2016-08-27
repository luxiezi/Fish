package com.example.fo.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.fo.bean.request.BaseRequest;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 * User: 漆可
 * Date: 2016-08-27 16:38
 */
public class OkhttptUtils
{
    private Handler mHandler;

    private final OkHttpClient mClient;

    private static OkhttptUtils mOkhpptUtils;

    public OkhttptUtils()
    {
        mClient = new OkHttpClient();

        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkhttptUtils getInstance()
    {
        if (mOkhpptUtils == null)
        {
            synchronized (OkhttptUtils.class)
            {
                mOkhpptUtils = new OkhttptUtils();
            }
        }

        return mOkhpptUtils;
    }

    /**
     * get方法
     *
     * @param url 请求地址
     */
    public void get(String url, BaseRequest requestObject, final CallBack callBack)
    {
        url = generateRequestUrlForGet(url, requestObject);
        final Request request = new Request.Builder()
                .url(url)
                .build();

        newCall(callBack, request);
    }

    private String generateRequestUrlForGet(String url, BaseRequest
            requestObject)
    {
        StringBuffer sb = new StringBuffer(url);
        sb.append("?");

        if (requestObject == null)
        {
            return url;
        }

        Field[] fields = requestObject.getClass().getFields();
        for (Field field : fields)
        {
            try
            {
                Object value = field.get(requestObject);
                if (!field.isSynthetic() || value != null)
                {
                    sb.append(field.getName())
                            .append("=")
                            .append(value.toString())
                            .append("&");
                }
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void newCall(final CallBack callBack, Request request)
    {
        mClient.newCall(request)
                .enqueue(new Callback()
                {
                    @Override
                    public void onFailure(Request request, final IOException e)
                    {
                        final String error = e.getMessage();
                        Log.e("result", e.getMessage());

                        runOnUI(error, callBack, false);
                    }

                    @Override
                    public void onResponse(Response response) throws IOException
                    {
                        String result = response.body().string();
                        Log.d("result", result);
                        runOnUI(result, callBack, true);
                    }
                });
    }

    private void runOnUI(final String message, final CallBack callBack, final boolean sucess)
    {
        if (callBack != null)
        {
            mHandler.post(new Runnable()
            {
                @Override
                public void run()
                {
                    if (sucess)
                    {
                        callBack.onSuccess(message);
                    } else
                    {
                        callBack.onFail(message);
                    }
                }
            });
        }
    }
}

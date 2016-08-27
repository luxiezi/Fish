package com.example.fo.http;

/**
 * 网络请求回调
 * User: 漆可
 * Date: 2016-08-27 17:35
 */
public interface CallBack
{
    void onSuccess(String result);

    void onFail(String error);
}

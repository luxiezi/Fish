package com.example.fo.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

/**
 * User: 漆可
 * Date: 2016-08-27 18:25
 */
public class JsonUtils
{
    public static <T> T praseObject(String content, Class<T> clazz)
    {
        if (TextUtils.isEmpty(content))
        {
            return null;
        }

        T o = null;
        try
        {
            o = new Gson().fromJson(content, clazz);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return o;
    }
}

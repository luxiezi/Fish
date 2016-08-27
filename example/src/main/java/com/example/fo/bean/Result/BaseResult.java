package com.example.fo.bean.Result;

import com.example.fo.base.BaseObject;

import java.util.Objects;

/**
 * User: 漆可
 * Date: 2016-08-27 18:24
 */
public class BaseResult extends BaseObject
{
    //        {
    //            code:1001,
    //                    message:请求成功
    //            body:
    //        }

    public String code;
    public String message;
    public Object body;
}

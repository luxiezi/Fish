package com.example.fo.base;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * User: 漆可
 * Date: 2016-08-27 18:09
 */
public class BaseObject implements Serializable
{

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("======>>>" + getClass().getName() + "\n");

        Field[] fields = getClass().getFields();
        for (Field field : fields)
        {
            try
            {
                sb.append(field.getName())
                        .append(" = ")
                        .append(field.get(this))
                        .append("\n");
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}

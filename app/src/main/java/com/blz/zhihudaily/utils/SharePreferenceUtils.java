package com.blz.zhihudaily.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by BuLingzhuang
 * on 2016/5/12
 * E-mail bulingzhuang@foxmail.com
 */
public class SharePreferenceUtils {

    private static SharedPreferences sp;

    private static void getSp(Context context) {
        if (sp == null)
        {
            sp = context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }
    }

    /**
     *
     * @param context
     * @param key
     * @param value
     * @return 是否保存成功
     */
    public static boolean setValue(Context context, String key, Object value)
    {
        getSp(context);
        SharedPreferences.Editor edit = sp.edit();
        if (value instanceof String)
        {
            return edit.putString(key, (String) value).commit();
        } else if (value instanceof Boolean)
        {
            return edit.putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Float)
        {
            return edit.putFloat(key, (Float) value).commit();
        } else if (value instanceof Integer)
        {
            return edit.putInt(key, (Integer) value).commit();
        } else if (value instanceof Long)
        {
            return edit.putLong(key, (Long) value).commit();
        } else if (value instanceof Set)
        {
            new IllegalArgumentException("Value can not be Set object!");
            return false;
        }
        return false;
    }

    public static boolean getBoolean(Context context, String key)
    {
        getSp(context);
        return sp.getBoolean(key, false);
    }

    public static boolean getBooleanDefaultTrue(Context context, String key)
    {
        getSp(context);
        return sp.getBoolean(key, true);
    }

    public static String getString(Context context, String key)
    {
        getSp(context);
        return sp.getString(key, "");
    }

    public static Float getFloat(Context context, String key)
    {
        getSp(context);
        return sp.getFloat(key, 0f);
    }

    public static int getInt(Context context, String key)
    {
        getSp(context);
        return sp.getInt(key, 0);
    }

    public static long getLong(Context context, String key)
    {
        getSp(context);
        return sp.getLong(key, 0);
    }

}

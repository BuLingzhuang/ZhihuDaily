package com.blz.zhihudaily.utils;

import com.blz.zhihudaily.services.BaseService;


import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by 卜令壮
 * on 2016/3/28
 * E-mail q137617549@qq.com
 */
public class HttpUtils {
    private static BaseService sBaseService;
    static {
        sBaseService = new Retrofit.Builder().baseUrl("http://news-at.zhihu.com/api/4/").addConverterFactory(GsonConverterFactory.create()).build().create(BaseService.class);
    }
    public static BaseService getBaseService(){
        return sBaseService;
    }
}

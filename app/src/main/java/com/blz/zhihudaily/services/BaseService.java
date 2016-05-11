package com.blz.zhihudaily.services;

import com.blz.zhihudaily.entities.LatestListEntity;
import com.blz.zhihudaily.entities.SplashScreenEntity;
import com.blz.zhihudaily.entities.StoryDetailEntityDownload;
import com.blz.zhihudaily.entities.StoryEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 卜令壮
 * on 2016/3/28
 * E-mail q137617549@qq.com
 */
public interface BaseService {
    /*首页最新带轮播：'http://news-at.zhihu.com/api/4/news/latest'
    上拉加载更多条目：'http://news.at.zhihu.com/api/4/news/before/yyyyMMdd' (后面是日期)
    点击打开对应条目：'http://news-at.zhihu.com/api/4/news/id'(对应的id)
    开始启动页的大图：'http://news-at.zhihu.com/api/4/start-image/1920*1080'
    侧拉菜单栏列表：'http://news-at.zhihu.com/api/4/themes'
    点击对应菜单栏条目加载：'http://news-at.zhihu.com/api/4/theme/id'(后跟id)*/

    /*首页最新带轮播：'http://news-at.zhihu.com/api/3/news/latest'
    上拉加载更多条目：'http://news.at.zhihu.com/api/4/news/before/yyyyMMdd' (后面是日期)
    点击打开对应条目：'http://news-at.zhihu.com/api/3/news/id'(对应的id)
    开始启动页的大图：'http://news-at.zhihu.com/api/4/start-image/1920*1080'
    侧拉菜单栏列表：'http://news-at.zhihu.com/api/4/themes'
    点击对应菜单栏条目加载：'http://news-at.zhihu.com/api/4/theme/id'(后跟id)*/
    @GET("news/latest")
    Call<LatestListEntity> getLatestList();

    @GET("news/before/{date}")
    Call<LatestListEntity> getLatestList(@Path("date") String date);

    @GET("start-image/1920*1080")
    Call<SplashScreenEntity> getSplashScreen();

    @GET("news/{id}")
    Call<StoryDetailEntityDownload> getStoryDetail(@Path("id") String id);
}

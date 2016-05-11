package com.blz.zhihudaily.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.blz.zhihudaily.R;
import com.blz.zhihudaily.entities.LatestListEntity;
import com.blz.zhihudaily.entities.StoryDetailEntity;
import com.blz.zhihudaily.entities.StoryDetailEntityDownload;
import com.blz.zhihudaily.entities.StoryEntity;
import com.blz.zhihudaily.ui.ViewPagerSlowScroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by 卜令壮
 * on 2016/4/12
 * E-mail q137617549@qq.com
 */
public class Tools {
    public static void showLogE(Object obj, String str) {
        if (Constants.DEBUG) {
            Log.e(obj.getClass().getSimpleName(), str);
        }
    }

    public static ArrayList<StoryEntity> changeSotries2Story(List<LatestListEntity.StoriesEntity> list) {
        ArrayList<StoryEntity> storyList = new ArrayList<>();
        for (LatestListEntity.StoriesEntity entity : list) {
            storyList.add(new StoryEntity(entity.getImages().get(0), entity.getType(), entity.getId(), entity.getGa_prefix(), entity.getTitle()));
        }
        return storyList;
    }

//    public static StoryDetailEntity changeStoryDetailDownload2StoryDetail(StoryDetailEntityDownload entity) {
//        String js = null;
//        if (entity.getJs().size() != 0) {
//            js = entity.getJs().get(0);
//        }
//        String css = null;
//        if (entity.getCss().size() != 0) {
//            css = entity.getCss().get(0);
//        }
//        return new StoryDetailEntity(entity.getBody(), entity.getImage_source(), entity.getTitle(), entity.getImage(), entity.getShare_url(), js, entity.getGa_prefix(), null, entity.getType(), entity.getId(), css);
//    }

    public static void snackAppear(String text, Context context, View view) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG);
        //白底的SnackBar样式的方法
        Snackbar.SnackbarLayout snackBarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            snackBarLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary, null));
        } else {
            snackBarLayout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
        ((TextView) snackBarLayout.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }

    //获取当前日期
    public static int getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR) * 10000;
        int month = (calendar.get(Calendar.MONTH) + 1) * 100;
        int date = calendar.get(Calendar.DATE);
        return year + month + date;
    }

    //利用反射设置ViewPager自动滑动速度
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void setViewPagerScrollSpeed(ViewPager viewPager, int speed){
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            ViewPagerSlowScroller viewPagerSlowScroller = new ViewPagerSlowScroller(viewPager.getContext(), new OvershootInterpolator(0.6f));
            field.set(viewPager,viewPagerSlowScroller);
            viewPagerSlowScroller.setDuration(speed);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

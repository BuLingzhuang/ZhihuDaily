package com.blz.zhihudaily.interfaces.views;

import android.content.Context;
import android.view.View;

import com.blz.zhihudaily.entities.LatestListEntity;

/**
 * Created by BuLingzhuang
 * on 2016/4/22
 * E-mail bulingzhuang@foxmail.com
 */
public interface MainView {
    void updateStoryList(LatestListEntity entity);
    void updateError(String text);
    void updateNavigationList();
    void showRefresh(boolean b);
}

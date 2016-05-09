package com.blz.zhihudaily.interfaces.presenters;

/**
 * Created by BuLingzhuang
 * on 2016/4/22
 * E-mail bulingzhuang@foxmail.com
 */
public interface MainPresenter {
    void getNavigationItem(String tag);
    void getStoryItem();
    void getStoryItem(boolean b, String date);
}

package com.blz.zhihudaily.interfaces.interactors;

import android.content.Context;

import com.blz.zhihudaily.entities.SplashScreenEntity;

/**
 * Created by 卜令壮
 * on 2016/3/29
 * E-mail q137617549@qq.com
 */
public interface SplashScreenInteractor {
    void setData(Context context);
    SplashScreenEntity getData();
    void startRunnable(OnAnimFinishedListener listener);
    interface OnAnimFinishedListener{
        void finish();
    }
}

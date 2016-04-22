package com.blz.zhihudaily.interfaces.views;

import android.content.Context;

import com.blz.zhihudaily.entities.SplashScreenEntity;

/**
 * Created by 卜令壮
 * on 2016/3/29
 * E-mail q137617549@qq.com
 */
public interface SplashScreenView {
    void navigateToMain();
    void startAnim(SplashScreenEntity splashScreen);
    Context getContext();
}

package com.blz.zhihudaily.ui;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * Created by BuLingzhuang
 * on 2016/5/11
 * E-mail bulingzhuang@foxmail.com
 */
public class ViewPagerSlowScroller extends Scroller {
    private int mDuration;

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public ViewPagerSlowScroller(Context context) {
        super(context);
    }

    public ViewPagerSlowScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public ViewPagerSlowScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}

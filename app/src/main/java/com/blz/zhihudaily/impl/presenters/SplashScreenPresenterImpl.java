package com.blz.zhihudaily.impl.presenters;

import com.blz.zhihudaily.entities.SplashScreenEntity;
import com.blz.zhihudaily.impl.interactors.SplashScreenInteractorImpl;
import com.blz.zhihudaily.interfaces.interactors.SplashScreenInteractor;
import com.blz.zhihudaily.interfaces.presenters.SplashScreenPresenter;
import com.blz.zhihudaily.interfaces.views.SplashScreenView;

/**
 * Created by 卜令壮
 * on 2016/3/29
 * E-mail q137617549@qq.com
 */
public class SplashScreenPresenterImpl implements SplashScreenPresenter, SplashScreenInteractor.OnAnimFinishedListener {
    private SplashScreenView mView;
    private SplashScreenInteractor mInteractor;

    public SplashScreenPresenterImpl(SplashScreenView view) {
        mView = view;
        mInteractor = new SplashScreenInteractorImpl();
    }

    @Override
    public void start() {
        SplashScreenEntity data = mInteractor.getData();
        mView.startAnim(data);
        mInteractor.startRunnable(this);
        mInteractor.setData(mView.getContext());
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void finish() {
        mView.navigateToMain();
    }
}

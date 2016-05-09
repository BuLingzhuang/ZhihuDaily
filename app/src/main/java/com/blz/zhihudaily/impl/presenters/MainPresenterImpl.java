package com.blz.zhihudaily.impl.presenters;

import com.blz.zhihudaily.entities.LatestListEntity;
import com.blz.zhihudaily.impl.interactors.MainInteractorImpl;
import com.blz.zhihudaily.interfaces.interactors.MainInteractor;
import com.blz.zhihudaily.interfaces.presenters.MainPresenter;
import com.blz.zhihudaily.interfaces.views.MainView;
import com.blz.zhihudaily.utils.Tools;

/**
 * Created by BuLingzhuang
 * on 2016/4/22
 * E-mail bulingzhuang@foxmail.com
 */
public class MainPresenterImpl implements MainPresenter, MainInteractor.CallResponseListener {
    private MainView mView;
    private MainInteractor mMainInteractor;

    public MainPresenterImpl(MainView view) {
        mView = view;
        mMainInteractor = new MainInteractorImpl();
    }

    @Override
    public void getNavigationItem(String tag) {

    }

    @Override
    public void getStoryItem() {
        mView.showRefresh(true);
        mMainInteractor.getData(this);
    }

    @Override
    public void getStoryItem(boolean b, String date) {
        mView.showRefresh(b);
        mMainInteractor.getData(this,b,date);
    }

    @Override
    public void myResponse(LatestListEntity entity) {
        mView.updateStoryList(entity);
    }

    @Override
    public void myError(Throwable t) {
        mView.updateError(t.getLocalizedMessage());
    }
}

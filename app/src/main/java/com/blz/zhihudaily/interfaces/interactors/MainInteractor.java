package com.blz.zhihudaily.interfaces.interactors;

import com.blz.zhihudaily.entities.LatestListEntity;

/**
 * Created by BuLingzhuang
 * on 2016/4/22
 * E-mail bulingzhuang@foxmail.com
 */
public interface MainInteractor {
    void getData(CallResponseListener listener);
    void getData(CallResponseListener listener,boolean b, String date);
    interface CallResponseListener{
        void myResponse(LatestListEntity entity);
        void myError(Throwable t);
    }
}

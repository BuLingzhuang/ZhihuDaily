package com.blz.zhihudaily.impl.interactors;

import android.content.Context;

import com.blz.zhihudaily.entities.LatestListEntity;
import com.blz.zhihudaily.interfaces.interactors.MainInteractor;
import com.blz.zhihudaily.services.BaseService;
import com.blz.zhihudaily.utils.HttpUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BuLingzhuang
 * on 2016/5/9
 * E-mail bulingzhuang@foxmail.com
 */
public class MainInteractorImpl implements MainInteractor, Callback<LatestListEntity> {

    private CallResponseListener mListener;

    @Override
    public void getData(CallResponseListener listener) {
        getData(listener, true, null);
    }

    @Override
    public void getData(CallResponseListener listener, boolean b, String date) {
        mListener = listener;
        BaseService baseService = HttpUtils.getBaseService();
        Call<LatestListEntity> recyclerCall;
        if (date != null) {
            recyclerCall = baseService.getLatestList(date);
        } else {
            recyclerCall = baseService.getLatestList();
        }
        recyclerCall.enqueue(this);
    }

    @Override
    public void onResponse(Response<LatestListEntity> response) {
        mListener.myResponse(response.body());
    }

    @Override
    public void onFailure(Throwable t) {
        mListener.myError(t);
    }
}

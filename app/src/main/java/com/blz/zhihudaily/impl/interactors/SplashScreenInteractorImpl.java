package com.blz.zhihudaily.impl.interactors;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.blz.zhihudaily.dao.SplashScreenEntityDao;
import com.blz.zhihudaily.entities.SplashScreenEntity;
import com.blz.zhihudaily.interfaces.interactors.SplashScreenInteractor;
import com.blz.zhihudaily.utils.DBUtils;
import com.blz.zhihudaily.utils.HttpUtils;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 卜令壮
 * on 2016/3/29
 * E-mail q137617549@qq.com
 */
public class SplashScreenInteractorImpl implements SplashScreenInteractor, Callback<SplashScreenEntity> {
    private static final String TAG = SplashScreenInteractorImpl.class.getSimpleName();
    Context mContext;
    boolean hasTable;
    @Override
    public void setData(Context context) {
        mContext = context;
        //访问网络查数据，存入数据库
        HttpUtils.getBaseService().getSplashScreen().enqueue(this);
    }

    @Override
    public SplashScreenEntity getData() {
        //从数据库中查数据，给图片赋地址
        List<SplashScreenEntity> splashScreenEntities = DBUtils.getDaoSession().getSplashScreenEntityDao().loadAll();
        SplashScreenEntity splashScreenEntity = null;
        if (splashScreenEntities.size() >=1){
            splashScreenEntity = splashScreenEntities.get(0);
        }
        return splashScreenEntity;
    }

    @Override
    public void startRunnable(final OnAnimFinishedListener listener) {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.finish();
            }
        },3000);
    }

    @Override
    public void onResponse(Response<SplashScreenEntity> response) {
        SplashScreenEntity splashScreenEntity = response.body();
        SplashScreenEntityDao splashScreenEntityDao = DBUtils.getDaoSession().getSplashScreenEntityDao();
        splashScreenEntityDao.deleteAll();
        splashScreenEntityDao.insertOrReplaceInTx(splashScreenEntity);
        Log.e(TAG, "网络访问成功，图片uri："+splashScreenEntity.getImg());
    }

    @Override
    public void onFailure(Throwable t) {
        Toast.makeText(mContext,"网络错误",Toast.LENGTH_SHORT).show();
    }
}

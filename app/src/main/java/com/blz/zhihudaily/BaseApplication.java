package com.blz.zhihudaily;

import android.app.Application;

import com.blz.zhihudaily.utils.DBUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.picasso.Picasso;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by 卜令壮
 * on 2016/3/28
 * E-mail q137617549@qq.com
 */
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
//        Fresco.initialize(this);
        DBUtils.initialize(this);
        ShareSDK.initSDK(this);
    }
}

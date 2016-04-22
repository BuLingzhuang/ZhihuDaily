package com.blz.zhihudaily.utils;

import android.content.Context;

import com.blz.zhihudaily.dao.DaoMaster;
import com.blz.zhihudaily.dao.DaoSession;

/**
 * Created by 卜令壮
 * on 2016/3/29
 * E-mail q137617549@qq.com
 */
public class DBUtils {
    private static DaoSession sDaoSession;
    public static void initialize(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, Constants.DB_NAME, null);
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
        sDaoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoSession(){
        return sDaoSession;
    };
}

package com.blz.zhihudaily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.blz.zhihudaily.entities.SplashScreenEntity;
import com.blz.zhihudaily.impl.presenters.SplashScreenPresenterImpl;
import com.blz.zhihudaily.interfaces.presenters.SplashScreenPresenter;
import com.blz.zhihudaily.interfaces.views.SplashScreenView;
import com.blz.zhihudaily.ui.swipebacklayout.SwipeBackActivity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * 闪屏页
 * Created by BuLingzhuang 
 * on 2016/5/9 16:05
 * E-mail bulingzhuang@foxmail.com
 */
public class SplashScreenActivity extends SwipeBackActivity implements SplashScreenView {

    private static final String TAG = SplashScreenActivity.class.getSimpleName();
    @Bind(R.id.SplashScreen_image)
    ImageView mImage;
    private SplashScreenPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        mPresenter = new SplashScreenPresenterImpl(this);
        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();
        ButterKnife.bind(this);
    }

    @Override
    public void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void startAnim(SplashScreenEntity splashScreen) {
        if (splashScreen != null) {
            Log.e(TAG, "开始加载图片");
            Picasso.with(this).load(splashScreen.getImg()).into(mImage);
        }
        Animation largeAnimation = AnimationUtils.loadAnimation(this, R.anim.enlarge);
        largeAnimation.setFillAfter(true);
        mImage.startAnimation(largeAnimation);
    }
    /*Animation largeAnimation = AnimationUtils.loadAnimation(this, R.anim.enlarge);
        largeAnimation.setFillAfter(true);
        mImage.startAnimation(largeAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);*/
}

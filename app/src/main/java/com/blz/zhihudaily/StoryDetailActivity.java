package com.blz.zhihudaily;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blz.zhihudaily.entities.StoryDetailEntityDownload;
import com.blz.zhihudaily.services.BaseService;
import com.blz.zhihudaily.ui.swipebacklayout.SwipeBackActivity;
import com.blz.zhihudaily.utils.Constants;
import com.blz.zhihudaily.utils.HttpUtils;
import com.blz.zhihudaily.utils.Tools;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 文章详情页
 * Created by BuLingzhuang
 * on 2016/5/9 16:06
 * E-mail bulingzhuang@foxmail.com
 */
public class StoryDetailActivity extends SwipeBackActivity implements Callback<StoryDetailEntityDownload> {

    @Bind(R.id.story_detail_tv)
    TextView mTv;
    @Bind(R.id.story_detail_rl)
    CoordinatorLayout mRl;
    @Bind(R.id.story_detail_im)
    ImageView mIm;
    @Bind(R.id.story_detail_toolBar)
    Toolbar mToolBar;
    @Bind(R.id.story_detail_ctl)
    CollapsingToolbarLayout mStoryDetailCtl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);
        init();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra(Constants.StoryEntityIdConstant);
        BaseService baseService = HttpUtils.getBaseService();
        Call<StoryDetailEntityDownload> call = baseService.getStoryDetail(id);
        call.enqueue(this);
    }

    private void init() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        finish();
        //Genymotion上alpha动画好象有显示问题
        overridePendingTransition(0,R.anim.finish_activity_alpha);
    }

    @Override
    public void onResponse(Response<StoryDetailEntityDownload> response) {
        StoryDetailEntityDownload body = response.body();
//        mToolBar.setTitle(body.getTitle());
//        mToolBar.setSubtitle(body.getTitle());
//        getSupportActionBar().setTitle(body.getTitle());
        mStoryDetailCtl.setTitle(body.getTitle());
        Picasso.with(this).load(body.getImage()).placeholder(R.mipmap.menu_icon).error(R.mipmap.menu_icon).into(mIm);
        mTv.setText(body.getBody());
        // TODO: 2016/5/10 把JS转为本地网址
    }

    @Override
    public void onFailure(Throwable t) {
        Tools.snackAppear("网络错误", this, mRl);
    }
}

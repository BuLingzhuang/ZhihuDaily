package com.blz.zhihudaily;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blz.zhihudaily.entities.StoryDetailEntityDownload;
import com.blz.zhihudaily.services.BaseService;
import com.blz.zhihudaily.utils.Constants;
import com.blz.zhihudaily.utils.HttpUtils;
import com.blz.zhihudaily.utils.Tools;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryDetailActivity extends BaseActivity implements Callback<StoryDetailEntityDownload> {

    @Bind(R.id.story_detail_tv)
    TextView mTv;
    @Bind(R.id.story_detail_rl)
    RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra(Constants.StoryEntityIdConstant);
        BaseService baseService = HttpUtils.getBaseService();
        Call<StoryDetailEntityDownload> call = baseService.getStoryDetail(id);
        call.enqueue(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResponse(Response<StoryDetailEntityDownload> response) {
        String body = response.body().getBody();
        mTv.setText(body);
    }

    @Override
    public void onFailure(Throwable t) {
        Tools.snackAppear("网络错误", this, mRl);
    }
}

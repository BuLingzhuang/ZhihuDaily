package com.blz.zhihudaily;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.blz.zhihudaily.adapters.StoryAdapter;
import com.blz.zhihudaily.entities.LatestListEntity;
import com.blz.zhihudaily.entities.StoryEntity;
import com.blz.zhihudaily.services.BaseService;
import com.blz.zhihudaily.utils.HttpUtils;
import com.blz.zhihudaily.utils.Tools;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements Callback<LatestListEntity>, SwipeRefreshLayout.OnRefreshListener {

    long firstTime = 0L;

    @Bind(R.id.main_nv)
    NavigationView mNavigationView;
    @Bind(R.id.main_drawerLayout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.main_recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.main_nv_listView)
    ListView mListView;
    @Bind(R.id.menu_header_favorites)
    LinearLayout mHeaderFavorites;
    @Bind(R.id.menu_header_download)
    LinearLayout mHeaderDownload;
    @Bind(R.id.menu_header_icon)
    ImageView mHeaderIcon;
    @Bind(R.id.main_refresh)
    SwipeRefreshLayout mRefreshLayout;
    private ActionBarDrawerToggle mToggle;
    private StoryAdapter mStoryAdapter;
    private boolean isRefresh;
    private int mLastVisibleItem;
    private int mDate = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void init() {
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setElevation(0);
        }
        mToggle.syncState();
        mDrawerLayout.addDrawerListener(mToggle);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            list.add("Item-" + i);
        }
        Map<Type, Integer> map = new HashMap<>();
        map.put(LatestListEntity.class, R.layout.adapter_top_story);
        map.put(StoryEntity.class, R.layout.adapter_story);
        mStoryAdapter = new StoryAdapter(this, map);
        mRecyclerView.setAdapter(mStoryAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItem + 1 == mStoryAdapter.getItemCount()) {
                    if (mDate == 0) {
                        mDate = getDate();
                    } else {
                        mDate--;
                    }
                    getData(false, String.valueOf(mDate));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                mLastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);
        getData(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_default_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean b = super.onOptionsItemSelected(item);
        if (mToggle.onOptionsItemSelected(item)) {
            b = true;
        }
        return b;
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if ((paramKeyEvent.getAction() == 0) && (4 == paramInt)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - firstTime >= 2000L) {
                invalidateOptionsMenu();
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                firstTime = currentTime;
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    @Override
    public void onResponse(Response<LatestListEntity> response) {
        LatestListEntity body = response.body();
        List<LatestListEntity.StoriesEntity> stories = body.getStories();
        if (body.getTop_stories() != null) {
            mStoryAdapter.addAll(body, Tools.changeSotries2Story(stories), isRefresh);
        } else {
            mStoryAdapter.addAll(Tools.changeSotries2Story(stories), isRefresh);
        }
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        Tools.snackAppear("刷新成功", this, mDrawerLayout);
    }

    @Override
    public void onFailure(Throwable t) {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        Tools.snackAppear("网络错误", this, mDrawerLayout);
    }


    @Override
    public void onRefresh() {
        getData(true);
    }

    public void getData(boolean b) {
        getData(b, null);
    }

    public void getData(boolean b, String date) {
        if (!mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(true);
        }
//        if (date == null) {
//            date = "/20160412";
//        }else {
//            date = "/"+date;
//        }
        isRefresh = b;
        BaseService baseService = HttpUtils.getBaseService();
        Call<LatestListEntity> recyclerCall;
        if (date != null) {
            recyclerCall = baseService.getLatestList(date);
        } else {
            recyclerCall = baseService.getLatestList();
        }
        recyclerCall.enqueue(this);
    }

    private int getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR) * 10000;
        int month = (calendar.get(Calendar.MONTH) + 1) * 100;
        int date = calendar.get(Calendar.DATE);
        return year + month + date;
    }
}

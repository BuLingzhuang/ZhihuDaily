package com.blz.zhihudaily.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blz.zhihudaily.R;
import com.blz.zhihudaily.StoryDetailActivity;
import com.blz.zhihudaily.entities.LatestListEntity;
import com.blz.zhihudaily.entities.StoryEntity;
import com.blz.zhihudaily.utils.Constants;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by 卜令壮
 * on 2016/4/8
 * E-mail q137617549@qq.com
 */
public class StoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Object> mList;
    private Map<Type, Integer> mMap;
    private Handler mHandler;

    public StoryAdapter(Context context, Map<Type, Integer> map) {
        mContext = context;
        mMap = map;
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(viewType, parent, false);
        switch (viewType) {
            case R.layout.adapter_top_story:
                return new StoryAdapterTopStoryViewHolder(inflate);
            case R.layout.adapter_story:
                return new StoryAdapterStoryViewHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case R.layout.adapter_top_story:
                final LatestListEntity latestListEntity = (LatestListEntity) mList.get(position);
                final List<StoryEntity> top_stories = latestListEntity.getTop_stories();
                final StoryAdapterTopStoryViewHolder topViewHolder = (StoryAdapterTopStoryViewHolder) holder;
                topViewHolder.mTvTitle.setText(top_stories.get(0).getTitle());
                topViewHolder.mViewPager.setAdapter(new TopStoryAdapter(top_stories));
                topViewHolder.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        topViewHolder.mTvTitle.setText(top_stories.get(position).getTitle());
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                break;
            case R.layout.adapter_story:
                final StoryEntity entity = (StoryEntity) mList.get(position);
                StoryAdapterStoryViewHolder viewHolder = (StoryAdapterStoryViewHolder) holder;
                Picasso.with(mContext).load(entity.getImage()).error(R.mipmap.menu_icon).placeholder(R.mipmap.menu_icon).into(viewHolder.mIv);
                viewHolder.mTv.setText(entity.getTitle());
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, StoryDetailActivity.class);
                        intent.putExtra(Constants.StoryEntityIdConstant, entity.getId());
                        mContext.startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMap.get(mList.get(position).getClass());
    }

    public void addAll(Collection<? extends StoryEntity> collection, boolean isRefresh) {
        addAll(null, collection, isRefresh);
    }

    public void addAll(LatestListEntity latestListEntity, Collection<? extends StoryEntity> collection, boolean isRefresh) {
        if (isRefresh) {
            mList.clear();
        }
        if (latestListEntity != null) {
            mList.add(latestListEntity);
        }
        mList.addAll(collection);
        notifyDataSetChanged();
    }

    public static class StoryAdapterStoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTv;
        private final ImageView mIv;

        public StoryAdapterStoryViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.item_story_tv);
            mIv = (ImageView) itemView.findViewById(R.id.item_story_iv);
        }
    }

    public static class StoryAdapterTopStoryViewHolder extends RecyclerView.ViewHolder {

        private final ViewPager mViewPager;
        private final TextView mTvTitle;

        public StoryAdapterTopStoryViewHolder(View itemView) {
            super(itemView);
            mViewPager = (ViewPager) itemView.findViewById(R.id.item_top_story_viewPager);
            mTvTitle = (TextView) itemView.findViewById(R.id.item_top_story_tvTitle);
        }
    }
}

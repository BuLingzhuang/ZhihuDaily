package com.blz.zhihudaily.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blz.zhihudaily.R;
import com.blz.zhihudaily.StoryDetailActivity;
import com.blz.zhihudaily.entities.StoryEntity;
import com.blz.zhihudaily.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 卜令壮
 * on 2016/4/12
 * E-mail q137617549@qq.com
 */
public class TopStoryAdapter extends PagerAdapter{

    private List<StoryEntity> mList;

    public TopStoryAdapter(List<StoryEntity> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final Context context = container.getContext();
        ImageView imageView = new ImageView(context);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StoryDetailActivity.class);
                intent.putExtra(Constants.StoryEntityIdConstant, mList.get(position).getId());
                context.startActivity(intent);
            }
        });
        Picasso.with(context).load(mList.get(position).getImage()).error(R.mipmap.menu_icon).placeholder(R.mipmap.menu_icon).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View){
            container.removeView((View) object);
        }
    }
}

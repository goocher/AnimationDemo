package com.gooch.animationdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * <pre>
 * author : gooch
 * e-mail : zhaoguangchao@100tal.com
 * time   : 2017/9/17
 * desc   :
 * version: 1.0
 * </pre>
 */


public class BannerAdapter extends PagerAdapter {
    private List<ImageView> mImageViews;
    private Context mContext;

    public BannerAdapter(Context context, List<ImageView> imageViews) {
        mImageViews = imageViews;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImageViews != null ? mImageViews.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView;
        if (mImageViews != null) {
            imageView = mImageViews.get(position);
            container.addView(imageView);
        } else {
            imageView = new ImageView(mContext);
        }
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

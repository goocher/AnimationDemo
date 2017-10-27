package com.gooch.animationdemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gooch.animationdemo.data.ClassifyBean;

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
    private List<ClassifyBean.DataEntity.BannerEntity.BottomEntity> mStrings;
    private String url = "http://i0.hdslb" +
            ".com/bfs/archive/13fe4ab319fdd39bfc70531089de50b1cf53e34b.jpg";

    public BannerAdapter(Context context, List<ImageView> imageViews) {
        mImageViews = imageViews;
        mContext = context;
    }

    public BannerAdapter(Context context, List<ImageView> imageViews, List<ClassifyBean
            .DataEntity.BannerEntity.BottomEntity> imgUrl) {
        mImageViews = imageViews;
        mContext = context;
        mStrings = imgUrl;
    }

    public void setStrings(List<ClassifyBean.DataEntity.BannerEntity.BottomEntity> strings) {
        mStrings = strings;
    }

    @Override
    public int getCount() {
        return mImageViews != null ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = null;
        if (mImageViews != null && mImageViews.size() > 0) {
            imageView = mImageViews.get(position % mImageViews.size());
            Glide.with(mContext).load(url).placeholder(R.mipmap
                    .ic_launcher)
                    .into(imageView);
//            if (imageView.getParent() != container) {
                container.addView(imageView);
//            }
        }
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

package com.gooch.animationdemo.fragment;


import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.gooch.animationdemo.BannerAdapter;
import com.gooch.animationdemo.CommonUtils;
import com.gooch.animationdemo.R;
import com.gooch.animationdemo.ZoomTransformer;
import com.gooch.animationdemo.databinding.FragmentMain3Binding;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main3Fragment extends Fragment {


    private FragmentMain3Binding mInflate;
    private Activity mActivity;
    private ScaleAnimation mAnimation;
    private double mFactor = 0.5;

    public Main3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflate = DataBindingUtil.inflate(inflater, R.layout.fragment_main3, container, false);
        init();
        return mInflate.getRoot();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "setUserVisibleHint: ");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
        android.util.Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    private void init() {
        final List<ImageView> imageViews = new ArrayList<>();
        int[] ints = {R.mipmap.aa, R.mipmap.aa, R.mipmap.aa,
                R.mipmap.aa, R.mipmap.aa};
        for (int i = 0; i < ints.length; i++) {
            ImageView view = new ImageView(mActivity);
            view.setImageResource(ints[i]);
            imageViews.add(view);
        }
        int initIndex = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size();
        BannerAdapter adapter = new BannerAdapter(getContext(), imageViews);
        mInflate.banner.setAdapter(adapter);
        mInflate.banner.setCurrentItem(initIndex);
        mInflate.banner.setPageTransformer(true, new ZoomTransformer());
        mInflate.banner.setPageMargin(1);
        CommonUtils.controlViewPagerSpeed(mActivity, mInflate.banner, 10000);
        mAnimation = new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, 150, 450);
        mAnimation.setDuration(500);
        mAnimation.setFillAfter(true);
        mAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return (float) (Math.pow(2, -10 * input) * Math.sin((input - mFactor / 4) * (2 *
                        Math.PI) / mFactor)
                        + 0.9);
            }
        });
        mInflate.banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                imageViews.get(position).startAnimation(mAnimation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mInflate.banner.start();
    }
}

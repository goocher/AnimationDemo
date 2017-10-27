package com.gooch.animationdemo.fragment;


import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.gooch.animationdemo.R;
import com.gooch.animationdemo.databinding.FragmentMainBinding;

import org.greenrobot.eventbus.EventBus;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    private FragmentMainBinding mInflate;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflate = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        initView();
        return mInflate.getRoot();
    }

    private void initView() {
        final Animation scaleAnimation = AnimationUtils.loadAnimation(getContext(), R.anim
                .scale_anim);
        final Animation translatAnimation = AnimationUtils.loadAnimation(getContext(), R.anim
                .translate_anim);
        mInflate.btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInflate.imageView.startAnimation(scaleAnimation);
            }
        });
        mInflate.btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInflate.imageView.startAnimation(translatAnimation);
            }
        });
        mInflate.btnStopanim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInflate.imageView.clearAnimation();
                EventBus.getDefault().postSticky("123");
            }
        });
        ViewPropertyAnimator animate = mInflate.imageView.animate();
        ValueAnimator.ofObject(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                return null;
            }
        }, 100, 200);
        mInflate.btnApha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimation();
            }
        });
        mInflate.btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimSet();
            }
        });
    }

    private void doAnimation() {
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder valuesHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0,
                frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mInflate.imageView,
                valuesHolder);
        objectAnimator.setDuration(2000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();
    }

    public void doAnimSet() {
        ObjectAnimator tv1BgAnimator = ObjectAnimator.ofInt(mInflate.btnApha, "BackgroundColor",
                0xffff00ff, 0xffffff00, 0xffff00ff);
        tv1BgAnimator.setStartDelay(2000);
        ObjectAnimator tv1TranslateY = ObjectAnimator.ofFloat(mInflate.btnApha, "translationY",
                0, 400, 0);
        tv1TranslateY.setStartDelay(3000);
        ObjectAnimator tv2TranslateY = ObjectAnimator.ofFloat(mInflate.btnScale, "translationY",
                0, 400, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(tv1BgAnimator, tv1TranslateY);
        animatorSet.setStartDelay(2000);
        animatorSet.setDuration(2000);
        animatorSet.start();

    }

}

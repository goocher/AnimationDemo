package com.gooch.animationdemo.fragment;


import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gooch.animationdemo.R;
import com.gooch.animationdemo.SketchView;
import com.gooch.animationdemo.databinding.FragmentSketchBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SketchFragment extends Fragment {


    private FragmentSketchBinding mBinding;

    public SketchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sketch, container, false);
        init();
        return mBinding.getRoot();
    }

    private void init() {
        Glide.with(getContext())
                .load("http://img2.imgtn.bdimg.com/it/u=2974104803,1439396293&fm=200&gp=0.jpg")
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mBinding.svCanvas.setBackgroundBitmap(getActivity(), resource);
                    }
                });
        initMode();
        mBinding.svCanvas.setOnDrawChangedListener(new SketchView.OnDrawChangedListener() {
            @Override
            public void onDrawChanged() {

            }
        });
    }

    private void initMode() {
        mBinding.btnPen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.svCanvas.setMode(SketchView.STROKE);
            }
        });
        mBinding.btnEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.svCanvas.setMode(SketchView.ERASER);
            }
        });
        mBinding.btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.svCanvas.undo();
            }
        });
        mBinding.btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.svCanvas.redo();
            }
        });
        mBinding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.svCanvas.eraser();
            }
        });
    }

}

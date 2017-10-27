package com.gooch.animationdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.gooch.animationdemo.R;
import com.gooch.animationdemo.databinding.FragmentSnapBinding;

import static android.databinding.DataBindingUtil.inflate;

/**
 * A simple {@link Fragment} subclass.
 */
public class SnapFragment extends Fragment {


    private FragmentSnapBinding mBinding;

    public SnapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = inflate(inflater, R.layout.fragment_snap, container, false);
        init();
        return mBinding.getRoot();
    }

    private void init() {
        mBinding.rvSnap.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
//        mBinding.rvSnap.setLayoutManager(new GridLayoutManager(getContext(), 3,
// GridLayoutManager.HORIZONTAL, false));
        mBinding.rvSnap.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.img, parent, false);
                return new RvViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 32;
            }

            class RvViewHolder extends RecyclerView.ViewHolder {
                public ImageView mImageView;

                public RvViewHolder(View itemVeiw) {
                    super(itemVeiw);
                    mImageView = (ImageView) itemVeiw.findViewById(R.id.iv_snap);
                }
            }
        });
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation
                (getContext(), R.anim.layout_anim);
        layoutAnimationController.setInterpolator(new DecelerateInterpolator());
        mBinding.rvSnap.setLayoutAnimation(layoutAnimationController);
        SnapHelper snapHelper = new PagerSnapHelper();
//        SnapHelper snapHelper = new LinearSnapHelper();
//        SnapHelper snapHelper = new GallerySnapHelper();
        snapHelper.attachToRecyclerView(mBinding.rvSnap);
    }

}

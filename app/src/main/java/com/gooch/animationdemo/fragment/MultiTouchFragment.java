package com.gooch.animationdemo.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gooch.animationdemo.ImageViewOnMultiTouchListener;
import com.gooch.animationdemo.R;
import com.gooch.animationdemo.databinding.FragmentMultiTouchBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MultiTouchFragment extends Fragment {


    private FragmentMultiTouchBinding mBinding;

    public MultiTouchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_multi_touch, container,
                false);
        init();
        return mBinding.getRoot();
    }

    private void init() {
        mBinding.ivTouch.setImageResource(R.mipmap.ic_launcher);
        mBinding.ivTouch.setOnTouchListener(new ImageViewOnMultiTouchListener());
    }

}

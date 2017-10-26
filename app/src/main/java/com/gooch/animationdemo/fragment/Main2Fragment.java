package com.gooch.animationdemo.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.gooch.animationdemo.R;
import com.gooch.animationdemo.databinding.FragmentMain2Binding;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main2Fragment extends Fragment {


    private FragmentMain2Binding mInflate;

    public Main2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflate = DataBindingUtil.inflate(inflater, R.layout.fragment_main2, container, false);
        initView();
        return mInflate.getRoot();
    }

    private void initView() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mInflate.pvAnim.setProgress(70f).setInterpolator(new OvershootInterpolator()).doAnimation();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mInflate.pvAnim.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mInflate.pvAnim.setTextSize(getResources().getDimensionPixelSize(R.dimen.pv_textsize));
        mInflate.pvAnim.setProgress(70f).setInterpolator(new OvershootInterpolator()).doAnimation();

    }
}

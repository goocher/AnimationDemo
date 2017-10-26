package com.gooch.animationdemo.fragment;


import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gooch.animationdemo.BannerAdapter;
import com.gooch.animationdemo.R;
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
        List<ImageView> imageViews = new ArrayList<>();
        int[] ints = {android.R.drawable.arrow_up_float, android.R.drawable.btn_default, android.R.drawable.btn_plus, android.R.drawable.btn_dropdown};
        for (int i = 0; i < ints.length; i++) {
            ImageView view = new ImageView(mActivity);
            view.setImageResource(ints[i]);
            imageViews.add(view);
        }
        BannerAdapter adapter = new BannerAdapter(getContext(), imageViews);
        mInflate.banner.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mInflate.banner.start();
    }
}

package com.gooch.animationdemo;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gooch.animationdemo.databinding.FragmentMain3Binding;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main3Fragment extends Fragment {


    private FragmentMain3Binding mInflate;

    public Main3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflate = DataBindingUtil.inflate(inflater, R.layout.fragment_main3, container, false);

        return mInflate.getRoot();
    }

}

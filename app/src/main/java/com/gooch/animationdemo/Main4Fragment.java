package com.gooch.animationdemo;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gooch.animationdemo.databinding.FragmentMain4Binding;


/**
 * A simple {@link Fragment} subclass.
 */
public class Main4Fragment extends Fragment {


    private FragmentMain4Binding mInflate;

    public Main4Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mInflate = DataBindingUtil.inflate(inflater, R.layout.fragment_main4, container, false);

        return mInflate.getRoot();
    }

}

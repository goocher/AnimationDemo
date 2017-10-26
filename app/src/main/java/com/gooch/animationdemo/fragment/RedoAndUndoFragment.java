package com.gooch.animationdemo.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gooch.animationdemo.R;
import com.gooch.animationdemo.databinding.FragmentRedoAndUndoBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RedoAndUndoFragment extends Fragment {


    private FragmentRedoAndUndoBinding mBinding;
    private List<String> mUndoList;
    private List<String> mContent;
    private boolean mChange;

    public RedoAndUndoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_redo_and_undo, container,
                false);
        mUndoList = new ArrayList<>();
        mContent = new ArrayList<>();
        mBinding.undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContent.size() > 0) {
                    mUndoList.add(mContent.remove(mContent.size() - 1));
                    invaliate();
                }
            }
        });
        mBinding.redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUndoList.size() > 0) {
                    mContent.add(mUndoList.remove(mUndoList.size() - 1));
                    invaliate();
                }
            }
        });
        mBinding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.clear();
                mUndoList.clear();
                mBinding.etContent.setText("");
            }
        });
        mBinding.etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!mChange) {
                    mContent.add(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return mBinding.getRoot();
    }

    private void invaliate() {
        mChange = true;
        if (mContent.size() > 0) {
            mBinding.etContent.setText(mContent.get(mContent.size() - 1));
            mBinding.etContent.setSelection(mContent.get(mContent.size() - 1).length());
        } else if (mContent.size() == 0) {
            mBinding.etContent.setText("");
            mBinding.etContent.setSelection(0);
        }
    }

}

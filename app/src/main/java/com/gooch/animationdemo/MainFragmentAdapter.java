package com.gooch.animationdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * <pre>
 * author : gooch
 * e-mail : zhaoguangchao@100tal.com
 * time   : 2017/9/16
 * desc   :
 * version: 1.0
 * </pre>
 */


public class MainFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mStrings;

    public MainFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragments = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments != null ? mFragments.get(position) : null;

    }

    public void setStrings(List<String> strings) {
        mStrings = strings;
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStrings != null ? mStrings.get(position) : "请设置";
    }
}

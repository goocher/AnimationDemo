package com.gooch.animationdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.gooch.animationdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initViewPager();
        initTab();
    }

    private void initTab() {
        String[] stringArray = getResources().getStringArray(R.array.title_array);
        for (String aStringArray : stringArray) {
            TabLayout.Tab tab = mMainBinding.tbTitle.newTab();
            tab.setText(aStringArray);
            mMainBinding.tbTitle.addTab(tab);
        }
        mMainBinding.tbTitle.setupWithViewPager(mMainBinding.vpPager);
    }

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new Main2Fragment());
        fragments.add(new Main3Fragment());
        fragments.add(new Main4Fragment());
        fragments.add(new SketchFragment());
        String[] stringArray = getResources().getStringArray(R.array.title_array);
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager(), fragments);
        adapter.setStrings(Arrays.asList(stringArray));
        mMainBinding.vpPager.setAdapter(adapter);
    }
}

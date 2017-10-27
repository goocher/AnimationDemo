package com.gooch.animationdemo;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @description: TODO
 * Date: 2017/10/26 15:56
 * @author: zhaoguangchao(gooch)
 * Email:zhaoguangchao@100tal.com
 */

public class ZoomTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {

        if (position < -1) { /* [-Infinity,-1)*/
        /*页面已经在屏幕左侧第一个*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getWidth());
            page.setRotationY(20);
        } else if (position <= 0) { /* [-1,0]*/
            /*页面从左侧进入或者向左侧滑出的状态*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getWidth());
            page.setRotationY(-20 + (1 - position) * 20);
        } else if (position <= 1) {/* (0,1]*/
            /*页面从右侧进入或者向右侧滑出的状态*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getWidth());
            page.setRotationY(-20 + (1 - position) * 20);
        } else if (position <= 2) {
        /*页面已经在屏幕右侧第一个*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getWidth());
            page.setRotationY(-20);
        }

    }
}

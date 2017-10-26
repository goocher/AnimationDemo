package com.gooch.animationdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;

import java.lang.reflect.Field;

/**
 * @description: TODO
 * Date: 2017/10/26 16:45
 * @author: zhaoguangchao(gooch)
 * Email:zhaoguangchao@100tal.com
 */

public class CommonUtils {
    public static void controlViewPagerSpeed(Context context, ViewPager viewPager, int duration) {
        Field field = null;
        try {
            field = ViewPager.class.getField("mScroller");
            field.setAccessible(true);
            FixSpeedScroller scroller = new FixSpeedScroller(context);
            scroller.setDurations(duration);
            field.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

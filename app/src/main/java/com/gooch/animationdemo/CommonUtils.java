package com.gooch.animationdemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.view.ViewPager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void controlViewPagerSpeeds(Context context, ViewPager viewPager, int duration) {
        Field field = null;
        try {
            field = ViewPager.class.getField("mVelocityTracker");
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

    /**
     * 读取asset目录下的json文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static <T> T jsonToBean(String json, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}

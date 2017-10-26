package com.gooch.animationdemo;

import android.app.Application;
import android.content.Context;

import com.samsung.android.sdk.pen.Spen;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/21
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class AnimApplication extends Application {
    private static Context mContext;
    public static boolean isSpenFeatureEnabled;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // 初始化手写笔
        Spen spenPackage = new Spen();
        try {
            spenPackage.initialize(this);
            isSpenFeatureEnabled = spenPackage.isFeatureEnabled(Spen.DEVICE_PEN);
        } catch (Exception e1) {
            isSpenFeatureEnabled = false;
        }
        tbsLogUpload();
    }

    private void tbsLogUpload() {

    }

    public static Context getInstance() {
        return mContext;
    }
}

package com.gooch.animationdemo;

import android.support.v7.widget.LinearSnapHelper;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/10/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class GravitySnapHelper extends LinearSnapHelper {
    @Override
    public int[] calculateScrollDistance(int velocityX, int velocityY) {
        return super.calculateScrollDistance(velocityX, velocityY);
    }
}

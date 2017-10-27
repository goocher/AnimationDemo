package com.gooch.animationdemo;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * @description: TODO
 * Date: 2017/10/26 16:41
 * @author: zhaoguangchao(gooch)
 * Email:zhaoguangchao@100tal.com
 */

public class FixSpeedScroller extends Scroller {
    public int mDuration=10000;

    public FixSpeedScroller(Context context) {
        super(context);
    }

    public FixSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public FixSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public int getDurations() {
        return mDuration;
    }

    public void setDurations(int duration) {
        mDuration = duration;
    }
}

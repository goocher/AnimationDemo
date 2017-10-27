package com.gooch.animationdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * author : gooch
 * e-mail : zhaoguangchao@100tal.com
 * time   : 2017/9/17
 * desc   :
 * version: 1.0
 * </pre>
 */


public class Banner extends ViewPager {
    private Timer mTimer;

    public Banner(Context context) {
        super(context);
    }

    public Banner(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public void start() {
        startTimer();
    }

    public void stop() {
        stopTimer();
    }

    private void startTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer.purge();
            mTimer = null;
        }
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Banner.this.post(new Runnable() {
                    @Override
                    public void run() {
                        if (Banner.this.getCurrentItem() < Banner.this.getChildCount()) {
                            Banner.this.setCurrentItem((Banner.this.getCurrentItem() + 1), true);
                        }
                    }
                });

            }
        }, 1000);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10, new ThreadFactory() {


            @Override
            public Thread newThread(@NonNull Runnable r) {
                return null;
            }
        });
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000, 2000, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                stopTimer();
                break;
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                startTimer();
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void stopTimer() {
        if (mTimer == null) {
            return;
        }
        mTimer.cancel();
        mTimer.purge();
        mTimer = null;

    }
}

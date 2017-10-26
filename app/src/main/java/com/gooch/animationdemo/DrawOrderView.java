package com.gooch.animationdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/10/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class DrawOrderView extends LinearLayout {

    private Paint mPaint;
    private Point mPoint;
    private Rect mRect;
    private String TAG = DrawOrderView.class.getSimpleName();

    public DrawOrderView(Context context) {
        this(context, null);
    }

    public DrawOrderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawOrderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(15);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRect = new Rect(0, 0, 100, 100);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: -=================");
        mPaint.setColor(Color.BLACK);
        canvas.drawColor(Color.BLACK);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        mPaint.setColor(Color.BLUE);
        Log.d(TAG, "dispatchDraw: =========================");
        Path path = new Path();
        path.addCircle(getWidth() / 2, getHeight() / 2, 300, Path.Direction.CW);
        path.addCircle(getWidth() / 2 + 400, getHeight() / 2, 300, Path.Direction.CCW);
//        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRect.height() / 2, mPaint);
        canvas.drawLine(20f, getHeight() / 3, getWidth() / 2, getHeight() / 3, mPaint);
        canvas.drawPath(path, mPaint);
    }
}

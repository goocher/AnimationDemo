package com.gooch.animationdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

/**
 * <pre>
 * author : gooch
 * e-mail : zhaoguangchao@100tal.com
 * time   : 2017/9/16
 * desc   :
 * version: 1.0
 * </pre>
 */


public class ProgressAnimView extends View {

    private Paint mPaint;
    private RectF mRectF;
    private TextPaint mTextPaint;
    private float progress;
    private float currentProgress;
    private String drawText = "0%";
    private int mTextSize = 22;
    private Interpolator mInterpolator = new BounceInterpolator();
    public ProgressAnimView(Context context) {
        this(context, null);
    }
    public ProgressAnimView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ProgressAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
        mRectF = new RectF();
        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.colorAccent));
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTextPaint.setTextSize(mTextSize);
        mRectF.set(0f, 0f, getWidth(), getHeight());
        canvas.drawText(drawText, mRectF.centerX() - mTextPaint.measureText(drawText) / 2,
                mRectF.centerY() - (mTextPaint.descent() + mTextPaint.ascent()) / 2, mTextPaint);
        canvas.drawArc(mRectF, 90f, currentProgress - 90f, false, mPaint);
    }

    public ProgressAnimView setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
        return this;
    }

    public float getProgress() {
        return progress;
    }

    public ProgressAnimView setProgress(float progress) {
        if (progress == 0f) {
            throw new IllegalStateException("请初始化progress！");
        }
        if (progress > 100 || progress < 0) {
            throw new IllegalStateException("progress不能大于100或小于0！");
        }
        this.progress = progress;
        return this;
    }

    public void doAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(90f, convertProgress(progress));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentProgress = (float) animation.getAnimatedValue();
                drawText = (int) ((currentProgress - 90f) * 100f / 360) + "%";
                invalidate();
            }
        });
        animator.setDuration(1500);
        animator.setInterpolator(mInterpolator);
        animator.start();
    }

    private float convertProgress(float progress) {

        return 90f + progress * 360f / 100;
    }
}

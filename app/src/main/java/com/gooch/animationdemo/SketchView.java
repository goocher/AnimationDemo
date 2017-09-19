package com.gooch.animationdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * <pre>
 *     author : gooch
 *     e-mail : zhaoguangchao@100tal.com
 *     time   : 2017/09/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class SketchView extends View implements View.OnTouchListener {
    private static final float TOUCH_TOLERANCE = 4;

    public static final int STROKE = 0;
    public static final int ERASER = 1;
    public static final int DEFAULT_STROKE_SIZE = 7;
    public static final int DEFAULT_ERASER_SIZE = 50;

    private float strokeSize = DEFAULT_STROKE_SIZE;
    private int strokeColor = Color.BLACK;
    private float eraserSize = DEFAULT_ERASER_SIZE;
    private int background = Color.WHITE;

    //	private Canvas mCanvas;
    private Path m_Path;
    private Paint m_Paint;
    private float mX, mY;
    private int width, height;

    private ArrayList<Pair<Path, Paint>> paths = new ArrayList<>();
    private ArrayList<Pair<Path, Paint>> undonePaths = new ArrayList<>();
    private Context mContext;

    private Bitmap bitmap;

    private int mode = STROKE;

    private OnDrawChangedListener onDrawChangedListener;


    public SketchView(Context context, AttributeSet attr) {
        super(context, attr);

        this.mContext = context;

        setFocusable(true);
        setFocusableInTouchMode(true);
        setBackgroundColor(Color.WHITE);

        this.setOnTouchListener(this);

        m_Paint = new Paint();
        m_Paint.setAntiAlias(true);
        m_Paint.setDither(true);
        m_Paint.setColor(strokeColor);
        m_Paint.setStyle(Paint.Style.STROKE);
        m_Paint.setStrokeJoin(Paint.Join.ROUND);
        m_Paint.setStrokeCap(Paint.Cap.ROUND);
        m_Paint.setStrokeWidth(strokeSize);
        m_Path = new Path();
        Paint newPaint = new Paint(m_Paint);
        invalidate();
    }


    public void setMode(int mode) {
        if (mode == STROKE || mode == ERASER)
            this.mode = mode;
    }


    public int getMode() {
        return this.mode;
    }


    /**
     * Change canvass background and force redraw
     *
     * @param bitmap
     */
    public void setBackgroundBitmap(Activity mActivity, Bitmap bitmap) {
        if (!bitmap.isMutable()) {
            android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();
            // set default bitmap config if none
            if (bitmapConfig == null) {
                bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
            }
            bitmap = bitmap.copy(bitmapConfig, true);
        }
        this.bitmap = bitmap;
		this.bitmap = getScaledBitmap(mActivity, bitmap);
//		mCanvas = new Canvas(bitmap);
    }


	private Bitmap getScaledBitmap(Activity mActivity, Bitmap bitmap) {
		DisplayMetrics display = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);
		int screenWidth = display.widthPixels;
		int screenHeight = display.heightPixels;
		float scale = bitmap.getWidth() / screenWidth > bitmap.getHeight() / screenHeight ? bitmap.getWidth() /
 screenWidth : bitmap.getHeight() / screenHeight;
		int scaledWidth = (int) (bitmap.getWidth() / scale);
		int scaledHeight = (int) (bitmap.getHeight() / scale);
		return Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
	}


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        height = View.MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }


    public boolean onTouch(View arg0, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(event, x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }

        for (Pair<Path, Paint> p : paths) {
            canvas.drawPath(p.first, p.second);
        }

        onDrawChangedListener.onDrawChanged();
    }


    private void touch_start(float x, float y) {
        // Clearing undone list
        undonePaths.clear();

        if (mode == ERASER) {
            m_Paint.setColor(Color.WHITE);
            m_Paint.setStrokeWidth(eraserSize);
        } else {
            m_Paint.setColor(strokeColor);
            m_Paint.setStrokeWidth(strokeSize);
        }

        Paint newPaint = new Paint(m_Paint); // Clones the mPaint object

        // Avoids that a sketch with just erasures is saved
        if (!(paths.size() == 0 && mode == ERASER && bitmap == null)) {
            paths.add(new Pair<>(m_Path, newPaint));
        }

        m_Path.reset();
        m_Path.moveTo(x, y);
        mX = x;
        mY = y;
    }


    private void touch_move(MotionEvent event, float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (event.getPointerCount() == 2) {
            m_Path = null;
        }
        if (m_Path != null) {
            m_Path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
        }
        mX = x;
        mY = y;
    }


    private void touch_up() {
//        m_Path.lineTo(mX, mY);
        Paint newPaint = new Paint(m_Paint); // Clones the mPaint object

        // Avoids that a sketch with just erasures is saved
        if (!(paths.size() == 0 && mode == ERASER && bitmap == null) && m_Path != null) {
            paths.add(new Pair<>(m_Path, newPaint));
        }

        // kill this so we don't double draw
        m_Path = new Path();
    }


    /**
     * Returns a new bitmap associated with drawed canvas
     *
     * @return
     */
    public Bitmap getBitmap() {
        if (paths.size() == 0)
            return null;

        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(background);
        }
        Canvas canvas = new Canvas(bitmap);
        for (Pair<Path, Paint> p : paths) {
            canvas.drawPath(p.first, p.second);
        }
        return bitmap;
    }


    public void undo() {
        if (paths.size() >= 2) {
            undonePaths.add(paths.remove(paths.size() - 1));
            // If there is not only one path remained both touch and move paths are removed
            undonePaths.add(paths.remove(paths.size() - 1));
            invalidate();
        }
    }


    public void redo() {
        if (undonePaths.size() > 0) {
            paths.add(undonePaths.remove(undonePaths.size() - 1));
            paths.add(undonePaths.remove(undonePaths.size() - 1));
            invalidate();
        }
    }


    public int getUndoneCount() {
        return undonePaths.size();
    }


    public ArrayList<Pair<Path, Paint>> getPaths() {
        return paths;
    }


    public void setPaths(ArrayList<Pair<Path, Paint>> paths) {
        this.paths = paths;
    }


    public ArrayList<Pair<Path, Paint>> getUndonePaths() {
        return undonePaths;
    }


    public void setUndonePaths(ArrayList<Pair<Path, Paint>> undonePaths) {
        this.undonePaths = undonePaths;
    }


    public int getStrokeSize() {
        return Math.round(this.strokeSize);
    }


    public void setSize(int size, int eraserOrStroke) {
        switch (eraserOrStroke) {
            case STROKE:
                strokeSize = size;
                break;
            case ERASER:
                eraserSize = size;
                break;
        }

    }


    public int getStrokeColor() {
        return this.strokeColor;
    }


    public void setStrokeColor(int color) {
        strokeColor = color;
    }


    public void eraser() {
        paths.clear();
        undonePaths.clear();
        invalidate();
    }


    public void setOnDrawChangedListener(OnDrawChangedListener listener) {
        this.onDrawChangedListener = listener;
    }

    public interface OnDrawChangedListener {
        void onDrawChanged();
    }
}

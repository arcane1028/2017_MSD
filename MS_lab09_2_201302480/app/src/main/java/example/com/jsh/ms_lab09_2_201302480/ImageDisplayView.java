package example.com.jsh.ms_lab09_2_201302480;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Created by JSH on 2017-11-03.
 */

class ImageDisplayView extends View implements OnTouchListener {

    public static final String TAG = "ImageDisplayView";

    Context mContext;
    Canvas mCanvas;
    Bitmap mBitmap;
    Paint mPaint;

    int lastX;
    int lastY;

    Bitmap sourceBitmap;
    Matrix mMatrix;

    float sourceWidth;
    float sourceHeight;

    float bitmapCenterX;
    float bitmapCenterY;

    float scaleRatio;
    float totalScaleRatio;

    float displayWidth = 0.0F;
    float displayHeight = 0.0F;

    int displayCenterX = 0;
    int displayCenterY = 0;

    public float startX;
    public float startY;

    public static float MAX_SCALE_RATIO = 5.0F;
    public static float MIN_SCALE_RATIO = 0.1F;

    float oldDistance = 0.0F;

    int oldPointCount = 0;
    boolean isScrolling = false;
    float distanceThreshold = 3.0F;


    public ImageDisplayView(Context context) {
        super(context);

        this.mContext = context;

        init();
    }

    public ImageDisplayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        init();
    }

    private void init() {
        mPaint = new Paint();
        mMatrix = new Matrix();

        lastX = -1;
        lastY = -1;

        setOnTouchListener(this);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w > 0 && h > 0) {
            newImage(w, h);

            redraw();
        }
    }

    public void newImage(int width, int height) {
        Bitmap img = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(img);

        mBitmap = img;
        mCanvas = canvas;

        displayWidth = (float) width;
        displayHeight = (float) height;

        displayCenterX = width / 2;
        displayCenterY = height / 2;
    }

    public void drawBackground(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
        }
    }

    protected void onDraw(Canvas canvas) {
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    public void setImageData(Bitmap image) {
        recycle();

        sourceBitmap = image;

        sourceWidth = sourceBitmap.getWidth();
        sourceHeight = sourceBitmap.getHeight();

        bitmapCenterX = sourceBitmap.getWidth() / 2;
        bitmapCenterY = sourceBitmap.getHeight() / 2;

        scaleRatio = 1.0F;
        totalScaleRatio = 1.0F;
    }

    public void recycle() {
        if (sourceBitmap != null) {
            sourceBitmap.recycle();
        }
    }

    public void redraw() {
        if (sourceBitmap == null) {
            Log.d(TAG, "sourceBitmap is null int redraw().");
            return;
        }

        drawBackground(mCanvas);

        float originX = (displayWidth - (float) sourceBitmap.getWidth()) / 2.0F;
        float originY = (displayHeight - (float) sourceBitmap.getHeight()) / 2.0F;

        mCanvas.translate(originX, originY);
        mCanvas.drawBitmap(sourceBitmap, mMatrix, mPaint);
        mCanvas.translate(-originX, -originY);

        invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        final int action = motionEvent.getAction();

        int pointerCount = motionEvent.getPointerCount();
        Log.d(TAG, "Pointer Count : " + pointerCount);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (pointerCount == 1) {
                    float curX = motionEvent.getX();
                    float curY = motionEvent.getY();

                    startX = curX;
                    startY = curY;
                } else if (pointerCount == 2) {
                    oldDistance = 0.0F;

                    isScrolling = true;
                }
                return true;

            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 1) {
                    if (isScrolling) {
                        return true;
                    }

                    float curX = motionEvent.getX();
                    float curY = motionEvent.getY();

                    if (startX == 0.0F) {
                        startX = curX;
                        startY = curY;

                        return true;
                    }

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if (oldPointCount == 2) {
                    } else {
                        Log.d(TAG, "ACTION : " + offsetX + ", " + offsetY);
                        if (totalScaleRatio > 1.0F) {
                            moveImage(-offsetX, -offsetY);
                        }
                        startX = curX;
                        startY = curY;
                    }
                } else if (pointerCount == 2) {
                    float x1 = motionEvent.getX();
                    float y1 = motionEvent.getY();
                    float x2 = motionEvent.getX(1);
                    float y2 = motionEvent.getY(1);
                    float dx = x1 - x2;
                    float dy = y1 - y2;
                    float distance = new Double(Math.sqrt(new Float(dx * dx + dy * dy).doubleValue())).floatValue();

                    float outScaleRatio = 0.0F;
                    if (oldDistance == 0.0F) {
                        oldDistance = distance;
                        break;
                    }
                    if (distance > oldDistance) {
                        if ((distance - oldDistance) < distanceThreshold) {
                            return true;
                        }
                        outScaleRatio = scaleRatio + (oldDistance / distance * 0.05F);

                    } else if (distance < oldDistance) {
                        if ((oldDistance - distance) < distanceThreshold) {
                            return true;
                        }
                        outScaleRatio = scaleRatio - (distance / oldDistance * 0.05F);
                    }
                    if (outScaleRatio < MIN_SCALE_RATIO || outScaleRatio > MAX_SCALE_RATIO) {
                        Log.d(TAG, "Invalid scaleRatio : " + outScaleRatio);
                    } else {
                        Log.d(TAG, "Distance : " + distance + ", ScaleRatio : " + outScaleRatio);
                        scaleImage(outScaleRatio);
                    }
                    oldDistance = distance;
                }
                oldPointCount = pointerCount;

                break;

            case MotionEvent.ACTION_UP:
                if (pointerCount == 1) {
                    float curX = motionEvent.getX();
                    float curY = motionEvent.getY();

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if (oldPointCount == 2) {
                    } else {
                        moveImage(-offsetX, -offsetY);
                    }
                } else {
                    isScrolling = false;
                }
                return true;
        }
        return true;
    }

    private void scaleImage(float inScaleRatio)
    {
        Log.d(TAG,"scaleImage() called : "+inScaleRatio);

        mMatrix.postScale(inScaleRatio, inScaleRatio, bitmapCenterX, bitmapCenterY);
        mMatrix.postRotate(0);

        totalScaleRatio = totalScaleRatio * inScaleRatio;

        redraw();
    }

    private void moveImage(float offsetX, float offsetY)
    {
        Log.d(TAG, "moveImage() called : "+offsetX+", "+offsetY);

        mMatrix.postTranslate(offsetX, offsetY);

        redraw();
    }
}
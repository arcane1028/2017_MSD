package jsh.com.ms_lab11_1_201302480;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by JSH on 2017-11-16.
 */

public class CoverFlow extends Gallery
{

    private Camera camera = new Camera();
    public static int maxRotationAngle = 55;
    public static int maxZoom = -60;
    private int centerPoint;

    public CoverFlow(Context context)
    {
        super(context);
        init();
    }


    public CoverFlow(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public CoverFlow(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        this.setStaticTransformationsEnabled(true);
    }

    private int getCenterOfCoverflow()
    {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }

    private int getCenterOfView(View view)
    {
        Log.d("Test", view.getLeft() + " " + view.getWidth() / 2);
        return view.getLeft() + view.getWidth() / 2;
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t)
    {
        final int childCenter = getCenterOfView(child);
        final int childWidth = child.getWidth();
        int rotationAngle = 0;

        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX);
        //Log.d("Test", childCenter + " " + centerPoint);

        if (childCenter == centerPoint)
        {
            Log.d("Test", "Center");
            transformImageBitmap((ImageView) child, t, 0);
        } else
        {
            rotationAngle = (int) (((float) (centerPoint - childCenter) / childWidth) * maxRotationAngle);
            if (Math.abs(rotationAngle) > maxRotationAngle)
            {
                rotationAngle = (rotationAngle < 0) ? -maxRotationAngle : maxRotationAngle;
            }
            transformImageBitmap((ImageView) child, t, rotationAngle);
        }
        return true;
    }
    private boolean isCenter(int childCenter){



        return false;
    }

    private void transformImageBitmap(ImageView child, Transformation t, int rotationAngle)
    {
        camera.save();

        final Matrix imageMatrix = t.getMatrix();
        final int imageHeight = child.getLayoutParams().height;
        final int imageWidth = child.getLayoutParams().width;
        final int rotation = Math.abs(rotationAngle);

        camera.translate(0.0f, 0.0f, 100.0f);

        if (rotation < maxRotationAngle)
        {
            float zoomAmount = (float) (maxZoom + (rotation * 1.5));
            camera.translate(0.0f, 0.0f, zoomAmount);
        }

        camera.rotateY(rotationAngle);
        camera.getMatrix(imageMatrix);

        imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
        imageMatrix.postTranslate((imageWidth / 2), (imageHeight / 2));

        camera.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        centerPoint = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }
}

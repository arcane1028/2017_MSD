package example.com.jsh.ms_hw09_201302480;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by JSH on 2017-11-11.
 */

public class CustomView extends View {

    private Paint paint;
    private Canvas mCanvas;
    int x = 100, y = 100;


    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(x, y, 200, 200, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                invalidate();

        }

        return super.onTouchEvent(event);
    }
}

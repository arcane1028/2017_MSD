package example.com.jsh.ms_hw09_201302480;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by JSH on 2017-11-11.
 */

public class CustomView extends View {

    private Paint paint;
    float x = 100, y = 100;
    float positionX = 100, positionY = 100;


    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(positionX - x / 2, positionY - y / 2,
                positionX + x / 2, positionY + y / 2, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            positionX = event.getX();
            positionY = event.getY();
        }else if (action == MotionEvent.ACTION_MOVE){
            positionX = event.getX();
            positionY = event.getY();
        }else if (action == MotionEvent.ACTION_UP){
            positionX = event.getX();
            positionY = event.getY();
        }
            invalidate();
        return true;
    }
}

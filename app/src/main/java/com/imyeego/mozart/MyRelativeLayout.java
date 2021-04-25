package com.imyeego.mozart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * @authur : liuzhao
 * @time : 2/24/21 7:49 PM
 * @Des :
 */
public class MyRelativeLayout extends RelativeLayout {

    private float y = 0f;


    public MyRelativeLayout(Context context) {
        super(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("MyRelativeLayout", "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }
//
//
//
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("MyRelativeLayout", "onTouchEvent");
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            y = event.getY();
            Log.e("MyRelativeLayout", String.format("y:%f", y));
        }

        return super.onTouchEvent(event);
    }
//
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("MyRelativeLayout", "onInterceptTouchEvent");
        int action = ev.getAction();

//        if (action == MotionEvent.ACTION_DOWN) {
//            y = ev.getY();
//            return y > getMeasuredHeight() / 2;
//        }
        return false;
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }
}

package com.imyeego.mozart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * @authur : liuzhao
 * @time : 2/24/21 8:23 PM
 * @Des :
 */
public class MyTextView extends androidx.appcompat.widget.AppCompatTextView {

    private float y = 0f;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("MyTextView", "dispatchTouchEvent");

        return super.dispatchTouchEvent(ev);
    }
////
////
////
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        if (action == MotionEvent.ACTION_DOWN) {
//            y = event.getY();
//            Log.e("MyTextView down", String.format("y:%f", y));
//            return true;
//        }
//        if (action == MotionEvent.ACTION_MOVE) {
//            y = event.getY();
//            Log.e("MyTextView move", String.format("y:%f", y));
//
//        }
        return super.onTouchEvent(event);
    }
}

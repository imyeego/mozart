package com.imyeego.mozart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @authur : liuzhao
 * @time : 3/20/21 8:57 PM
 * @Des :
 */
public class MyScrollView extends ScrollView {

    private static final String TAG = "MyScrollView";


    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e(TAG, "dispatchTouchEvent");
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.e(TAG, "dispatchTouchEvent down: " + ev.getY());
//
//        }
//
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            Log.e(TAG, "dispatchTouchEvent move: " + ev.getY());
//
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            Log.e(TAG, "onInterceptTouchEvent down: " + ev.getY());

        }

        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            Log.e(TAG, "onInterceptTouchEvent move: ");

            return ev.getY() > getMeasuredHeight() >> 1;

        }
        return super.onInterceptTouchEvent(ev);
//        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        Log.e(TAG, "onTouchEvent");

        return super.onTouchEvent(ev);
    }
}

package com.example.newsreader.utils;

import android.annotation.SuppressLint;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

public class ScrollUtils {
    @SuppressLint("ClickableViewAccessibility")
    public static void enableScroll(TextView textView) {
        textView.setMovementMethod(new ScrollingMovementMethod());

        textView.setOnTouchListener((v, event) -> {
            v.getParent().requestDisallowInterceptTouchEvent(true);
            if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return false;
        });
    }
}

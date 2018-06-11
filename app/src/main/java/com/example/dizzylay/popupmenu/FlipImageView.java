package com.example.dizzylay.popupmenu;

import android.content.Context;
import android.view.MotionEvent;

public class FlipImageView extends android.support.v7.widget.AppCompatImageView {

    private int downX;
    private int downY;

    public FlipImageView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                break;
            case MotionEvent.ACTION_MOVE:

        }
        return true;
    }

}

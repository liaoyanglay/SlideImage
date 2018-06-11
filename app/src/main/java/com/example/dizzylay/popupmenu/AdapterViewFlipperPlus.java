package com.example.dizzylay.popupmenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterViewFlipper;

public class AdapterViewFlipperPlus extends AdapterViewFlipper {

//    int mWhichChild = 0;

    private int downX;
    private int downY;
    private float initX;
    private float initY;
    private View currentView;
    private ObjectAnimator nextOutAnimator;
    private ObjectAnimator nextInAnimator;
    private ObjectAnimator previousInAnimator;
    private ObjectAnimator previousOutAnimator;
    private static final String TAG = "AdapterViewFlipperPlus";

    public AdapterViewFlipperPlus(Context context) {
        super(context);
    }

    public AdapterViewFlipperPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AdapterViewFlipperPlus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setDisplayedChild(int whichChild) {
        super.setDisplayedChild(whichChild);
        initX = getTranslationX();
        initY = getTranslationY();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        currentView = getCurrentView();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                Log.d(TAG, "onTouchEvent: down");
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetX = (x - downX) * 0.7f;
                float offsetY = (y - downY) * 0.7f;
                float percent = Math.abs(offsetX / 2 / 580) + Math.abs(offsetY / 2 / 1000);
                currentView.setTranslationX(offsetX);
                currentView.setTranslationY(offsetY);
                currentView.setScaleX(1 - percent);
                currentView.setScaleY(1 - percent);
                currentView.setRotation(360 * percent);
                currentView.setAlpha(1 - percent);
                break;
            case MotionEvent.ACTION_UP:
                if (downY - y > 200) {
                    showNext();
                } else if (y - downY > 200) {
                    showPrevious();
                } else {
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(
                            currentView, "translationX", initX);
                    ObjectAnimator animator2 = ObjectAnimator.ofFloat(
                            currentView, "translationY", initY);
                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(
                            currentView, "scaleX", 1);
                    ObjectAnimator animator4 = ObjectAnimator.ofFloat(
                            currentView, "scaleY", 1);
                    ObjectAnimator animator5 = ObjectAnimator.ofFloat(
                            currentView, "rotation", 0f);
                    ObjectAnimator animator6 = ObjectAnimator.ofFloat(
                            currentView, "alpha", 1);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(animator1, animator2, animator3,
                            animator4, animator5, animator6);
                    animatorSet.start();
                }
                break;
        }
        return true;
    }

    @Override
    public void showNext() {
        if (nextOutAnimator != null) {
            setOutAnimation(nextOutAnimator);
        }
        if (nextInAnimator != null) {
            setInAnimation(nextInAnimator);
        }
        super.showNext();
    }

    @Override
    public void showPrevious() {
        if (previousOutAnimator != null) {
            setOutAnimation(previousOutAnimator);
        }
        if (previousInAnimator != null) {
            setInAnimation(previousInAnimator);
        }
        super.showPrevious();
    }


    public void setNextOutAnimator(ObjectAnimator nextOutAnimator) {
        this.nextOutAnimator = nextOutAnimator;
    }

    public void setNextInAnimator(ObjectAnimator nextInAnimator) {
        this.nextInAnimator = nextInAnimator;
    }

    public void setPreviousInAnimator(ObjectAnimator previousInAnimator) {
        this.previousInAnimator = previousInAnimator;
    }

    public void setPreviousOutAnimator(ObjectAnimator previousOutAnimator) {
        this.previousOutAnimator = previousOutAnimator;
    }
}

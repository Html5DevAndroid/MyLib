package bk.itc.html5.mylib.component.view.switcher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

/**
 * Created by Hien on 5/7/2018.
 */

public class SwitcherView extends CoordinatorLayout {
    public static final int DIR_LEFT = 1;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_DOWN = 3;
    public static final int DIR_UP = 4;

    private boolean mIsAnimating = false;

    public SwitcherView(@NonNull Context context) {
        super(context);
        init();
    }

    public SwitcherView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SwitcherView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {}

    public void replaceView(View oldView, View newView) {
        int index = indexOfChild(oldView);
        removeView(oldView);
        addView(newView, index);
    }

    public void turnOn(View view) {
        int index = indexOfChild(view);
        if(index > -1) {
            getChildAt(index).setVisibility(VISIBLE);
        }
    }

    public void turnOff(View view) {
        int index = indexOfChild(view);
        if(index > -1) {
            getChildAt(index).setVisibility(GONE);
        }
    }

    public void turnOffOther(View view) {
        for (int i=0; i<getChildCount(); i++) {
            View v = getChildAt(i);
            if(v != view) {
                v.setVisibility(GONE);
            }
        }

        bringChildToFront(view);
    }

    public void turnOffAll() {
        for (int i=0; i<getChildCount(); i++) {
            View v = getChildAt(i);
            v.setVisibility(GONE);
        }
    }

    public View getTopView() {
        for (int i=getChildCount()-1; i>=0; i--) {
            View view = getChildAt(i);
            if(view.getVisibility() == VISIBLE)
                return view;
        }
        return null;
    }

    public void replaceWithSlideAndFade(View view, int duration, int dir, final boolean isRemove) {
        final View top = getTopView();
        if(top == view) {
            return;
        }

        if(indexOfChild(view) < 0) {
            turnOffOther(top);
            addView(view);
        } else {
            turnOffOther(top);
            turnOn(view);
        }

        mIsAnimating = true;

        animFade(top, view, duration, null);

        animSlide(top, view, dir, duration, new AnimationListener.Stop() {
            @Override
            public void onStop() {
                if(isRemove) {
                    removeView(top);
                }else {
                    turnOff(top);
                    top.setX(0);
                    top.setY(0);
                }

                mIsAnimating = false;
            }
        });
    }

    public void replaceWithFade(View view, int duration, final boolean isRemove) {
        final View top = getTopView();
        if(top == view) {
            return;
        }

        if(indexOfChild(view) < 0) {
            turnOffOther(top);
            addView(view);
        } else {
            turnOffOther(top);
            turnOn(view);
        }

        mIsAnimating = true;

        animFade(top, view, duration, new AnimationListener.Stop() {
            @Override
            public void onStop() {
                if(isRemove) {
                    removeView(top);
                }else {
                    turnOff(top);
                }

                mIsAnimating = false;
            }
        });
    }

    private void animFade(final View viewOut, final View viewIn, final int duration, final AnimationListener.Stop listener) {
        if (getWidth() > 0) {
            animFadeRun(viewOut, viewIn, duration, listener);
        }else {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    animFadeRun(viewOut, viewIn, duration, listener);
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    private void animFadeRun(View viewOut, View viewIn, int duration, AnimationListener.Stop listener) {
        ViewAnimator.animate(viewOut)
                .alpha(1, 0)
                .duration(duration)
                .onStop(listener)
                .start();
        ViewAnimator.animate(viewIn)
                .alpha(0, 1)
                .duration(duration)
                .start();
    }

    public void addViewWithSlide(final View view, int dir, int duration) {
        if(indexOfChild(view) < 0) {
            addView(view);
        } else {
            bringChildToFront(view);
        }

        mIsAnimating = true;

        animSlideSingle(view, dir, duration, new AnimationListener.Stop() {
            @Override
            public void onStop() {
                turnOffOther(view);

                mIsAnimating = false;
            }
        });
    }

    private void animSlideSingle(final View viewIn, final int dir, final int duration, final AnimationListener.Stop listener) {
        if (getWidth() > 0) {
            animSlideSingleRun(viewIn, dir, duration, listener);
        }else {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    animSlideSingleRun(viewIn, dir, duration, listener);
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    private void animSlideSingleRun(View viewIn, int dir, int time, AnimationListener.Stop listener) {

        if(dir == DIR_LEFT) {
            ViewAnimator.animate(viewIn)
                    .translationX(getWidth(), 0)
                    .duration(time)
                    .onStop(listener)
                    .start();
        }
        if(dir == DIR_RIGHT) {
            ViewAnimator.animate(viewIn)
                    .translationX(-getWidth(), 0)
                    .duration(time)
                    .onStop(listener)
                    .start();
        }
        if(dir == DIR_DOWN) {
            ViewAnimator.animate(viewIn)
                    .translationY(-getHeight(), 0)
                    .duration(time)
                    .onStop(listener)
                    .start();
        }
        if(dir == DIR_UP) {
            ViewAnimator.animate(viewIn)
                    .translationY(getHeight(), 0)
                    .duration(time)
                    .onStop(listener)
                    .start();
        }
    }

    public void replaceWithSlide(View view, int dir, int duration, final boolean isRemove) {
        final View top = getTopView();
        if(top == view) {
            return;
        }

        if(indexOfChild(view) < 0) {
            turnOffOther(top);
            addView(view);
        } else {
            turnOffOther(top);
            turnOn(view);
        }

        mIsAnimating = true;

        animSlide(top, view, dir, duration, new AnimationListener.Stop() {
            @Override
            public void onStop() {
                if(isRemove) {
                    removeView(top);
                }else {
                    turnOff(top);
                    top.setX(0);
                    top.setY(0);
                }

                mIsAnimating = false;
            }
        });
    }

    private void animSlide(final View viewOut, final View viewIn, final int dir, final int duration, final AnimationListener.Stop listener) {
        if (getWidth() > 0) {
            animSlideRun(viewOut, viewIn, dir, duration, listener);
        }else {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    animSlideRun(viewOut, viewIn, dir, duration, listener);
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    private void animSlideRun(View viewOut, View viewIn, int dir, int time, AnimationListener.Stop listener) {

        if(dir == DIR_LEFT) {
            ViewAnimator.animate(viewOut)
                    .translationX(0, -getWidth())
                    .duration(time)
                    .onStop(listener)
                    .start();
            ViewAnimator.animate(viewIn)
                    .translationX(getWidth(), 0)
                    .duration(time)
                    .start();
        }
        if(dir == DIR_RIGHT) {
            ViewAnimator.animate(viewOut)
                    .translationX(0, getWidth())
                    .duration(time)
                    .onStop(listener)
                    .start();
            ViewAnimator.animate(viewIn)
                    .translationX(-getWidth(), 0)
                    .duration(time)
                    .start();
        }
        if(dir == DIR_DOWN) {
            ViewAnimator.animate(viewOut)
                    .translationY(0, getHeight())
                    .duration(time)
                    .onStop(listener)
                    .start();
            ViewAnimator.animate(viewIn)
                    .translationY(-getHeight(), 0)
                    .duration(time)
                    .start();
        }
        if(dir == DIR_UP) {
            ViewAnimator.animate(viewOut)
                    .translationY(0, -getHeight())
                    .duration(time)
                    .onStop(listener)
                    .start();
            ViewAnimator.animate(viewIn)
                    .translationY(getHeight(), 0)
                    .duration(time)
                    .start();
        }
    }

    public boolean isAnimating() {
        return mIsAnimating;
    }
}

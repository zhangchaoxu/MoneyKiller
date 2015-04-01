package com.lean56.app.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * CircleButton
 */
public class CircleButton extends ImageView {

    private static final int ANIMATION_TIME_ID = 0x10E0000;
    private static final int DEFAULT_PRESSED_RING_WIDTH_DIP = 0x04;
    private static final int PRESSED_COLOR_LIGHTUP = 0x02;
    private static final int PRESSED_RING_ALPHA = 0x4B;
    private float animationProgress;
    private int centerX;
    private int centerY;
    private Paint circlePaint;
    private int defaultColor;
    private int outerRadius;
    private ObjectAnimator pressedAnimator;
    private int pressedColor;
    private int pressedRingRadius;
    private int pressedRingWidth;

    public CircleButton(Context context) {
        super(context);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}

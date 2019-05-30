package com.example.a04_valueanimator_advanced.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.example.a04_valueanimator_advanced.evaluator.PointEvaluator;

/**
 * @创建者 mingyan.su
 * @创建时间 2019/5/27 15:33
 * @类描述 ${TODO}
 */
public class MyPointView extends View {
    private Point mPoint;

    public MyPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPoint != null) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            canvas.drawCircle(500f, 600f, mPoint.getRadius(), paint);
        }
    }

    public void doAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), new Point(20), new Point(300));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.start();
    }
}

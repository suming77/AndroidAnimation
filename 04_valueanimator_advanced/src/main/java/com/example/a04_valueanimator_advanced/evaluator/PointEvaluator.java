package com.example.a04_valueanimator_advanced.evaluator;

import android.animation.TypeEvaluator;

import com.example.a04_valueanimator_advanced.view.Point;

/**
 * @创建者 mingyan.su
 * @创建时间 2019/5/27 14:45
 * @类描述 ${TODO}自定义字母渐变Evaluator
 */
public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startPoint, Point endPoint) {
        int startInt = startPoint.getRadius();
        int endInt = endPoint.getRadius();
        int value = (int) (startInt + fraction * (endInt - startInt));
        return new Point(value);
    }
}

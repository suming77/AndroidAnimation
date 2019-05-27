package com.example.a04_valueanimator_advanced;

import android.animation.TypeEvaluator;

/**
 * @创建者 mingyan.su
 * @创建时间 2019/5/24 11:29
 * @类描述 ${TODO}自定义Evaluator
 */
public class MyEvaluator implements TypeEvaluator<Integer> {
    @Override
    public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
        return (int) (200 + startValue + fraction * (endValue - startValue));
    }
}

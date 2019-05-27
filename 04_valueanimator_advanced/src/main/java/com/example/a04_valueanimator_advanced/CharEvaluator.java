package com.example.a04_valueanimator_advanced;

import android.animation.TypeEvaluator;

/**
 * @创建者 mingyan.su
 * @创建时间 2019/5/27 14:45
 * @类描述 ${TODO}自定义字母渐变Evaluator
 */
public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int value = (int) (startInt + fraction * (endInt - startInt));
        return (char) value;
    }
}

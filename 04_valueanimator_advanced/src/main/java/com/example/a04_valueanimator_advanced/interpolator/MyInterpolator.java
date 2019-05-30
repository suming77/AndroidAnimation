package com.example.a04_valueanimator_advanced.interpolator;

import android.view.animation.BaseInterpolator;

/**
 * @创建者 mingyan.su
 * @创建时间 2019/5/23 18:20
 * @类描述 ${TODO}自定义Interpolator
 */

public class MyInterpolator extends BaseInterpolator {
    @Override
    public float getInterpolation(float input) {
        return 1 - input;
    }
}

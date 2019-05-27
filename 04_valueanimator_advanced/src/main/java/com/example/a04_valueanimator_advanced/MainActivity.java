package com.example.a04_valueanimator_advanced;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 插值器,Evaluator,ofObject()的使用
 * 我的博客地址：https://blog.csdn.net/m0_37796683/article/details/90483462
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;
    private MyPointView myPointView;
    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPointView = findViewById(R.id.myPointView);
        mTextView = findViewById(R.id.tv);
        mTextView.setOnClickListener(this);
        findViewById(R.id.tv_start_anim).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_start_anim:
                setMyPointView();
                break;
            case R.id.tv_cancel:
                if (mValueAnimator != null) {
                    //退出动画
                    mValueAnimator.cancel();
                }
                break;
            default:
                break;
        }
    }

    /**
     * ReverseEvaluator的使用
     */
    private ValueAnimator setReverseEvaluator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(1000);
        valueAnimator.setEvaluator(new ReverseEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mTextView.layout(mTextView.getLeft(), value, mTextView.getRight(), value + mTextView.getHeight());
                Log.e(TAG, "value:" + value);
            }
        });
        valueAnimator.start();
        return valueAnimator;
    }

    /**
     * MyEvaluator的使用
     */
    private ValueAnimator setMyEvaluator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(1000);
        valueAnimator.setEvaluator(new MyEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mTextView.layout(mTextView.getLeft(), value, mTextView.getRight(), value + mTextView.getHeight());
                Log.e(TAG, "value:" + value);
            }
        });
        valueAnimator.start();
        return valueAnimator;
    }

    /**
     * MyInterpolator的使用
     */
    private ValueAnimator setMyInterpolator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new MyInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mTextView.layout(mTextView.getLeft(), value, mTextView.getRight(), value + mTextView.getHeight());
                Log.e(TAG, "value:" + value);
            }
        });
        valueAnimator.start();
        return valueAnimator;
    }

    /**
     * ArgbEvaluator的使用
     */
    private ValueAnimator setArgbEvaluator() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0xff000000, 0xffffffff);
        valueAnimator.setDuration(2000);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mTextView.setBackgroundColor(value);
                Log.e(TAG, "value:" + value);
            }
        });
        valueAnimator.start();
        return valueAnimator;
    }

    /**
     * ofObject的使用1
     */
    private ValueAnimator setOfObject() {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
        valueAnimator.setDuration(6000);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char value = (char) animation.getAnimatedValue();
                mTextView.setText(String.valueOf(value));
                Log.e(TAG, "value:" + value);
            }
        });
        valueAnimator.start();
        return valueAnimator;
    }

    /**
     * ofObject的使用2
     */
    private void  setMyPointView() {
        myPointView.doAnimator();
    }
}

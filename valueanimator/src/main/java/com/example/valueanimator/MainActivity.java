package com.example.valueanimator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;
    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.tv);
        mTextView.setOnClickListener(this);
        findViewById(R.id.tv_start_anim).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_pause).setOnClickListener(this);
        findViewById(R.id.tv_resume).setOnClickListener(this);
        findViewById(R.id.tv_remove).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_start_anim:
                mValueAnimator = setAnimatorListener();
                break;
            case R.id.tv_cancel:
                if (mValueAnimator != null) {
                    //退出动画
                    mValueAnimator.cancel();
                }
                break;
            case R.id.tv_pause:
                if (mValueAnimator != null) {
                    //暂停动画
                    mValueAnimator.pause();
                }
                break;
            case R.id.tv_resume:
                if (mValueAnimator != null) {
                    //继续动画
                    mValueAnimator.resume();
                }
                break;
                case R.id.tv_remove:
                if (mValueAnimator != null) {
                    //移除动画监听
                    mValueAnimator.removeAllListeners();
                }
                break;
            default:
                break;
        }
    }

    /**
     * ofInt()的 使用
     */
    private void setAnimator1() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mTextView.layout(value, value, value + mTextView.getWidth(), value + mTextView.getHeight());
                Log.e(TAG, "value:" + value);
            }
        });
        valueAnimator.start();
    }

    /**
     * ofFloat()的使用
     */
    private void setAnimator2() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 400f, 200f, 600f);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float v = (Float) animation.getAnimatedValue();
                int value = v.intValue();
                mTextView.layout(value, value, value + mTextView.getWidth(), value + mTextView.getHeight());
                Log.e(TAG, "value:" + value);
            }
        });
        valueAnimator.start();
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }

    /**
     * 其他方法调用
     *
     * @return
     */
    private ValueAnimator setAnimator3() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 600f);
        //设置动画时长 2秒
        valueAnimator.setDuration(2000);
        //设置重复次数，无限次
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //设置重复类型，倒叙从新开始
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float v = (Float) animation.getAnimatedValue();
                int value = v.intValue();
                mTextView.layout(mTextView.getLeft(), value, mTextView.getRight(), value + mTextView.getHeight());
                Log.e(TAG, "value:" + value);
            }
        });

        //设置2秒启动动画
        valueAnimator.setStartDelay(2000);
        //开启动画
        valueAnimator.start();
        return valueAnimator;
    }

    /**
     * 监听器的使用
     *
     * @return
     */
    private ValueAnimator setAnimatorListener() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 200);
        //设置动画时长 2秒
        valueAnimator.setDuration(1000);
        //设置重复次数，无限次
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //设置重复类型，倒叙从新开始
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                mTextView.layout(mTextView.getLeft(), value, mTextView.getRight(), value + mTextView.getHeight());
//                Log.e(TAG, "AnimatorUpdateListener: value:" + value);
            }
        });

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(TAG, "AnimatorListener:Start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG, "AnimatorListener:End");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e(TAG, "AnimatorListener:Cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(TAG, "AnimatorListener:Repeat");
            }
        });

        valueAnimator.addPauseListener(new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animation) {
                Log.e(TAG, "AnimatorPauseListener:Pause");
            }

            @Override
            public void onAnimationResume(Animator animation) {
                Log.e(TAG, "AnimatorPauseListener:Resume");
            }
        });
        //开启动画
        valueAnimator.start();
        return valueAnimator;
    }
}

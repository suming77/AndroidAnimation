package com.example.a05_objectanimator;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ObjectAnimator的基本使用
 * 我的博客：https://blog.csdn.net/m0_37796683/article/details/90607428
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;
    private ObjectAnimator mObjectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                backgroundColor();
                break;
            case R.id.tv_cancel:
                if (mObjectAnimator != null) {
                    //退出动画
                    mObjectAnimator.cancel();
                }
                break;
            default:
                break;
        }
    }


    /**
     * 透明度
     */
    private void alpha() {
        mObjectAnimator = ObjectAnimator.ofFloat(mTextView, "alpha", 1f, 0f, 1f);
        mObjectAnimator.setDuration(2000);
        mObjectAnimator.start();
        AnimatorSet animatorSet = new AnimatorSet();
    }

    /**
     * 围绕Z轴旋转
     */
    private void rotation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "rotation", 0f, 360f, 0f);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 围绕X轴旋转
     */
    private void rotationX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "rotationX", 0f, 360f, 0f);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 围绕Y轴旋转
     */
    private void rotationY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "rotationY", 0f, -360f, 0f);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 围绕X轴缩放
     */
    private void scaleX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "scaleX", 0f, 4f, 2f, 1f);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 围绕Y轴缩放
     */
    private void scaleY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "scaleY", 0f, 4f, 2f, 1f);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 在X轴方向移动
     */
    private void translationX() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "translationX", 0f, 400f, -200f, 0f);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 在Y轴方向移动
     */
    private void translationY() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTextView, "translationY", 0f, 400f, -200f, 0f);
        animator.setDuration(3000);
        //设置循环模式，倒叙回放
        animator.setRepeatMode(ValueAnimator.REVERSE);
        //循环次数，这里设置了无限循环
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    /**
     * 设置背景颜色值
     */
    private void backgroundColor() {
        ObjectAnimator animator = ObjectAnimator.ofInt(mTextView, "backgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
        animator.setDuration(8000);
        animator.setEvaluator(new ArgbEvaluator());
        animator.start();
    }


}

package com.example.a06_animatorset;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 动画集合：AnimatorSet和propertyValuesHolder的使用
 * 我的博客：https://blog.csdn.net/m0_37796683/article/details/90645047
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView1;
    private TextView mTextView2;
    private AnimatorSet mAnimatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView1 = findViewById(R.id.tv1);
        mTextView2 = findViewById(R.id.tv2);
        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        findViewById(R.id.tv_start_anim).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_anim:
//                mAnimatorSet = setOther();
                propertyValuesHolder();
                break;
            case R.id.tv_cancel:
                if (mAnimatorSet != null) {
                    //退出动画
                    mAnimatorSet.cancel();
                }
                break;
            default:
                break;
        }
    }


    /**
     * AnimatorSet的基本使用
     *
     * @return
     */
    private AnimatorSet setAnimatorSet() {
        ObjectAnimator color = ObjectAnimator.ofArgb(mTextView1, "backgroundColor", 0xffff00ff, 0xffffff00, 0xff0000ff);
        color.setEvaluator(new ArgbEvaluator());
        color.setRepeatCount(ValueAnimator.INFINITE);
        color.setRepeatMode(ValueAnimator.REVERSE);
        ObjectAnimator translation = ObjectAnimator.ofFloat(mTextView1, "translationY", 0f, 400f, 0f);
        //构建AnimatorSet实例
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(color, translation);
        //添加动画监听
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(TAG, "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG, "onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e(TAG, "onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(TAG, "onAnimationRepeat");
            }
        });
        //设置动画时长
        animatorSet.setDuration(2000);
        //开启动画
        animatorSet.start();
        return animatorSet;
    }

    /**
     * playTogether的使用
     *
     * @return
     */
    private AnimatorSet animatorSetTogether() {
        ObjectAnimator color = ObjectAnimator.ofArgb(mTextView1, "backgroundColor", 0xffff00ff, 0xffffff00, 0xff0000ff);
        color.setEvaluator(new ArgbEvaluator());
        ObjectAnimator translation = ObjectAnimator.ofFloat(mTextView1, "translationY", 0f, 400f, 0f);
        ObjectAnimator translation2 = ObjectAnimator.ofFloat(mTextView2, "translationY", 0f, 600f, 0f);
        //构建AnimatorSet实例
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(color, translation, translation2);
        //设置动画时长
        animatorSet.setDuration(2000);
        //开启动画
        animatorSet.start();
        return animatorSet;
    }

    /**
     * playSequentially的使用
     *
     * @return
     */
    private AnimatorSet sequentially() {
        ObjectAnimator color = ObjectAnimator.ofArgb(mTextView1, "backgroundColor", 0xffff00ff, 0xffffff00, 0xff0000ff);
        color.setEvaluator(new ArgbEvaluator());
        ObjectAnimator translation = ObjectAnimator.ofFloat(mTextView1, "translationY", 0f, 400f, 0f);
        ObjectAnimator translation2 = ObjectAnimator.ofFloat(mTextView2, "translationY", 0f, 600f, 0f);
        //将动画添加到集合中
        List<Animator> animatorList = new ArrayList<>();
        animatorList.add(color);
        animatorList.add(translation);
        animatorList.add(translation2);

        //构建AnimatorSet实例
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);
        //设置动画时长
        animatorSet.setDuration(2000);
        //开启动画
        animatorSet.start();
        return animatorSet;
    }

    /**
     * playSequentially的使用
     * 第一个动画无限循环
     *
     * @return
     */
    private AnimatorSet sequentiallyRepeat() {
        ObjectAnimator color = ObjectAnimator.ofArgb(mTextView1, "backgroundColor", 0xffff00ff, 0xffffff00, 0xff0000ff);
        color.setEvaluator(new ArgbEvaluator());
        //设置循环模式
        color.setRepeatMode(ValueAnimator.REVERSE);
        //设置无限循环
        color.setRepeatCount(ValueAnimator.INFINITE);

        ObjectAnimator translation = ObjectAnimator.ofFloat(mTextView1, "translationY", 0f, 400f, 0f);
        ObjectAnimator translation2 = ObjectAnimator.ofFloat(mTextView2, "translationY", 0f, 600f, 0f);
        //将动画添加到集合中
        List<Animator> animatorList = new ArrayList<>();
        animatorList.add(color);
        animatorList.add(translation);
        animatorList.add(translation2);

        //构建AnimatorSet实例
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorList);

        //设置动画时长
        animatorSet.setDuration(2000);
        //开启动画
        animatorSet.start();
        return animatorSet;
    }

    /**
     * builder
     *
     * @return
     */
    private AnimatorSet builder() {
        ObjectAnimator color1 = ObjectAnimator.ofArgb(mTextView1, "backgroundColor", 0xffff00ff, 0xffffff00, 0xff0000ff);
        color1.setEvaluator(new ArgbEvaluator());
        ObjectAnimator translation1 = ObjectAnimator.ofFloat(mTextView1, "translationY", 0f, 400f, 0f);
        ObjectAnimator translation2 = ObjectAnimator.ofFloat(mTextView2, "translationY", 0f, 600f, 0f);
        ObjectAnimator rotation2 = ObjectAnimator.ofFloat(mTextView2, "rotation", 0f, 360f, 0f);

        //构建AnimatorSet实例
        AnimatorSet animatorSet = new AnimatorSet();
        //获取builder实例
        AnimatorSet.Builder builder = animatorSet.play(color1);//builder播放动画color1
        builder.with(translation1);//跟随builder一起播放translation1
        builder.before(translation2);//builder在动画translation2之前播放
        builder.after(rotation2);//builder在动画rotation2之后播放

        //链式写法
//        builder.with(translation1).before(translation2).after(rotation2);

        //设置动画时长
        animatorSet.setDuration(2000);
        //开启动画
        animatorSet.start();
        return animatorSet;
    }

    /**
     * 其他函数的比较
     *
     * @return
     */
    private AnimatorSet setOther() {
        ValueAnimator valueAnimator = ObjectAnimator.ofPropertyValuesHolder();
        ObjectAnimator translation1 = ObjectAnimator.ofFloat(mTextView1, "translationY", 0f, 400f, 0f);
        translation1.setDuration(5000);
        translation1.setInterpolator(new BounceInterpolator());
        ObjectAnimator rotation2 = ObjectAnimator.ofFloat(mTextView2, "rotation", 0f, 360f, 0f);
        rotation2.setDuration(10000);
        rotation2.setInterpolator(new AccelerateInterpolator());

        //构建AnimatorSet实例
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translation1).with(rotation2);

        //设置动画时长
        animatorSet.setDuration(1000);
        animatorSet.setTarget(mTextView1);
        animatorSet.setInterpolator(new LinearInterpolator());
        //开启动画
        animatorSet.start();
        return animatorSet;
    }

    /**
     * PropertyValuesHolder的使用
     *
     * @return
     */
    private AnimatorSet propertyValuesHolder() {
        //创建PropertyValuesHolder实例
        PropertyValuesHolder valuesHolder1 = PropertyValuesHolder.ofInt("backgroundColor", 0xffff00ff, 0xffffff00, 0xff0000ff);
        valuesHolder1.setEvaluator(new ArgbEvaluator());
        PropertyValuesHolder valuesHolder2 = PropertyValuesHolder.ofFloat("scaleX", 0f, 2f, 0f, 3f, 1f);
        PropertyValuesHolder valuesHolder3 = PropertyValuesHolder.ofFloat("scaleY", 0f, 2f, 0f, 3f, 1f);

        //将创建PropertyValuesHolder实例添加到ObjectAnimator中
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mTextView1, valuesHolder1, valuesHolder2, valuesHolder3);
        objectAnimator.setDuration(2000);
        objectAnimator.start();

        return null;
    }
}

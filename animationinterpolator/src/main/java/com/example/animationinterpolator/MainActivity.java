package com.example.animationinterpolator;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

/**
 * 我的博客：
 * 代码动态生成alpha、scale、translate、rotate、set及插值器动画
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;
    private boolean isLoadXml = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_AccelerateDece).setOnClickListener(this);
        findViewById(R.id.btn_Accelerate).setOnClickListener(this);
        findViewById(R.id.btn_Anticipate).setOnClickListener(this);
        findViewById(R.id.btn_AnticipateOver).setOnClickListener(this);
        findViewById(R.id.btn_Bounce).setOnClickListener(this);
        findViewById(R.id.btn_Cycle).setOnClickListener(this);
        findViewById(R.id.btn_Decelerate).setOnClickListener(this);
        findViewById(R.id.btn_LinearInter).setOnClickListener(this);
        findViewById(R.id.btn_Overshoot).setOnClickListener(this);

        mTextView = findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        Interpolator interpolator = null;
        int inter = 0;
        switch (v.getId()) {
            case R.id.btn_AccelerateDece:
                interpolator = new AccelerateDecelerateInterpolator();
                inter = R.anim.anim_tranlate_acceleratedec;
                break;
            case R.id.btn_Accelerate:
                interpolator = new AccelerateInterpolator();
                inter = R.anim.anim_tranlate_accelerate;
                break;
            case R.id.btn_Anticipate:
                interpolator = new AnticipateInterpolator();
                inter = R.anim.anim_tranlate_anticipate;
                break;
            case R.id.btn_AnticipateOver:
                interpolator = new AnticipateOvershootInterpolator();
                inter = R.anim.anim_tranlate_anticipateover;
                break;
            case R.id.btn_Bounce:
                interpolator = new BounceInterpolator();
                inter = R.anim.anim_tranlate_bounce;
                break;
            case R.id.btn_Decelerate:
                interpolator = new DecelerateInterpolator();
                inter = R.anim.anim_tranlate_decelerate;
                break;
            case R.id.btn_LinearInter:
                interpolator = new LinearInterpolator();
                inter = R.anim.anim_tranlate_linearinter;
                break;
            case R.id.btn_Overshoot:
                interpolator = new OvershootInterpolator();
                inter = R.anim.anim_tranlate_overshoot;
                break;
            case R.id.btn_Cycle:
                interpolator = new CycleInterpolator(0.5f);
                inter = R.anim.anim_tranlate_cycle;
                break;
        }

        if (interpolator != null && isLoadXml) {
            //代码设置
            setInterpolator(interpolator);
        } else {
            //xml设置
            setInterpolatorXml(inter);
        }
    }

    /**
     * 代码设置动画
     *
     * @param interpolator 插值器
     */
    private void setInterpolator(Interpolator interpolator) {
        TranslateAnimation translateAnim = new TranslateAnimation(0, 300, 0, 300);
        translateAnim.setDuration(1000);
        //设置插值器
        translateAnim.setInterpolator(interpolator);
        mTextView.startAnimation(translateAnim);
    }

    /**
     * 本地xml文件加载动画
     *
     * @param interpolator xml文件
     */
    private void setInterpolatorXml(@AnimRes int interpolator) {
        Animation animation = AnimationUtils.loadAnimation(this, interpolator);
        mTextView.startAnimation(animation);
    }
}

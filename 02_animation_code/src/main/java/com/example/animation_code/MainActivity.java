package com.example.animation_code;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

/**
 * 代码动态生成alpha、scale、translate、rotate、set及插值器动画
 *     我的博客：https://blog.csdn.net/m0_37796683/article/details/90376533
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_alpha).setOnClickListener(this);
        findViewById(R.id.btn_scale).setOnClickListener(this);
        findViewById(R.id.btn_rotate).setOnClickListener(this);
        findViewById(R.id.btn_translate).setOnClickListener(this);
        findViewById(R.id.btn_set).setOnClickListener(this);

        mTextView = findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alpha:
                //初始化透明度动画对象，传入开始和结束的透明度值
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                //设置动画时长2秒
                alphaAnimation.setDuration(2000);
                //设置动画结束后保持动画最后的状态
                alphaAnimation.setFillBefore(true);
                //控件启动动画
                mTextView.startAnimation(alphaAnimation);
                break;
            case R.id.btn_scale:
                //初始化缩放动画对象
                ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 2f, 0f, 2f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                //设置动画时间0.7秒
                scaleAnimation.setDuration(700);
                //设置动画结束后还原动画开始前的状态
                scaleAnimation.setFillBefore(true);
                //控件使用动画
                mTextView.startAnimation(scaleAnimation);
                break;
            case R.id.btn_rotate:
                RotateAnimation rotateAnimation = new RotateAnimation(0f, -650f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(2000);
                rotateAnimation.setFillAfter(true);
                mTextView.startAnimation(rotateAnimation);
                break;
            case R.id.btn_translate:
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 100, 0, 100);
                translateAnimation.setDuration(2000);
                //设置重复次数，INFINITE表示无限重复
                translateAnimation.setRepeatCount(Animation.INFINITE);
                //设置重复类型，每次重复都重新开始动画
                translateAnimation.setRepeatMode(Animation.RESTART);
                mTextView.startAnimation(translateAnimation);
                break;
            case R.id.btn_set:
                ScaleAnimation scale = new ScaleAnimation(0.0f, 1.5f, 0.0f, 1.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scale.setRepeatMode(Animation.REVERSE);
                scale.setRepeatCount(1);

                AlphaAnimation alpha = new AlphaAnimation(0.2f, 0.8f);
                alpha.setRepeatCount(1);
                alpha.setRepeatMode(Animation.REVERSE);

                RotateAnimation rotate = new RotateAnimation(0f, 360f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setRepeatMode(Animation.REVERSE);
                rotate.setRepeatCount(1);

                TranslateAnimation translate = new TranslateAnimation(Animation.ABSOLUTE, 0,
                        Animation.ABSOLUTE, 300, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 300);
                translate.setRepeatCount(1);
                translate.setRepeatMode(Animation.REVERSE);

                //构建动画合集
                AnimationSet animationSet = new AnimationSet(true);
                //设置动画合集重复的次数,在AnimationSet设置是无效的
//                animationSet.setRepeatCount(6);
                //设置动画合集重复类型，优先使用AnimationSet设置的，如果AnimationSet没有设置则使用子动画中的
//                animationSet.setRepeatMode(Animation.RESTART);
                //添加动画
                animationSet.addAnimation(scale);
                animationSet.addAnimation(alpha);
                animationSet.addAnimation(rotate);
                animationSet.addAnimation(translate);

                //设置动画合集时间,如果animationSet中有设置则使用animationSet中设置的时长,否则使用子动画中的时长
                animationSet.setDuration(1000);
                //设置动画合集结束时保持动画最后的状态，只animationSet中设置有效，如果animationSet中没有设置，则使用默认的setFillBefore(true)
                animationSet.setFillAfter(true);

                //设置动画集合监听
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.e(TAG, "Set:onAnimationStart");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.e(TAG, "Set:onAnimationEnd");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        Log.e(TAG, "Set:onAnimationRepeat");
                    }
                });
                //退出动画
//                animationSet.cancel();
//                animationSet.reset();
                mTextView.startAnimation(animationSet);
                break;
        }
    }
}

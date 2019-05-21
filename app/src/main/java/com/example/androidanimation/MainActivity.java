package com.example.androidanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * 我的博客：https://blog.csdn.net/m0_37796683/article/details/90293418
 *          alpha、scale、translate、rotate、set的xml属性及用法
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
                setAnimation(R.anim.anim_alpha);
                break;
            case R.id.btn_scale:
                setAnimation(R.anim.anim_scale);
                break;
            case R.id.btn_rotate:
                setAnimation(R.anim.anim_rotate);
                break;
            case R.id.btn_translate:
                setAnimation(R.anim.anim_tranlate);
                break;
            case R.id.btn_set:
                setAnimation(R.anim.anim_set);
                break;

        }
    }

    private void setAnimation(int resAnim) {
        //1.通过AnimationUtils.loadAnimation(this, R.anim.anim_set)从xml中获取动画
        Animation animation = AnimationUtils.loadAnimation(this, resAnim);
        //2.利用startAnimation（）将动画传递给指定控件显示
        mTextView.startAnimation(animation);
    }
}

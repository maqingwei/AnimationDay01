package com.example.maqingwei.animationday01;

import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

/*
* 属性动画演示
* */

public class PropertyAnimActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mPropertyImg;
    private Button mTranslate, mScale, mRotate, mAlpha, mCombine, mColor;

    private ViewGroup mGroup;
    private LayoutTransition trasion;
    private GridLayout grid;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        initView();
    }

    private void initView() {
        mPropertyImg = (ImageView) findViewById(R.id.propertyimg);
        mAlpha = (Button) findViewById(R.id.palpha);
        mScale = (Button) findViewById(R.id.pscale);
        mRotate = (Button) findViewById(R.id.protate);
        mTranslate = (Button) findViewById(R.id.ptranslate);
        mCombine = (Button) findViewById(R.id.combine);
        mColor = (Button) findViewById(R.id.color);
        mGroup = (ViewGroup) findViewById(R.id.llgropu);
        grid = new GridLayout(this);
        grid.setColumnCount(5);
        mGroup.addView(grid);
        trasion = new LayoutTransition();



        findViewById(R.id.delbtn).setOnClickListener(this);
        findViewById(R.id.addbtn).setOnClickListener(this);
        mColor.setOnClickListener(this);
        mCombine.setOnClickListener(this);
        mAlpha.setOnClickListener(this);
        mScale.setOnClickListener(this);
        mRotate.setOnClickListener(this);
        mTranslate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.palpha:

                ObjectAnimator tAlpha = ObjectAnimator.ofFloat(mPropertyImg, "alpha", 0, 0.7f, 0.3f, 1);
                tAlpha.setDuration(1000);
                tAlpha.start();
                break;

            case R.id.pscale:

                ObjectAnimator tScale = ObjectAnimator.ofFloat(mPropertyImg, "scaleY", 1f, 0.2f, 0.5f, 1);
                tScale.setDuration(1000);
                tScale.start();

                break;
            case R.id.ptranslate:

                ObjectAnimator tTrans = ObjectAnimator.ofFloat(mPropertyImg, "translationX", 0, 300);
                tTrans.setDuration(1000);
                tTrans.setInterpolator(new AccelerateInterpolator());
                tTrans.start();

                break;
            case R.id.protate:

                ObjectAnimator tRotate = ObjectAnimator.ofFloat(mPropertyImg, "rotation", 0, 360);
                tRotate.setDuration(1000);
                tRotate.start();

                break;

            case R.id.combine:

                animationSet(mPropertyImg);
                userValueAnimator();

                break;

            case R.id.color:
                ObjectAnimator argb = ObjectAnimator.ofArgb(mColor, "backgroundColor",
                        getResources().getColor(R.color.colorPrimaryDark),
                        getResources().getColor(R.color.colorAccent));
                argb.setDuration(2000);
                argb.start();

                propertyHolder();
                break;

            case  R.id.addbtn:

              final   Button btn = new Button(this);
                trasion.setAnimator(LayoutTransition.APPEARING, ObjectAnimator.ofFloat(btn,"scaleY",1,0).setDuration(2000));
                btn.setText(++count+"");
                mGroup.setLayoutTransition(trasion);
                grid.addView(btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        grid.removeView(btn);
                    }
                });
                break;



        }
    }
    //将多个ObjectAnimator组合在一起,可调整动画执行顺序或者一起执行
    private void animationSet(View view) {

        AnimatorSet set = new AnimatorSet();

        ObjectAnimator tTransX = ObjectAnimator.ofFloat(view, "translationX", 0, 300);
        ObjectAnimator tTransY = ObjectAnimator.ofFloat(view, "translationY", 0, 300);
        ObjectAnimator tRotate = ObjectAnimator.ofFloat(view, "rotation", 0, 360);
        ObjectAnimator tScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.2f, 0.5f, 1);
        ObjectAnimator tScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.2f, 0.5f, 1);

        //按顺序执行动画
        //set.playSequentially(tTransX,tTransY,tRotate);
        //设置所有动画一起执行
        set.setDuration(1000);
        set.playTogether(tTransX, tTransY, tRotate, tScaleX, tScaleY);
        set.start();

    }

    //ValueAnimator不操作对象 计算动画变换的属性值
    private void userValueAnimator() {

        final ValueAnimator value = ValueAnimator.ofFloat(0f, 1.0f);
        value.setDuration(1000);

        //可以监听动画变化的每一帧
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCombine.setAlpha((Float) value.getAnimatedValue());
            }
        });
        //设置无线循环播放
        // value.setRepeatCount(ValueAnimator.INFINITE);
        //设置模式ValueAnimator.REVERSE表示反向播放,ValueAnimator.RESTART表示从头播放
        value.start();

    }
    //PropertyValueHolder 组合动画
    private void propertyHolder(){
        PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("scaleX",0f,1f);
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleY",0f,1f);
        ObjectAnimator.ofPropertyValuesHolder(mPropertyImg,holder,holder1).setDuration(2000).start();
    }

}

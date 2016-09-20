package com.example.maqingwei.animationday01;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * 传统的动画:
 * 补间动画  帧动画演示
 */

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioButton mAlpha, mScale, mRotate, mTranslate;
    private RadioGroup mGroup;
    private ImageView mShowImage;
    private Button mWalkbtn,mRunBtn,mRotateBtn,mPropertyAnim;
    private ImageView mWalkimg,mRunImg ,mRotateImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        mGroup = (RadioGroup) findViewById(R.id.groupbtn);
        mShowImage = (ImageView) findViewById(R.id.showimageview);
        mAlpha = (RadioButton) findViewById(R.id.alpha);
        mScale = (RadioButton) findViewById(R.id.scale);
        mRotate = (RadioButton) findViewById(R.id.rotate);
        mTranslate = (RadioButton) findViewById(R.id.translate);
        mWalkbtn = (Button) findViewById(R.id.walkbtn);
        mWalkimg = (ImageView) findViewById(R.id.walkimg);
        mRunImg = (ImageView) findViewById(R.id.runimg);
        mRunBtn = (Button) findViewById(R.id.runbtn);
        mRotateBtn = (Button) findViewById(R.id.rotatebtn);
        mRotateImg = (ImageView) findViewById(R.id.rotateImg);
        mPropertyAnim = (Button) findViewById(R.id.propertyani);

        mPropertyAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PropertyAnimActivity.class);
                startActivity(intent);

            }
        });

        mGroup.setOnCheckedChangeListener(this);

    }

    //************************* Tween动画 ***************************//
    //************************* 补间动画 ***************************//
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.alpha:
                Animation alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
                mShowImage.startAnimation(alpha);
                Toast.makeText(MainActivity.this, "透明度变化", Toast.LENGTH_SHORT).show();
                break;
            case R.id.scale:
                Animation scale = AnimationUtils.loadAnimation(this, R.anim.scale);
                mShowImage.startAnimation(scale);
                Toast.makeText(MainActivity.this, "缩放动画", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rotate:
                Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
                mShowImage.startAnimation(rotate);
                Toast.makeText(MainActivity.this, "旋转动画", Toast.LENGTH_SHORT).show();
                break;
            case R.id.translate:
                Animation translate = AnimationUtils.loadAnimation(this, R.anim.translate);
                mShowImage.startAnimation(translate);
                Toast.makeText(MainActivity.this, "平移动画", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //************************* Drawable动画 ***************************//
    //************************* 帧动画 ***************************//
    //start不能放到 onCreat方法中,因为那时候AnimationDrawable还没有附着在Window上
    //Activity生命周期中，onStart, onResume, onCreate都不是真正visible的时间点，
    // 真正的visible时间点是onWindowFocusChanged()函数被执行时。
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Xml方式
        mWalkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWalkimg.setBackgroundResource(R.drawable.walk);
                AnimationDrawable ani = (AnimationDrawable) mWalkimg.getBackground();
                ani.start();
            }
        });

        //代码方式
        mRunBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationDrawable animation = new AnimationDrawable();
                animation.addFrame(getDrawable(R.drawable.f20),50);
                animation.addFrame(getDrawable(R.drawable.f21),50);
                animation.addFrame(getDrawable(R.drawable.f22),50);
                animation.addFrame(getDrawable(R.drawable.f23),50);
                animation.addFrame(getDrawable(R.drawable.f24),50);
                animation.addFrame(getDrawable(R.drawable.f25),50);
                animation.addFrame(getDrawable(R.drawable.f26),50);
                animation.addFrame(getDrawable(R.drawable.f27),50);
                mRunImg.setBackgroundDrawable(animation);
                animation.setOneShot(false);
                animation.start();


            }
        });

    }

}

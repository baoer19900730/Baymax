package com.example.zhou.watch.Rapid;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by jxr20 on 2017/7/5
 */

public class MyArcView extends android.support.v7.widget.AppCompatTextView {

    private RectF mRectF = new RectF(330, 330, 750, 750);
    private RectF mRectF2 = new RectF(350, 350, 730, 730);

    private Paint mPaintGray = new Paint();
    private Paint mPaintOrange = new Paint();

    private Paint mPaintGray2 = new Paint();
    private Paint mPaintWhite = new Paint();

    private float degree = 0 ;

    public MyArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaintGray.setAntiAlias(true);
        mPaintGray.setStrokeWidth(30);
        mPaintGray.setColor(0xffd0d0d0);
        mPaintGray.setStyle(Paint.Style.STROKE);

       //圆角弧，起点和终点都是半圆

        mPaintGray2.setAntiAlias(true);
        mPaintGray2.setStrokeWidth(20);  //设置空心线宽度
        mPaintGray2.setColor(0xffEEEEEE);
        mPaintGray2.setStyle(Paint.Style.STROKE);  //设置空心圆

        mPaintWhite.setAntiAlias(true);
        mPaintWhite.setStrokeWidth(30);
        mPaintWhite.setColor(0xffFFFFFF);
        //mPaintWhite.setStyle(Paint.Style.STROKE); //画实心的弧，和画圆一样的效果

        mPaintOrange.setAntiAlias(true);
        mPaintOrange.setColor(0xfffe7f41);
        mPaintOrange.setStrokeWidth(30);
        mPaintOrange.setStyle(Paint.Style.STROKE);
    }

    public void  setDegree(float degree){
        this.degree = degree;
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawArc(mRectF2, 0, 360, false, mPaintWhite);    //画中间的白心，虽然是画弧，但由于是实心的，所有和画圆一样的
        canvas.drawArc(mRectF2, 0, 360, false, mPaintGray2);    //画中间的灰弧，0度开始，画360度，就是画圆

        canvas.drawArc(mRectF, 0, 360, false, mPaintGray);      //画外面的灰弧，0度开始，画360度，效果就是一个圆
        canvas.drawArc(mRectF, -90, degree, false, mPaintOrange);  //画外面的橙色弧，从-90度，度数270度，刚好3/4个圆


        super.onDraw(canvas);
    }
}

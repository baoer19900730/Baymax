package com.example.zhou.watch.Uitil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by zhou on 2017/7/12.
 */

public class MyRectangle extends TextView{

    private RectF rectF1 = new RectF(330, 330, 750, 750);
    private Paint gray = new Paint();
    private Paint orange = new Paint();
    private float degree = 0;


    public  MyRectangle(Context context, AttributeSet attr){
        super(context, attr);
        init();
    }

    private void setDegree(float degree){
        this.degree = degree;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(rectF1, 0, 360, false, gray);
        canvas.drawArc(rectF1, 0, degree, false, orange);
        super.onDraw(canvas);
    }

    private void init(){
        gray.setAntiAlias(true);
        gray.setColor(0xffd0d0d0);
        gray.setStrokeWidth(20);
        gray.setStyle(Paint.Style.STROKE);

        orange.setStrokeWidth(20);
        orange.setColor(0xffffbf42);
        orange.setStyle(Paint.Style.STROKE);
        orange.setAntiAlias(true);
        orange.setStrokeCap(Paint.Cap.ROUND);

    }
}

package com.example.zhou.watch.Uitil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhou on 2017/7/5.
 */

public class My2TextView extends View{

    private RectF rectF = new RectF(330, 330, 750, 750);
    private Paint mPaintGray = new Paint();

    My2TextView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init();
    }

    private void init(){
        mPaintGray.setColor(0xffd0d0d0);
        mPaintGray.setStrokeWidth(30);
        mPaintGray.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(rectF, 0, 360, false, mPaintGray);
    }
}


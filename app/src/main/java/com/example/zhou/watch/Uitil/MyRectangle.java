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
    private Paint pi = new Paint();


    public  MyRectangle(Context context, AttributeSet attr){
        super(context, attr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(rectF1, 0, 360, false, pi);
        super.onDraw(canvas);
    }

    private void init(){
        pi.setAntiAlias(true);
        pi.setColor(0xffd0d0d0);
        pi.setStrokeWidth(20);
        pi.setStyle(Paint.Style.STROKE);
    }
}

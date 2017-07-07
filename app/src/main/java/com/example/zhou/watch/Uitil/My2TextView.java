package com.example.zhou.watch.Uitil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by zhou on 2017/7/5.
 */

public class My2TextView extends android.support.v7.widget.AppCompatTextView{

    public My2TextView(Context context, AttributeSet attrs){
        super(context, attrs);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint pa1 = new Paint();
        Paint pa2 = new Paint();
        Paint pa3 = new Paint();
        pa3.setStyle(Paint.Style.STROKE);

        float wi = getWidth();
        float hi = getHeight();
        pa1.setColor(0x9affffff);
        pa2.setColor(0xfffe7f41);
        pa3.setColor(Color.BLACK);

        float x = (wi - hi / 2) / 2;
        float y = hi / 4;
        RectF oval = new RectF(x, y, wi - x, hi - y);

        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawArc(oval, 0 , 270, false, pa3);
        canvas.drawCircle(wi/2, hi/2, 350, pa1);
        canvas.drawCircle(wi/2, hi/2, 350, pa2);
        canvas.drawCircle(wi/2, hi/2, 280, pa3);

        super.onDraw(canvas);
    }
}

package com.example.zhou.watch.Uitil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhou on 2017/7/4.
 */

public class MyView extends View {

    public MyView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
    }

    public MyView(Context context){
        super(context);
    }

    public MyView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint pa1 = new Paint();
        Paint pa2 = new Paint();
        Paint pa3 = new Paint();
        Paint pa4 = new Paint();
        float wi = canvas.getWidth();
        float hi = canvas.getHeight();
        pa1.setColor(0x9affffff);
        pa2.setColor(0xfffe7f41);
        pa3.setColor(0x9affffff);
        pa4.setColor(0xccffffff);

        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawCircle(wi/2, hi/2, 200, pa1);
        canvas.drawCircle(wi/2, hi/2, 196, pa2);
        canvas.drawCircle(wi/2, hi/2, 186, pa3);
        canvas.drawCircle(wi/2, hi/2, 170, pa4);



        super.onDraw(canvas);
    }
}

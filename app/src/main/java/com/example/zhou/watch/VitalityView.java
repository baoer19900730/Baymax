package com.example.zhou.watch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
//
//import com.facebook.react.bridge.Arguments;
//import com.facebook.react.bridge.ReactContext;
//import com.facebook.react.bridge.WritableMap;
//import com.facebook.react.uimanager.events.RCTEventEmitter;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jxr20 on 2017/6/17
 */

public class VitalityView extends View implements AudioRecoderUtils.Callback {

    public static final String TAG = "Vitality";

    private boolean isAlive = false;
    private Paint mPaint;
    private Paint mPaint1;

    private long mSpeedX = 0;
    private ArrayList<PointF> mPointArray;


    public VitalityView(Context context) {
        super(context);
        init();
    }

    public VitalityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint1 = new Paint();
        mPaint1.setColor(0xff00DB00);
        mPaint1.setAntiAlias(true);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(3);

        mPaint = new Paint();
        mPaint.setColor(0xffFF5d42);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPointArray = new ArrayList<>();
    }

    @Override
    protected void onAttachedToWindow() {
        Log.i(TAG, "VitalityView.. onAttachedToWindow..");
        super.onAttachedToWindow();
//        EventBus.getDefault().register(this);
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        Log.i(TAG, "VitalityView.. onDetachedFromWindow..");
        super.onDetachedFromWindow();
//        EventBus.getDefault().unregister(this);
        stop();
    }

//    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
//    public void onEventPostThread(VitalityMessage message) {
//        Log.i(TAG, "onEventMainThread.. message: " + message + ", Thread: " + Thread.currentThread());
//        boolean start = message.isStart();
//        if (start) {
//            start();
//        } else {
//            stop();
//        }
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mPointArray.size() > getWidth() / 5) {
            mPointArray.remove(0);
        }

        canvas.save();//锁画布(为了保存之前的画布状态)
        canvas.translate(mSpeedX, 0);//把当前画布的原点移到(x,y),后面的操作都以(x,y)作为参照点，默认原点为(0,0)
        for (int i = mPointArray.size() - 1; i >= 0; i --) {
            PointF p = mPointArray.get(i); //PointF是定义坐标为float型数
            canvas.drawLine(p.x, p.y, p.x, getHeight() / 2, mPaint1);//许多竖线
        }
        canvas.restore();//把当前画布返回（调整）到上一个save()状态之前
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPaint);//一条直线

        mSpeedX += 8;
    }

    public void start() {
        Log.i(TAG, "Vitality. start.. isAlive: " + isAlive);
        if (!isAlive) {
            isAlive = true;
            AudioRecoderUtils.prepare(this);
            AudioRecoderUtils.start();
            AudioRecoderUtils.updateCallback();
        }
    }

    public void stop() {
        Log.i(TAG, "Vitality. stop.. isAlive: " + isAlive);
        if (isAlive) {
            isAlive = false;
            AudioRecoderUtils.stop();
            AudioRecoderUtils.saveWavFile();
        }
    }

    public void setVolume(int volume) {
        if (volume < 50) {
            volume = 0;
        }
        mPointArray.add(new PointF(-mSpeedX, getHeight() / 2 - volume * 2));
        postInvalidate();
    }

    @Override
    public void onUpdate(double data) {
        Log.i(TAG, "onUpdate.. data: " + data);
        setVolume((int) data);

        callback(data);
    }

    /**
     * 回调到RN的JS端
     * @param db 数据
//     */
    private void callback(double db) {
        double data = 1.0 * db / 100;
//        WritableMap event = Arguments.createMap();
//        event.putDouble("data", data);
//        ReactContext reactContext = (ReactContext) getContext();
//        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(getId(), "topChange", event);
    }
}

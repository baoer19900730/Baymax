package com.example.zhou.watch;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by zhou on 2017/7/5
 */

public class RapidExamineFragment extends Fragment implements SurfaceHolder.Callback{

    private static final String TAG = "RapidExamineFragment";
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private   Camera camera;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rapid_examine, container, false);
        TextView startText = (TextView) view.findViewById(R.id.begin_text);
        surfaceView = (SurfaceView) view.findViewById(R.id.main_surface_view);  //拿到surfaceView对象

        surfaceHolder = surfaceView.getHolder();    //SurfaceView在创建的时候，就会自动生成一个Holder，这个对象后期我们有用，所以保存到全局变量
        surfaceHolder.addCallback(this);            //SurfaceView需要一个回调，用于告诉你，什么时候开始创建，什么时候创建完成，什么时候销毁，也就是下面的那三个方法
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// 设置显示器类型，setType必须设置   Holder必须要设置成这个类型，才能支持显示摄像头数据
        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);   //这一句不知道什么意思

        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPreview();
            }
        });
        return view;
    }


    /**
     * 调用摄像头
     */
    public void startPreview() {
        Log.i(TAG, "startPreview.. ");
        if (camera == null) {
            try {
                camera = Camera.open(); //获取Camera对象，思考一下为什么是open而不是new一个Camera，它这里使用了什么样的设计模式
                camera.setPreviewDisplay(surfaceHolder);    //Camera需要一个Holder对象才能显示，不然它显示在哪.Holder会把摄像头数据拿过来，显示在SurfaceView上面
                Camera.Parameters params = camera.getParameters();  //Camera对象需要一些参数，我们把它的参数对象拿过来
                params.setPreviewSize(352, 288);    //将它的参数对象设置一些值，比如说352*288的大小，比如说1920*1080的大不等
                camera.setParameters(params);   //把参数设置好了后，传回给Camera
                camera.startPreview();  //开始预览，意思就是开始显示在SurfaceView上面了
                camera.setDisplayOrientation(90);  //旋转90度，如果不调用这个，会发现报像头是横着的，你可以试下注释掉这一行
                //camera.setPreviewCallback(this);  //可以给摄像头设置一个回调对象，报像头每采集一些数据，都会回调回来，1秒钟可能采集几十次数据，会回调几十次，这个数据用来计算心率血压等值的
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * SurfaceView开始创建了
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i(TAG, "surfaceCreated..");
        /*try {
          camera = Camera.open();
            camera.setPreviewDisplay(holder);
        }catch (Exception e){
            if (null != camera){
                camera.release();
                camera = null;
            }
            e.printStackTrace();
        }*/
    }

    /**
     * SurfaceView大小改变了，也就是创建完了
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            //camera.startPreview();
        Log.i(TAG, "surfaceChanged..");
        //startPreview();
    }

    /**
     * SurfaceView要销毁了，你要释放资源，不然camera会一直在内存里面，导致你的内存越用越小
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i(TAG, "surfaceDestroyed..");
         if (null != camera){
             camera.stopPreview();
             camera.release();
             camera = null;
         }
    }
}



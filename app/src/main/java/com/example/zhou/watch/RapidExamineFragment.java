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
        surfaceView = (SurfaceView) view.findViewById(R.id.main_surface_view);

        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// 设置显示器类型，setType必须设置
        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);

        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPreview();
            }
        });
        return view;
    }


    /**
     * 调用
     */
    public void startPreview() {
        Log.i(TAG, "startPreview.. ");
        if (camera == null) {
            try {
                camera = Camera.open();
                camera.setPreviewDisplay(surfaceHolder);
                Camera.Parameters params = camera.getParameters();
                params.setPreviewSize(352, 288);
                camera.setParameters(params);
                camera.startPreview();
                camera.setDisplayOrientation(90);  //旋转90度
                //camera.setPreviewCallback(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            //camera.startPreview();
        Log.i(TAG, "surfaceChanged..");
        //startPreview();
    }

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



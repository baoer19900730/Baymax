package com.example.zhou.watch;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zhou on 2017/7/5
 */

public class RapidExamineFragment extends Fragment implements SurfaceHolder.Callback{
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
        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surfaceChanged();
            }
        });
        return view;
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
          camera = Camera.open();
            camera.setPreviewDisplay(holder);
        }catch (Exception e){
            if (null != camera){
                camera.release();
                camera = null;
            }
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
         if (null != camera){
             camera.stopPreview();
             camera.release();
             camera = null;
         }
    }
}



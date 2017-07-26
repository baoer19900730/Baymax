package com.example.zhou.watch.Rapid;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;

import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.zhou.watch.R;
import com.oudmon.algo.ppg.PpgAnalyzer;

import java.io.IOException;

/**
 * Created by zhou on 2017/7/5
 */

public class RapidExamineFragment extends Fragment implements SurfaceHolder.Callback, Camera.PreviewCallback{

    private static final String TAG = "RapidExamineFragment";
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private   Camera camera;
    private int index = 0;
    private int length = 16;
    private PpgAnalyzer ppgAnalyzer = new PpgAnalyzer();
    private float[] imageHue = new float[length];
    private float[] imageRed = new float[length];
    private float[] imageBlue = new float[length];
    public TextView xueya;
    public TextView xinlv;
    public TextView xueyang;
    public TextView nongdu;
    boolean b = true;
    private MyArcView startText;
    private Button back;
    private LinearLayout checkResult;
    private Button checkAgain;
    private Button checkSave;
    private boolean isclick=false;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rapid_examine, container, false);
        xueya = (TextView) view.findViewById(R.id.xueya);
        xinlv = (TextView) view.findViewById(R.id.xinlv);
        xueyang = (TextView) view.findViewById(R.id.xueyang);
        nongdu = (TextView) view.findViewById(R.id.breath);
        startText = (MyArcView) view.findViewById(R.id.begin_text);
        checkResult = (LinearLayout) view.findViewById(R.id.check_result);
        checkAgain = (Button)view.findViewById(R.id.check_again);
        checkSave = (Button)view.findViewById(R.id.check_save);

        surfaceView = (SurfaceView) view.findViewById(R.id.main_surface_view);  //拿到surfaceView对象
        surfaceView.setVisibility(View.INVISIBLE);
        surfaceHolder = surfaceView.getHolder();    //SurfaceView在创建的时候，就会自动生成一个Holder，这个对象后期我们有用，所以保存到全局变量
        surfaceHolder.addCallback(this);            //SurfaceView需要一个回调，用于告诉你，什么时候开始创建，什么时候创建完成，什么时候销毁，也就是下面的那三个方法
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);// 设置显示器类型，setType必须设置   Holder必须要设置成这个类型，才能支持显示摄像头数据
        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);   //这一句不知道什么意思
        back = (Button) view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RapidExamineActivity) getActivity()).replaceFragment(0);
            }
        });
        startText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isclick==false) {
                    isclick = true;
                    surfaceView.setVisibility(View.VISIBLE);
                    startText.setText("正在测量");
                    startPreview();
                    animator();
                }
            }
        });
        return view;
    }

    private void animator(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(startText, "degree", 0, 360);
        animator.setDuration(60000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(final Animator animation) {//动画结束
                super.onAnimationEnd(animation);
                isclick = false;
                b = false;
                startText.setVisibility(View.INVISIBLE);
                surfaceView.setVisibility(View.INVISIBLE);
                checkResult.setVisibility(View.VISIBLE);
                checkAgain.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isclick==false){
                            isclick =true;
                            checkResult.setVisibility(View.INVISIBLE);
                            startText.setVisibility(View.VISIBLE);
                            surfaceView.setVisibility(View.VISIBLE);
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(startText, "degree", 0, 0);
                            animator1 .start();
                            xueya.setText("- -");
                            xinlv.setText("- -");
                            xueyang.setText("- -");
                            nongdu.setText("- -");
                            animator();
                            startPreview();
                        }

                    }
                });
                checkSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((RapidExamineActivity)getActivity()).saveResult();
                    }
                });
            }

            @Override
            public void onAnimationStart(Animator animation) { //动画开始
                super.onAnimationStart(animation);
                b = true;


            }
        });
        animator.start();

    }


    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        if (b){
            ppgAnalyzer = ppgAnalyzer.ppgImageAlgo(data, 352, 288); //图像处理
            imageHue[index] = ppgAnalyzer.getHue();  //从图像中得到灰色数组元素
            imageBlue[index] = ppgAnalyzer.getBlue();//从图像中得到蓝色数组元素
            imageRed[index] = ppgAnalyzer.getRed();//从图像中得到红色数组元素
            index++;  //角标增加
            if (index >= length) { //如果角标大于数组的长度就开始计算，并且角标把角标设为0
                index = 0;
                int xinLv = ppgAnalyzer.ppgInstantHrAlgo(imageHue, 16); //心率算法
                xinlv.setText(xinLv + "");
                float huXi = ppgAnalyzer.ppgRespirAlgo(imageHue, 16);  //呼吸算法
                nongdu.setText(huXi + "");
                PpgAnalyzer xueYa = ppgAnalyzer.ppgBpAlgo(xinLv); //血压计算
                int sbp = xueYa.getSbp();
                int dbp = xueYa.getDbp();
                xueya.setText(sbp + "/" + dbp);
                float xueYang = ppgAnalyzer.ppgSao2Algo(imageRed, imageBlue, 16); //血氧计算
                xueyang.setText(xueYang + "");

            }
        }
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
                camera.setPreviewCallback(this);  //可以给摄像头设置一个回调对象，报像头每采集一些数据，都会回调回来，1秒钟可能采集几十次数据，会回调几十次，这个数据用来计算心率血压等值的
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
             camera.setPreviewCallback(null);
             camera.stopPreview();
             camera.release();
             camera = null;
         }
    }


}



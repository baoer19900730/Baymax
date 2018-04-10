package com.example.zhou.watch.Lung;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhou.watch.AudioRecoderUtils;
import com.example.zhou.watch.R;
import com.example.zhou.watch.VitalityView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhou on 2017/7/18.
 */

public class LungChecked1 extends Fragment implements View.OnClickListener, AudioRecoderUtils.Callback {

    private TextView lungStart;
    private ImageView lungWind;
    private VitalityView lungView;
    private int time = 100;
    private Timer timer;
    private   ObjectAnimator animator;
    private boolean isClick;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1 ){
                    animator.start();
                if (time <=0){
                    AudioRecoderUtils.stop();
                    AudioRecoderUtils.saveWavFile();
                    ((LungChecked)getActivity()).replaceLung(new LungChecked2());
                    timer.cancel();
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lung_checked1, container, false);
        lungStart = (TextView) view.findViewById(R.id.lung_start);
        lungWind = (ImageView) view.findViewById(R.id.lung_wind);
        lungView = (VitalityView) view.findViewById(R.id.lung_view);
        lungStart.setOnClickListener(this);
        AudioRecoderUtils.prepare(this);
        animator = ObjectAnimator.ofFloat(lungWind, "rotation", 0, 360);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.lung_start){
            lungView.setVisibility(View.VISIBLE);
             lungStart.setVisibility(View.INVISIBLE);
                windRun();
                AudioRecoderUtils.start();

        }
    }

    private void windRun(){
       timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
                time --;
                Log.i("倒计时", "Timer Run.. time: " + time);
            }
        }, 0, 100);
    }

    @Override
    public void onUpdate(double data) {
        AudioRecoderUtils.updateCallback();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer!= null){
            timer.cancel();
        }
    }
}

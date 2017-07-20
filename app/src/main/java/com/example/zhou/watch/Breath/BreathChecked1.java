package com.example.zhou.watch.Breath;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zhou.watch.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhou on 2017/7/18.
 */

public class BreathChecked1 extends Fragment implements View.OnClickListener,AudioRecoderUtils.Callback{

    private static final String TAG = "Breath";

    private Button breathStart;
    private MyRectangle breathRound;
    private int time = 5;
    private Timer timer;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                    breathRound.setText(time+"s");

                Log.i(TAG, "Handler Run.. time: " + time + ", Activity: " + getActivity());

                if (time <= 0){
                    AudioRecoderUtils.saveWavFile();
                    AudioRecoderUtils.stop();
                    ((BreathChecked)getActivity()).replaceBreathFragment(new BreathChecked2());
                    timer.cancel();
                }
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.breath_checked1, container, false);
        breathStart = (Button)view.findViewById(R.id.breath_start);
        breathRound = (MyRectangle) view.findViewById(R.id.breath_round);

        breathStart.setOnClickListener(this);
        AudioRecoderUtils.prepare(this);
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.breath_start:
                breathStart.setVisibility(View.INVISIBLE);
                animor();
                countdown();
                AudioRecoderUtils.start();

                break;
        }
    }
    private void animor(){
        ObjectAnimator an = ObjectAnimator.ofFloat(breathRound, "degree", 0, 360);
        an.setDuration(5000);
        an.start();
    }

    private void countdown(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                    /*Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);*/   //TODO -> 就只传一个what进去，为什么不用sendEmptyMessage方法
                handler.sendEmptyMessage(1);    //TODO -> 为什么不用这个方法，而创建一个新的Message对象导致资源消耗？
                    time--;
                Log.i(TAG, "Timer Run.. time: " + time);

            }
        }, 0, 1000);

    }

    @Override
    public void onUpdate(double data) {
        AudioRecoderUtils.updateCallback();
    }
}

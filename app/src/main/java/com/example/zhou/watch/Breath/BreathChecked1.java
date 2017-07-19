package com.example.zhou.watch.Breath;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class BreathChecked1 extends Fragment implements View.OnClickListener{
    private Button breathStart;
    private MyRectangle breathRound;
    private int time = 60;
    private Timer timer;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                    breathRound.setText(time+"s");
                if (time <= 0){
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
        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.breath_start:
                breathStart.setVisibility(View.INVISIBLE);
                animor();
                countdown();
                break;
        }
    }
    private void animor(){
        ObjectAnimator an = ObjectAnimator.ofFloat(breathRound, "degree", 0, 360);
        an.setDuration(60000);
        an.start();
    }

    private void countdown(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    time--;

            }
        }, 0, 1000);

    }
}

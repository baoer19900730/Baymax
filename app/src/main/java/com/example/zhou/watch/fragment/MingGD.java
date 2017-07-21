package com.example.zhou.watch.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.zhou.watch.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhou on 2017/7/14.
 */

public class MingGD extends Fragment implements View.OnClickListener {

    private int r;
    private int g;
    private int b;
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private int mColor;
    private int difColor;
    private int step = 2 ;
    private int mIndex;
    private int index;
    private Random random = new Random();
    private TableLayout mTable;
    private int total=0;
    public int time = 30;
    private Button start;
    private Button button;
    private Timer mTimer;
    private boolean startB; //判断是否点击开始测量

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (msg.what ==1) {
                    start.setText(time + "s");
                    if (time <= 0){
                        mTimer.cancel();
                        ((VisionChecked)getActivity()).replaceFragment(new MingGD1());
                    }
                }
            }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.minggd_checked, container, false);
        mTable = (TableLayout) view.findViewById(R.id.table);
        start = (Button) view.findViewById(R.id.start_checked);
        mTimer = new Timer();
        initButton();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startB ==false){
                    countdown();
                }
                startB = true;
            }
        });

        return view;
    }

    private void countdown(){

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what =1;
                handler.sendMessage(message);
                time --;
            }
        }, 0, 1000);
    }

    private void initColor(){
        r = (int) Math.round(Math.random()*234) + 20;
        g = (int) Math.round(Math.random()*234) + 20;
        b = (int) Math.round(Math.random()*234) + 20;
        mColor = Color.rgb(r, g, b);
        difColor = Color.rgb((r - 15), (g - 15), (b - 15));
        mIndex = random.nextInt(step * step);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTimer != null){
            mTimer.cancel();
        }
    }

    @Override
    public void onClick(View v) {
        if (startB == true) {
            if (v.getId() == mIndex) {
                total += 1;
                ((VisionChecked) getActivity()).setTotal(total);
                next();
            } else {
                error();
            }
        }
    }

    private void next(){
        step ++;
        if (step> 8){
            step = 8;
        }
        initButton();
    }

    private void error(){
        ((VisionChecked)getActivity()).replaceFragment(new MingGD1());
    }

    private void initButton(){
        mTable.removeAllViews();
        initColor();
        for (int row = 0; row < step; row ++){
            TableRow tableRow = new TableRow(getActivity());
            for (int col = 0; col < step; col ++){
                TableRow.LayoutParams params = new TableRow.LayoutParams(MP, MP, 1.0F);
                params.bottomMargin = 5;
                params.topMargin = 5;
                params.leftMargin = 5;
                params.rightMargin = 5;
                index = row * step + col;
                button = new Button(getActivity());
                button.setOnClickListener(this);
                button.setId(index);
                if (index == mIndex){
                    button.setBackgroundColor(difColor);
                }else {
                    button.setBackgroundColor(mColor);
                }
                tableRow.addView(button, params);
            }
            TableLayout.LayoutParams params1 = new TableLayout.LayoutParams(WC, WC, 1.0F);
            mTable.addView(tableRow, params1);
        }
    }

}

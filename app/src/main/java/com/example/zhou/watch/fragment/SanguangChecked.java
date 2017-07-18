package com.example.zhou.watch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zhou.watch.R;

/**
 * Created by zhou on 2017/7/7.
 */

public class SanguangChecked extends Fragment implements View.OnClickListener{

    private Button threeButton;
    private Button sixButton;
    private Button sameButton;
    private Button wrongButton;
    private Button submitButton;
    private boolean mBoolean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sanguang_checked, container, false);
        threeButton = (Button) view.findViewById(R.id.three_button);
        sixButton = (Button) view.findViewById(R.id.six_button);
        sameButton = (Button) view.findViewById(R.id.same_button);
        wrongButton = (Button) view.findViewById(R.id.wrong_button);
        submitButton = (Button) view.findViewById(R.id.submit_button);
        threeButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sameButton.setOnClickListener(this);
        wrongButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_button:
                if(mBoolean == true){
                    ((VisionChecked)getActivity()).replaceFragment(new SanGuangCheck1());
                }else {
                    ((VisionChecked)getActivity()).replaceFragment(new sanGuangCheck2());
                }

            case R.id.three_button:
                mBoolean = false;
                threeButton.setTextColor(0xffFF5d42);
                sixButton.setTextColor(0xff333333);
                sameButton.setTextColor(0xff333333);
                wrongButton.setTextColor(0xff333333);
                threeButton.getPaint().setFakeBoldText(true);
                threeButton.setBackgroundResource(R.drawable.red);
                sixButton.getPaint().setFakeBoldText(false);
                sixButton.setBackgroundResource(R.drawable.touming);
                sameButton.getPaint().setFakeBoldText(false);
                sameButton.setBackgroundResource(R.drawable.touming);
                wrongButton.getPaint().setFakeBoldText(false);
                wrongButton.setBackgroundResource(R.drawable.touming);
                break;
            case R.id.six_button:
                mBoolean = false;
                sixButton.setTextColor(0xffFF5d42);
                threeButton.setTextColor(0xff333333);
                sameButton.setTextColor(0xff333333);
                wrongButton.setTextColor(0xff333333);
                sixButton.getPaint().setFakeBoldText(true);
                sixButton.setBackgroundResource(R.drawable.red);
                threeButton.getPaint().setFakeBoldText(false);
                threeButton.setBackgroundResource(R.drawable.touming);
                sameButton.getPaint().setFakeBoldText(false);
                sameButton.setBackgroundResource(R.drawable.touming);
                wrongButton.getPaint().setFakeBoldText(false);
                wrongButton.setBackgroundResource(R.drawable.touming);
                break;
            case R.id.same_button:
                mBoolean = true;
                sameButton.setTextColor(0xffFF5d42);
                sixButton.setTextColor(0xff333333);
                threeButton.setTextColor(0xff333333);
                wrongButton.setTextColor(0xff333333);
                sameButton.getPaint().setFakeBoldText(true);
                sameButton.setBackgroundResource(R.drawable.red);
                sixButton.getPaint().setFakeBoldText(false);
                sixButton.setBackgroundResource(R.drawable.touming);
                threeButton.getPaint().setFakeBoldText(false);
                threeButton.setBackgroundResource(R.drawable.touming);
                wrongButton.getPaint().setFakeBoldText(false);
                wrongButton.setBackgroundResource(R.drawable.touming);
                break;
            case R.id.wrong_button:
                mBoolean = false;
                wrongButton.setTextColor(0xffFF5d42);
                sixButton.setTextColor(0xff333333);
                sameButton.setTextColor(0xff333333);
                threeButton.setTextColor(0xff333333);
                wrongButton.getPaint().setFakeBoldText(true);
                wrongButton.setBackgroundResource(R.drawable.red);
                sixButton.getPaint().setFakeBoldText(false);
                sixButton.setBackgroundResource(R.drawable.touming);
                sameButton.getPaint().setFakeBoldText(false);
                sameButton.setBackgroundResource(R.drawable.touming);
                threeButton.getPaint().setFakeBoldText(false);
                threeButton.setBackgroundResource(R.drawable.touming);
                break;
            default:
                break;
        }

    }
}

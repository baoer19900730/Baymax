package com.example.zhou.watch;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by zhou on 2017/7/5
 */

public class RapidExamineTipFragment extends Fragment implements View.OnClickListener{

    private Button tipBack;
    private Button startChecked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rapid_examine_tip, container, false);
        tipBack = (Button) view.findViewById(R.id.tip_back);
        startChecked = (Button) view.findViewById(R.id.start_checked);
        tipBack.setOnClickListener(this);
        startChecked.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_checked:
                ((RapidExamineActivity) getActivity()).replaceFragment(1);  //点击按钮的时候切换到第2个界面
                break;
            case R.id.tip_back:
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}

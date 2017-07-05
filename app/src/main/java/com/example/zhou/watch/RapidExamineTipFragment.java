package com.example.zhou.watch;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zhou on 2017/7/5
 */

public class RapidExamineTipFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rapid_examine_tip, container, false);
        view.findViewById(R.id.start_checked).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RapidExamineActivity) getActivity()).replaceFragment(1);  //点击按钮的时候切换到第2个界面
            }
        });
        return view;
    }
}

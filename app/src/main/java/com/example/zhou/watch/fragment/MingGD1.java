package com.example.zhou.watch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.zhou.watch.R;

/**
 * Created by zhou on 2017/7/14.
 */

public class MingGD1 extends Fragment {

    private Button minggdAgain;
    private TextView minggdTotal;  //总共过关
    private int pass;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.minggd_checked1, container, false);
        minggdAgain = (Button) view.findViewById(R.id.minggd1_again);
        minggdTotal = (TextView) view.findViewById(R.id.minggd_total);
        minggdAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((VisionChecked)getActivity()).setTotal(0);
                ((VisionChecked)getActivity()).replaceFragment(new MingGD());
            }
        });
        pass = ((VisionChecked)getActivity()).getTotal();
        Log.d("MingCD1", pass +"");
        minggdTotal.setText(pass + "关");
        return view;
    }
}

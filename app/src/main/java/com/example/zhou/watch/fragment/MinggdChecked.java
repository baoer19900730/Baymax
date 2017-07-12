package com.example.zhou.watch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhou.watch.R;

/**
 * Created by zhou on 2017/7/7.
 */

public class MinggdChecked extends Fragment {
    private String TAG = "MinggdChecked";

    private long r;
    private long g;
    private long b;
    private long color;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.minggd_checked, container, false);
        r = Math.round(Math.random()* 234 ) + 20;
        g = Math.round(Math.random()* 234 ) + 20;
        b = Math.round(Math.random()* 234 ) + 20;
        color = r + g + b;

//        Log.d(TAG, "r " + r);
        return view;
    }
}

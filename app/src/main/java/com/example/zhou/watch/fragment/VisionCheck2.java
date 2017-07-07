package com.example.zhou.watch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhou.watch.R;

import java.util.Random;


/**
 * Created by zhou on 2017/7/7.
 */

public class VisionCheck2 extends Fragment {

    private String[] e = {"ㅗ",  " ㅜ",  "ㅏ",  "┨"};
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vision_checked2, container, false);
        textView = (TextView) view.findViewById(R.id.e_text);
        Random random = new Random();
        int i = random.nextInt(4);
        textView.setText(e[i]);
        return view;
    }


}

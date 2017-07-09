package com.example.zhou.watch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhou.watch.R;

import java.util.Random;


/**
 * Created by zhou on 2017/7/7.
 */

public class VisionCheck2 extends Fragment implements View.OnClickListener{

    private String[] e = {"ㅗ",  "ㅜ",  "ㅏ",  "┨"};
    private int i;
    private Random random;
    private TextView textView;
    private ImageView up;
    private ImageView down;
    private ImageView left;
    private ImageView right;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vision_checked2, container, false);
        textView = (TextView) view.findViewById(R.id.e_text);
        up = (ImageView) view.findViewById(R.id.e_up);
        down = (ImageView) view.findViewById(R.id.e_down);
        left = (ImageView) view.findViewById(R.id.e_left);
        right = (ImageView) view.findViewById(R.id.e_right);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        random = new Random();
        i = random.nextInt(4);
        textView.setText(e[i]);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.e_up:
                if (i == 0){
                    Toast.makeText(getContext(),"正确", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"错误", Toast.LENGTH_SHORT).show();
                }
                i = random.nextInt(4);
                textView.setText(e[i]);
                break;
            case R.id.e_down:
                if (i == 1){
                    Toast.makeText(getContext(),"正确", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"错误", Toast.LENGTH_SHORT).show();
                }
                i = random.nextInt(4);
                textView.setText(e[i]);
                break;
            case R.id.e_right:
                if (i == 2){
                    Toast.makeText(getContext(),"正确", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"错误", Toast.LENGTH_SHORT).show();
                }
                i = random.nextInt(4);
                textView.setText(e[i]);
                break;
            case R.id.e_left:
                if (i == 3){
                    Toast.makeText(getContext(),"正确", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(),"错误", Toast.LENGTH_SHORT).show();
                }
                i = random.nextInt(4);
                textView.setText(e[i]);
                break;
        }
    }
}

package com.example.zhou.watch.Heart;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.zhou.watch.R;

/**
 * Created by zhou on 2017/7/13.
 */

public class HeartChecked1 extends Fragment implements View.OnClickListener{

    private Button heartStart;
    private RadioButton zeroScore;
    private RadioButton twoScore;
    private RadioButton fourScore;
    private RadioButton sixScore;
    public int score = 0;
    private int total = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.heart_checked1, container, false);
        heartStart = (Button) view.findViewById(R.id.heart_start);
        zeroScore = (RadioButton) view.findViewById(R.id.zero_score);
        twoScore = (RadioButton) view.findViewById(R.id.two_score);
        fourScore = (RadioButton) view.findViewById(R.id.four_score);
        sixScore = (RadioButton) view.findViewById(R.id.six_score);
        zeroScore.setOnClickListener(this);
        twoScore.setOnClickListener(this);
        fourScore.setOnClickListener(this);
        sixScore.setOnClickListener(this);
        heartStart.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zero_score:
                ++total;
                break;
            case R.id.two_score:
                ++total;
                score += 2;
                break;
            case R.id.four_score:
                ++total;
                score += 4;
                break;
            case R.id.six_score:
                ++total;
                score += 6;
                break;
            case R.id.heart_start:
                Log.d("做了多少题目", "" + total );
                Log.d("的了多少分", "" + score );
                if (total >= 10){
                    ((HeartChecked)getActivity()).replaceHeart(new HeartChecked2());
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Alert");
                    builder.setMessage("请做完题目");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }
                break;
            default:
                break;

        }
    }
}

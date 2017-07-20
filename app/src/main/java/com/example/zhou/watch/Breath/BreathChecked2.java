package com.example.zhou.watch.Breath;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhou.watch.R;
import com.oudmon.algo.breath.BreathAnalyzer;

import java.io.File;

/**
 * Created by zhou on 2017/7/18.
 */

public class BreathChecked2 extends Fragment {

    private Button breathAgain;
    private TextView breathScore;
    private static final String PATH = Environment.getExternalStorageDirectory()+"/Watch/";
    private static final String WAV_FILE_PATH = "/breath.wav";
    private static final String PCM_FILE_PATH = "/breath.pcm";

    private BreathAnalyzer breathAnalyzer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.breath_checked2, container, false);
        breathAgain = (Button) view.findViewById(R.id.breath_again);
        breathScore = (TextView) view.findViewById(R.id.breath_total_score);
        breathAnalyzer = new BreathAnalyzer();
        File file = new File(PATH + WAV_FILE_PATH);
        Log.d("BreathChecked2", "不在");
        if (file.exists()) {    //TODO -> 文件都不存在，不报错就有鬼了，你不会在调用算法之前，判断一下文件在不在么？
            Log.d("文件在不在", "yes");
            int score = breathAnalyzer.breathRateFromWavFile(PATH + WAV_FILE_PATH);
            breathScore.setText(score + "次");
        }
        breathAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BreathChecked)getActivity()).replaceBreathFragment(new BreathChecked1());
            }
        });
        return view;
    }
}

package com.example.zhou.watch.Lung;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.zhou.watch.R;
import com.oudmon.algo.breath.BreathAnalyzer;

import java.io.File;

/**
 * Created by zhou on 2017/7/18.
 */

public class LungChecked2 extends Fragment {

    private TextView lungScore;
    private static final String PATH = Environment.getExternalStorageDirectory()+"/Watch/";
    private static final String WAV_FILE_PATH = "/breath.wav";
    private static final String PCM_FILE_PATH = "/breath.pcm";
    private int score;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lung_checked2, container, false);
        lungScore = (TextView) view.findViewById(R.id.lung_total_score);
        File  lungFile = new File(PATH + PCM_FILE_PATH);
        if (lungFile.exists()){
            BreathAnalyzer breathAnalyzer = new BreathAnalyzer();
            score = breathAnalyzer.pulmonaryFromWavFile(PATH + PCM_FILE_PATH);
            lungScore.setText(score + "次");
        }
        return view;
    }
}

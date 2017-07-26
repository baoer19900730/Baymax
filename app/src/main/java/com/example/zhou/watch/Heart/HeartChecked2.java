package com.example.zhou.watch.Heart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhou.watch.R;

/**
 * Created by zhou on 2017/7/13.
 */

public class HeartChecked2 extends Fragment {

    private Button again;
    private TextView score;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.heart_checked2, container, false);
        again = (Button) view.findViewById(R.id.heart_again);
        score = (TextView) view.findViewById(R.id.total_score);

        score.setText( ((HeartChecked)getActivity()).getScore() +"分");
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HeartChecked)getActivity()).replaceHeart(new HeartChecked1());
            }
        });
        return view;
    }
}

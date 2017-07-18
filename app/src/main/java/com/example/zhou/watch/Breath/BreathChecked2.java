package com.example.zhou.watch.Breath;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zhou.watch.R;

/**
 * Created by zhou on 2017/7/18.
 */

public class BreathChecked2 extends Fragment {

    private Button breathAgain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.breath_checked2, container, false);
        breathAgain = (Button) view.findViewById(R.id.breath_again);
        breathAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BreathChecked)getActivity()).replaceBreathFragment(new BreathChecked1());
            }
        });
        return view;
    }
}

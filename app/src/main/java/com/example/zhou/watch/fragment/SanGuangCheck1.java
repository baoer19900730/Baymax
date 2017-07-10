package com.example.zhou.watch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zhou.watch.R;

/**
 * Created by zhou on 2017/7/10.
 */

public class SanGuangCheck1 extends Fragment {

    private Button againChecked;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sanguang_checked1, container, false);
        againChecked = (Button) view.findViewById(R.id.sanguang1_again);
        againChecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((VisionChecked)getActivity()).replaceFragment(new SanguangChecked());
            }
        });
        return view;
    }
}

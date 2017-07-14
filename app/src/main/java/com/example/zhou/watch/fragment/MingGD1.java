package com.example.zhou.watch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.zhou.watch.R;

/**
 * Created by zhou on 2017/7/14.
 */

public class MingGD1 extends Fragment {

    private Button again;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.minggd_checked1, container, false);
        again = (Button) view.findViewById(R.id.minggd1_again);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((VisionChecked)getActivity()).replaceFragment(new MingGD());
            }
        });
        return view;
    }
}

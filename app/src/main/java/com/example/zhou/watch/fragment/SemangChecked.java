package com.example.zhou.watch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhou.watch.R;

import java.util.Random;

/**
 * Created by zhou on 2017/7/7.
 */

public class SemangChecked extends Fragment {

    private ImageView picture;  //显示出来的图片
    private int selectPicture; //随机选中的数字
    private TextView next;    //下一题按钮
    private  int total =0; // 总共所测试的题数
    private int right = 0 ; //正确的提示
    private EditText seenNumber;  //编辑框
    private String pictureNumber;  //图中对应的数字


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.semang_checked, container, false);
        picture = (ImageView) view.findViewById(R.id.number_picture);
        seenNumber = (EditText) view.findViewById(R.id.see_number);
        selectPicture = (new Random()).nextInt(9);
        select();
        next = (TextView) view.findViewById(R.id.next_checked);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!seenNumber.getText().toString().equals("")){
                    total ++;
                    if (seenNumber.getText() != null && seenNumber.getText().toString().equals(pictureNumber)){
                        right ++;
                        Toast.makeText(getActivity(), "You are right", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "You are wrong", Toast.LENGTH_SHORT).show();

                    }
                    select();
                    seenNumber.setText("");
                    if (total >= 10){
                        next.setText("查看结果");
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (right >=7){
                                    ((VisionChecked)getActivity()).replaceFragment(new SemangChecked1());
                                }else {
                                    ((VisionChecked)getActivity()).replaceFragment(new SemangChecked2());
                                }
                            }
                        });
                    }
                }else {
                    Toast.makeText(getActivity(), "请输入你看到的数字" , Toast.LENGTH_SHORT).show();
                }
                }

        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void select(){   //设置Image图片
        selectPicture = (new Random()).nextInt(9);
        switch (selectPicture){
            case 1:
                picture.setImageResource(R.drawable.one);
                pictureNumber = "6";
                break;
            case 2:
                picture.setImageResource(R.drawable.two);
                pictureNumber = "3";
                break;
            case 3:
                picture.setImageResource(R.drawable.three);
                pictureNumber = "36";
                break;
            case 4:
                picture.setImageResource(R.drawable.four);
                pictureNumber = "15";
                break;
            case 5:
                picture.setImageResource(R.drawable.five);
                pictureNumber = "73";
                break;
            case 6:
                picture.setImageResource(R.drawable.six);
                pictureNumber = "26";
                break;
            case 7:
                picture.setImageResource(R.drawable.sever);
                pictureNumber = "29";
                break;
            case 8:
                picture.setImageResource(R.drawable.eight);
                pictureNumber = "806";
                break;
            case 0:
                picture.setImageResource(R.drawable.nine);
                pictureNumber = "689";
                break;
            default:
                break;
        }
    }
}

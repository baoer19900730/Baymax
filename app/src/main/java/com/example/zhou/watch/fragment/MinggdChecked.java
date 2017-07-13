package com.example.zhou.watch.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.zhou.watch.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhou on 2017/7/7
 */

public class MinggdChecked extends Fragment implements View.OnClickListener {

    private static final String TAG = "Baymax";

    public final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    public final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;

    private Unbinder unbinder;
    public @BindView(R.id.table) TableLayout mTable;
    public @BindView(R.id.start_checked) Button startChecked;

    /** 初始格子是2*2，最大格子是8*8 **/
    private int step = 2;
    /** 当前关数 **/
    private int stage = 1;
    /** 颜色不同的格子位置 **/
    private int mIndex = -1;
    /** 正常格子颜色 **/
    private int mColor;
    /** 异常格子颜色 **/
    private int mDiffColor;

    private Random mRandom = new Random();

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.minggd_checked, container, false);
        unbinder = ButterKnife.bind(this, view);    //ButterKnife资源绑定,该操作相当于帮我们findViewById了
        createButton();
        return view;
    }

    /**
     * 动态添加Button
     */
    private void createButton() {
        mTable.removeAllViews(); //在添加Button之前删除所有子Button
        initColor();
        for (int row = 0; row < step; row ++) {
            TableRow tableRow = new TableRow(getActivity());    //表格布局的一行，需要一个TableRow装起来
            for (int col = 0; col < step; col ++) {
                TableRow.LayoutParams params = new TableRow.LayoutParams(MATCH_PARENT, MATCH_PARENT, 1.0f); //Button的参数，注意，这个1.0f相当于layout_weight = 1;
                params.leftMargin = 5;
                params.topMargin = 5;
                params.rightMargin = 5;
                params.bottomMargin = 5;

                int index = row * step + col;
                Button button = new Button(getActivity());  //Button初始化
                button.setOnClickListener(this);    //监听
                button.setId(index); //TODO => 思考下为什么这样设置ID，有什么好处
                Log.i(TAG, "mIndex: " + mIndex +  ", index: " + index + ", mColor: " + mColor + ", mDiffColor: " + mDiffColor);
                if (index == mIndex) {  //如果Button的位置等于随机产生的那个位置相同，则是异常的颜色
                    button.setBackgroundColor(mDiffColor);
                } else {
                    button.setBackgroundColor(mColor);
                }
                tableRow.addView(button, params);   //将Button添加到TableRow
            }
            mTable.addView(tableRow, new TableLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT, 1.0f));    //将TableRow添加到table
        }
    }

    /**
     * 初始化颜色等数据
     */
    private void initColor() {
        int r = (int) (Math.round(Math.random() * 234) + 20);
        int g = (int) (Math.round(Math.random() * 234) + 20);
        int b = (int) (Math.round(Math.random() * 234) + 20);
        mColor = Color.rgb(r, g, b);
        mDiffColor = Color.rgb(r - 15, g - 15, b - 15);
        mIndex = mRandom.nextInt(step * step);
        Log.i(TAG, "initColor.. mIndex: " + mIndex + ", r: " + r + ", g: " + g + ", b: " + b);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();  //销毁的时候要解除绑定
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick.. id: " + v.getId());
        if (v.getId() == mIndex) {
            next();
        } else {
            error();
        }
    }

    /**
     * 进入下一关
     */
    private void next() {
        stage ++;
        step ++;
        if (step >= 8) {
            step = 8;
        }
        createButton();
    }

    /**
     * 通过失败，显示关数
     */
    private void error() {
        //TODO 显示关数，值为stage
        Toast.makeText(getContext(), "你通过了" + stage + "关", Toast.LENGTH_SHORT).show();
    }

}

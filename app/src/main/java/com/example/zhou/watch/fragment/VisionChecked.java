package com.example.zhou.watch.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhou.watch.R;

public class VisionChecked extends AppCompatActivity implements View.OnClickListener{

    private Button shili;
    private Button semang;
    private Button minggd;
    private Button sanguang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_checked);
        shili = (Button)findViewById(R.id.shi_li);      //得到按钮实例
        semang = (Button) findViewById(R.id.se_mang);   //得到按钮实例
        minggd = (Button) findViewById(R.id.minggd);    //得到按钮实例
        sanguang = (Button) findViewById(R.id.sanguang); //得到按钮实例
        shili.setOnClickListener(this);                  //注册点击事件
        semang.setOnClickListener(this);                  //注册点击事件
        minggd.setOnClickListener(this);                 //注册点击事件
        sanguang.setOnClickListener(this);              //注册点击事件
        replaceFragment(new VisionCheck1());

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shi_li:
                replaceFragment(new VisionCheck1());
                break;
            case R.id.se_mang:
                replaceFragment(new SemangChecked());
                break;
            case R.id.minggd:
                replaceFragment(new MinggdChecked());
                break;
            case R.id.sanguang:
                replaceFragment(new SanguangChecked());
                break;
            default:
                break;
        }

    }

    public void replaceFragment(Fragment fragment){   //更换碎片方法
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.vision_checked, fragment);
        transaction.commit();
    }
}

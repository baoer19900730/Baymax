package com.example.zhou.watch.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.zhou.watch.MainActivity;
import com.example.zhou.watch.R;

public class VisionChecked extends AppCompatActivity implements View.OnClickListener{

    private Button shili;
    private Button semang;
    private Button minggd;
    private Button sanguang;
    private Button visionBack;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision_checked);
        shili = (Button)findViewById(R.id.shi_li);      //得到按钮实例
        semang = (Button) findViewById(R.id.se_mang);   //得到按钮实例
        minggd = (Button) findViewById(R.id.minggd);    //得到按钮实例
        sanguang = (Button) findViewById(R.id.sanguang); //得到按钮实例
        visionBack = (Button) findViewById(R.id.vision_back);
        visionBack.setOnClickListener(this);
        shili.setOnClickListener(this);                  //注册点击事件
        semang.setOnClickListener(this);                  //注册点击事件
        minggd.setOnClickListener(this);                 //注册点击事件
        sanguang.setOnClickListener(this);              //注册点击事件
        replaceFragment(new VisionCheck1());
        shili.setTextColor(0xffFF5E42); //修改文字颜色
        shili.setBackgroundColor(0xccFFFFFF);   //修改背景颜色
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.vision_back:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.shi_li:
                shili.setTextColor(0xffFF5E42); //修改文字颜色
                shili.setBackgroundColor(0xccFFFFFF);   //修改背景颜色
                replaceFragment(new VisionCheck1());
                semang.setBackgroundColor(0x33FFFFFF);
                minggd.setBackgroundColor(0x33FFFFFF);
                sanguang.setBackgroundColor(0x33FFFFFF);
                break;
            case R.id.se_mang:
                semang.setTextColor(0xffFF5E42);
                semang.setBackgroundColor(0xccFFFFFF);
                replaceFragment(new SemangChecked());
                shili.setBackgroundColor(0x33FFFFFF);
                minggd.setBackgroundColor(0x33FFFFFF);
                sanguang.setBackgroundColor(0x33FFFFFF);
                break;
            case R.id.minggd:
                minggd.setTextColor(0xffFF5E42);
                minggd.setBackgroundColor(0xccFFFFFF);
                replaceFragment(new MingGD());
                semang.setBackgroundColor(0x33FFFFFF);
                shili.setBackgroundColor(0x33FFFFFF);
                sanguang.setBackgroundColor(0x33FFFFFF);
                break;
            case R.id.sanguang:
                sanguang.setTextColor(0xffFF5E42);
                sanguang.setBackgroundColor(0xccFFFFFF);
                replaceFragment(new SanguangChecked());
                semang.setBackgroundColor(0x33FFFFFF);
                minggd.setBackgroundColor(0x33FFFFFF);
                shili.setBackgroundColor(0x33FFFFFF);
                break;
            default:
                break;
        }

    }

    protected void setTotal(int total){
        this.total = total;
    }
    protected int getTotal(){
        return total;
    }

    public void replaceFragment(Fragment fragment){   //更换碎片方法
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.vision_checked, fragment);
        transaction.commit();
    }
}

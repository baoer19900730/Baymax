package com.example.zhou.watch.Breath;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhou.watch.MainActivity;
import com.example.zhou.watch.R;

import java.util.Timer;
import java.util.TimerTask;

public class BreathChecked extends AppCompatActivity implements View.OnClickListener{

    private Button breathBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_checked);
        breathBack = (Button) findViewById(R.id.breath_back);
        breathBack.setOnClickListener(this);
        replaceBreathFragment(new BreathChecked1());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.breath_back:
                finish();
                //TODO -> 为什么不用finish掉本身，而频繁地启用intent创建对象？
                /*Intent earIntent = new Intent(BreathChecked.this, MainActivity.class);
                startActivity(earIntent);*/
                break;
        }
    }

    protected void replaceBreathFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.breath_fragment, fragment);
        transaction.commit();
    }
}

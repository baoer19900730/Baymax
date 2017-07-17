package com.example.zhou.watch.Breath;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhou.watch.MainActivity;
import com.example.zhou.watch.R;

public class BreathChecked extends AppCompatActivity implements View.OnClickListener{

    private Button breathBack;
    private Button breathStart;
    private MyRectangle breathRound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_checked);
        breathBack = (Button) findViewById(R.id.breath_back);
        breathStart = (Button) findViewById(R.id.breath_start);
        breathRound = (MyRectangle) findViewById(R.id.breath_round);
        breathBack.setOnClickListener(this);
        breathStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.breath_start:
                animor();
                break;
            case R.id.breath_back:
                Intent earIntent = new Intent(BreathChecked.this, MainActivity.class);
                startActivity(earIntent);
                break;
        }
    }
    private void animor(){
        ObjectAnimator an = ObjectAnimator.ofFloat(breathRound, "degree", 0, 360);
        an.setDuration(5000);
        an.start();

    }
}

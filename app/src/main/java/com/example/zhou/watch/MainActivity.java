package com.example.zhou.watch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhou.watch.Breath.BreathChecked;
import com.example.zhou.watch.Hear.HearChecked;
import com.example.zhou.watch.Heart.HeartChecked;
import com.example.zhou.watch.Lung.LungChecked;
import com.example.zhou.watch.Rapid.RapidExamineActivity;
import com.example.zhou.watch.fragment.VisionChecked;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ear_button;
    private TextView quick_checked;
    private Button hear_button;
    private Button lung;
    private Button breath;
    private Button heart_checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ear_button = (Button) findViewById(R.id.eye);
        hear_button = (Button) findViewById(R.id.ear);
        quick_checked = (TextView) findViewById(R.id.quick_checked);
        breath = (Button) findViewById(R.id.breath);
        lung = (Button) findViewById(R.id.lung);
        heart_checked = (Button) findViewById(R.id.heart_checked);
        hear_button.setOnClickListener(this);
        ear_button.setOnClickListener(this);
        quick_checked.setOnClickListener(this);
        lung.setOnClickListener(this);
        breath.setOnClickListener(this);
        heart_checked.setOnClickListener(this);
//        if (Build.VERSION.SDK_INT >= 21){
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.eye:
                Intent eyeIntent = new Intent(MainActivity.this, VisionChecked.class);
                startActivity(eyeIntent);
                break;
            case R.id.quick_checked:
                Intent quickIntent = new Intent(MainActivity.this, RapidExamineActivity.class);
                startActivity(quickIntent);
                break;
            case R.id.ear:
                Intent earIntent = new Intent(MainActivity.this, HearChecked.class);
                startActivity(earIntent);
                break;
            case R.id.lung:
                Intent lungIntent = new Intent(MainActivity.this, LungChecked.class);
                startActivity(lungIntent);
                break;
            case R.id.breath:
                Intent breathIntent = new Intent(MainActivity.this, BreathChecked.class);
                startActivity(breathIntent);
                break;
            case R.id.heart_checked:
                Intent heartIntent = new Intent(MainActivity.this, HeartChecked.class);
                startActivity(heartIntent);
                break;
        }
    }
}

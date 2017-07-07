package com.example.zhou.watch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhou.watch.fragment.VisionChecked;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button ear_button;
    private TextView quick_checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ear_button = (Button) findViewById(R.id.eye);
        quick_checked = (TextView) findViewById(R.id.quick_checked);
        ear_button.setOnClickListener(this);
        quick_checked.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.eye:
                Intent eyeIntent = new Intent(MainActivity.this, VisionChecked.class);
                startActivity(eyeIntent);
                break;
            case R.id.quick_checked:
                Intent quickIntent = new Intent(MainActivity.this, RapidExamineActivity.class);
                startActivity(quickIntent);
                break;
        }
    }
}

package com.example.zhou.watch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BreathChecked extends AppCompatActivity {

    private Button breathBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_checked);
        breathBack = (Button) findViewById(R.id.breath_back);
        breathBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent earIntent = new Intent(BreathChecked.this, MainActivity.class);
                startActivity(earIntent);
            }
        });
    }
}

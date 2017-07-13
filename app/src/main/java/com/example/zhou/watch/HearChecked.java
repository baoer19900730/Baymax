package com.example.zhou.watch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HearChecked extends AppCompatActivity {

    private Button hearBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_checked);
        hearBack = (Button) findViewById(R.id.hear_back);
        hearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent earIntent = new Intent(HearChecked.this, MainActivity.class);
                startActivity(earIntent);
            }
        });
    }
}

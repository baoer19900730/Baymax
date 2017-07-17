package com.example.zhou.watch.Heart;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhou.watch.MainActivity;
import com.example.zhou.watch.R;

public class HeartChecked extends AppCompatActivity {

    private Button heartBack;
    private HeartChecked1 heartChecked1;
    private int sCore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_checked);
        heartBack = (Button) findViewById(R.id.heart_back);
        heartChecked1 = (HeartChecked1)getSupportFragmentManager().findFragmentById(R.id.heart_fragment);
        replaceHeart(new HeartChecked1());
        heartBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent earIntent = new Intent(HeartChecked.this, MainActivity.class);
                startActivity(earIntent);
            }
        });
    }

    public void replaceHeart(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.heart_fragment, fragment);
        transaction.commit();
    }

//    public int getScore(){
//        sCore = heartChecked1.scroe;
//        return sCore;
//    }
}

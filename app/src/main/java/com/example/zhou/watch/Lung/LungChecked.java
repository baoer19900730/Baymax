package com.example.zhou.watch.Lung;

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

public class LungChecked extends AppCompatActivity implements View.OnClickListener{

    private Button lungBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lung_checked);
        lungBack = (Button) findViewById(R.id.lung_back);
        replaceLung(new LungChecked1());
        lungBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent earIntent = new Intent(LungChecked.this, MainActivity.class);
        startActivity(earIntent);
    }

    protected  void  replaceLung(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.replace_lung, fragment);
        transaction.commit();
    }
}

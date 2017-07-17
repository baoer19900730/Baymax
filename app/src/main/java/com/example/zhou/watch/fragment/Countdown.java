package com.example.zhou.watch.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Countdown extends Service {
    public Countdown() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int t =  intent.getIntExtra("time",0);
                t--;
            }
        });
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        long time = System.currentTimeMillis() + 1000;
        Intent i = new Intent(this, Countdown.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        manager.set(AlarmManager.RTC, time, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}

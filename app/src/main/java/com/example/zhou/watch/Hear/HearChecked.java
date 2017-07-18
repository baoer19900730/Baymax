package com.example.zhou.watch.Hear;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhou.watch.MainActivity;
import com.example.zhou.watch.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class HearChecked extends AppCompatActivity implements View.OnClickListener{
    private static final int MSG_GET_VOLUME = 0x1001;

    private RecordThread mRecordThread;

    private Button hearBack;  //返回键
    private MySiri mySiri;    //
    private Handler mHandler;

    private Button hearStart; //开始测量
    private Button  hearInaudibility; //听不见
    private Button hearAudibility;   //听得见
    private MediaPlayer player; //音频
    private TextView hearMin;
    private TextView hearMax;

    private int decibel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_checked);
        hearBack = (Button) findViewById(R.id.hear_back);
        mySiri = (MySiri) findViewById(R.id.siri_view);
        hearStart = (Button) findViewById(R.id.hear_start);
        hearInaudibility = (Button) findViewById(R.id.hear_inaudibility);
        hearAudibility = (Button) findViewById(R.id.hear_audibility);
        hearMax = (TextView) findViewById(R.id.hear_max);
        hearMin = (TextView)findViewById(R.id.hear_min) ;
        hearAudibility.setOnClickListener(this);
        hearInaudibility.setOnClickListener(this);
        hearStart.setOnClickListener(this);
        hearBack.setOnClickListener(this);
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == MSG_GET_VOLUME) {
                    update((Float) msg.obj);
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.hear_back:
                Intent earIntent = new Intent(HearChecked.this, MainActivity.class);
                startActivity(earIntent);
                break;
            case R.id.hear_start:
                hearMax.setText("- -");
                hearMin.setText("- -");
                decibel = 0;
                hearStart.setVisibility(View.INVISIBLE);
                hearAudibility.setVisibility(View.VISIBLE);
                hearInaudibility.setVisibility(View.VISIBLE);
                applyPermission();
                break;
            case R.id.hear_inaudibility:
                if (player != null) {
                    player.stop();
                }
                hearInaudibility.setVisibility(View.INVISIBLE);
                hearAudibility.setVisibility(View.INVISIBLE);
                hearStart.setVisibility(View.VISIBLE);
                hearStart.setText("再测一次");
                break;
            case R.id.hear_audibility:
                hearMin.setText("200");
                decibel += 200;
                hearMax.setText(decibel+"");
                break;
        }
    }

    private void applyPermission(){  //申请权限
        if(ContextCompat.checkSelfPermission(HearChecked.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(HearChecked.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else {
          initMedia();
        }
    }

    private void initMedia(){  //准备播放音频
        try {
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor afd = assetManager.openFd("audio_200hz.m4a");
            if (player == null){
                player = new MediaPlayer();
            }
            player.reset();

            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.setLooping(true); //音乐循环播放
            player.prepare();
            player.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initMedia();
                }else {
                    Toast.makeText(HearChecked.this, "拒绝访问", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecordThread = new RecordThread(mHandler);
        mRecordThread.start();
        //mGLWaveformView.onResume();
    }

    @Override
    protected void onPause() {
        //mGLWaveformView.onPause();
        if (mRecordThread != null) {
            mRecordThread.pause();
            mRecordThread = null;
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (player != null){
            player.stop();
            player.release();
        }
        super.onDestroy();
    }

    private void update(final float volume) {
        mySiri.post(new Runnable() {
            @Override
            public void run() {
                mySiri.updateAmplitude(volume * 0.1f / 2000);
            }
        });
    }

    static class RecordThread extends Thread {
        private AudioRecord ar;
        private int bs;
        private final int SAMPLE_RATE_IN_HZ = 8000;
        private boolean isRun = false;
        private Handler mHandler;

        public RecordThread(Handler handler) {
            super();
            bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
                    AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT);
            ar = new AudioRecord(MediaRecorder.AudioSource.MIC,
                    SAMPLE_RATE_IN_HZ,
                    AudioFormat.CHANNEL_CONFIGURATION_MONO,
                    AudioFormat.ENCODING_PCM_16BIT, bs);
            mHandler = handler;
        }

        public void run() {
            super.run();
            ar.startRecording();
            byte[] buffer = new byte[bs];
            isRun = true;
            while (running()) {
                int r = ar.read(buffer, 0, bs);
                int v = 0;
                for (byte aBuffer : buffer) {
                    v += aBuffer * aBuffer;
                }

                Message msg = mHandler.obtainMessage(MSG_GET_VOLUME, v * 1f / r);
                mHandler.sendMessage(msg);
            }
            ar.stop();
        }

        public synchronized void pause() {
            isRun = false;
        }

        private synchronized boolean running() {
            return isRun;
        }

        public void start() {
            if (!running()) {
                super.start();
            }
        }
    }
}

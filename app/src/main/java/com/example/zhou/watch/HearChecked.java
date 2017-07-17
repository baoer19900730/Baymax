package com.example.zhou.watch;

import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhou.watch.Uitil.MySiri;

public class HearChecked extends AppCompatActivity {
    private static final int MSG_GET_VOLUME = 0x1001;

    private RecordThread mRecordThread;

    private Button hearBack;
    private MySiri mySiri;
    private Handler mHandler;

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

        mySiri = (MySiri) findViewById(R.id.siri_view);


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

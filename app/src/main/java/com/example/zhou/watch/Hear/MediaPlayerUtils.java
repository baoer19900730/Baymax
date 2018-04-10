package com.example.zhou.watch.Hear;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

/**
 * Created by jxr20 on 2017/7/3
 */

public class MediaPlayerUtils {

    private static final String TAG = "MediaPlayerUtils";

    private static MediaPlayerUtils mInstance;

    private static MediaPlayer mMediaPlayer;

    private MediaPlayerUtils() {
        mMediaPlayer = new MediaPlayer();
    }

    public static MediaPlayerUtils getInstance() {
        if (mInstance == null) {
            mInstance = new MediaPlayerUtils();
        }
        return mInstance;
    }

    public static void play(Context context, String path) {
        try {
            Log.i(TAG, "play.. path: " + path);
            AssetFileDescriptor fileDescriptor = context.getAssets().openFd(path);
            if (mMediaPlayer == null) {
                mMediaPlayer = new MediaPlayer();
            }
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void playLoop(Context context, String path) {
        try {
            Log.i(TAG, "playLoop.. path: " + path);
            AssetFileDescriptor fileDescriptor = context.getAssets().openFd(path);
            if (mMediaPlayer == null) {
                mMediaPlayer = new MediaPlayer();
            }
            mMediaPlayer.reset();  //让它处于空闲状态
            mMediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            mMediaPlayer.setLooping(true);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        if (mMediaPlayer != null &&  mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

}

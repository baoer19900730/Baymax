package com.example.zhou.watch;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

/**
 * Created by jxr20 on 2017/6/16
 */
public class AudioRecoderUtils implements Runnable {

    private static final String TAG = "AudioRecoderUtils";
    //根目录
    private static final String DIRECTORY = Environment.getExternalStorageDirectory() + "/Watch/";
    //wav文件目录
    protected static final String OUT_FILE_PATH = DIRECTORY + "/breath.wav";
    //pcm文件目录
    private static final String IN_FILE_PATH = DIRECTORY + "/breath.pcm";

    private static final int SOURCE = MediaRecorder.AudioSource.MIC; //TODO 声音来源
    private static final int RATE = 44100;    //TODO 采样率
    private static final int CHANNEL = AudioFormat.CHANNEL_IN_MONO;  //TODO 声道设置
    private static final int FORMAT = AudioFormat.ENCODING_PCM_16BIT;  //TODO 编码制式和采样大小

    private static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(RATE, CHANNEL, FORMAT);  // TODO 缓冲区的大小

    private static AudioRecord mRecorder;
    //记录播放状态
    private static boolean isRecording = false;
    //数字信号数组
    private static byte [] noteArray;
    //PCM文件
    private static File pcmFile;
    //WAV文件
    private static File wavFile;

    //文件输出流
    private static OutputStream os;

    private static Calendar calendar = Calendar.getInstance();

    private static Callback mCallback;


    /**
     * 初始化数据
     * @param callback 回调数据
     */
    public  static void prepare(Callback callback) {
        File file = new File(DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();  //创建目录
        }
        pcmFile = new File(IN_FILE_PATH);
        wavFile = new File(OUT_FILE_PATH);
        if (pcmFile.exists()) {
            pcmFile.delete();
        }
        if (wavFile.exists()) {
            wavFile.delete();
        }
        try {
            pcmFile.createNewFile();
            wavFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mRecorder = new AudioRecord(SOURCE, RATE, CHANNEL, FORMAT, BUFFER_SIZE);
        mCallback = callback;
    }

   public static void start() {
        isRecording = true;
        mRecorder.startRecording();
    }

    /**
     * 记录数据
     */
    /*static void updateCallback(){
        //new Thread(new AudioRecoderUtils()).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                noteArray = new byte[BUFFER_SIZE];
                try {
                    os = new BufferedOutputStream(new FileOutputStream(pcmFile));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (isRecording) {
                    int recordSize = mRecorder.read(noteArray, 0, BUFFER_SIZE);
                    if (recordSize > 0) {
                        try {
                            int voice = 0;
                            for (byte b : noteArray) {
                                voice += b * b;
                            }
                            mCallback.onUpdate(20 * Math.log10(voice * 1.0 / recordSize));    //回调数据
                            os.write(noteArray);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }*/

    /**
     * 记录数据
     */
   public static void updateCallback(){
        new Thread() {
            public void run() {
                write();
            }
        }.start();
    }

    private static void write() {
        noteArray = new byte[BUFFER_SIZE];
        try {
            os = new BufferedOutputStream(new FileOutputStream(pcmFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (isRecording) {
            int recordSize = mRecorder.read(noteArray, 0, BUFFER_SIZE);
            if (recordSize > 0) {
                try {
                    int voice = 0;
                    for (byte b : noteArray) {
                        voice += b * b;
                    }
                    mCallback.onUpdate(20 * Math.log10(voice * 1.0 / recordSize)); //回调数据，Math.log10（）返回一个以10为底的double类型数。
                    os.write(noteArray);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {

    }

   public  static void stop() {
        isRecording = false;
        mRecorder.stop();
    }

    public static void saveWavFile() {
        FileInputStream in;
        FileOutputStream out;
        long totalAudioLen;
        long totalDataLen;
        int channels = 1;
        long byteRate = 16 * RATE * channels / 8;
        byte[] data = new byte[BUFFER_SIZE];
        try {
            in = new FileInputStream(IN_FILE_PATH);
            out = new FileOutputStream(OUT_FILE_PATH);
            totalAudioLen = in.getChannel().size();
            //由于不包括RIFF和WAV
            totalDataLen = totalAudioLen + 36;
            writeWaveFileHeader(out, totalAudioLen, totalDataLen, RATE, channels, byteRate);
            while (in.read(data) != -1) {
                out.write(data);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 任何一种文件在头部添加相应的头文件才能够确定的表示这种文件的格式，
     * wave是RIFF文件结构，
     * 每一部分为一个chunk，
     * 其中有RIFF WAVE chunk， FMT Chunk，Fact chunk,Data chunk,
     * 其中Fact chunk是可以选择的，
     */
    private static void writeWaveFileHeader(FileOutputStream out, long totalAudioLen, long totalDataLen, long longSampleRate,
                                            int channels, long byteRate) throws IOException {
        byte[] header = new byte[44];
        header[0] = 'R'; // RIFF
        header[1] = 'I';
        header[2] = 'F';
        header[3] = 'F';
        header[4] = (byte) (totalDataLen & 0xff);//数据大小
        header[5] = (byte) ((totalDataLen >> 8) & 0xff);
        header[6] = (byte) ((totalDataLen >> 16) & 0xff);
        header[7] = (byte) ((totalDataLen >> 24) & 0xff);
        header[8] = 'W';//WAVE
        header[9] = 'A';
        header[10] = 'V';
        header[11] = 'E';
        //FMT Chunk
        header[12] = 'f'; // 'fmt '
        header[13] = 'm';
        header[14] = 't';
        header[15] = ' ';//过渡字节
        //数据大小
        header[16] = 16; // 4 bytes: size of 'fmt ' chunk
        header[17] = 0;
        header[18] = 0;
        header[19] = 0;
        //编码方式 10H为PCM编码格式
        header[20] = 1; // format = 1
        header[21] = 0;
        //通道数
        header[22] = (byte) channels;
        header[23] = 0;
        //采样率，每个通道的播放速度
        header[24] = (byte) (longSampleRate & 0xff);
        header[25] = (byte) ((longSampleRate >> 8) & 0xff);
        header[26] = (byte) ((longSampleRate >> 16) & 0xff);
        header[27] = (byte) ((longSampleRate >> 24) & 0xff);
        //音频数据传送速率,采样率*通道数*采样深度/8
        header[28] = (byte) (byteRate & 0xff);
        header[29] = (byte) ((byteRate >> 8) & 0xff);
        header[30] = (byte) ((byteRate >> 16) & 0xff);
        header[31] = (byte) ((byteRate >> 24) & 0xff);
        // 确定系统一次要处理多少个这样字节的数据，确定缓冲区，通道数*采样位数
        header[32] = (byte) (16 / 8);
        header[33] = 0;
        //每个样本的数据位数
        header[34] = 16;
        header[35] = 0;
        //Data chunk
        header[36] = 'd';//data
        header[37] = 'a';
        header[38] = 't';
        header[39] = 'a';
        header[40] = (byte) (totalAudioLen & 0xff);
        header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
        header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
        header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
        out.write(header, 0, 44);
    }



    /**
     * 用于回调数据，实时显示趋势
     */
    public interface Callback {
        /**
         * 更新数据
         * @param data 数据
         */
        void onUpdate(double data);
    }

}

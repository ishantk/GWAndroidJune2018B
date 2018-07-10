package com.auribises.gw2018b;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MyMusicService extends Service {

    MediaPlayer mediaPlayer;

    public MyMusicService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("MyMusicService","==onCreate==");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("MyMusicService","==onStartCommand==");

        String songToPlay = intent.getStringExtra("keySong");
        String path = Environment.getExternalStorageDirectory().getPath()+"/"+songToPlay;

        Uri uri = Uri.parse("http://somewebsite/somesong.mp3");

        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(path);
            //mediaPlayer.setDataSource(this,uri); // Online Streaming the Music File
            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (Exception e){
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        mediaPlayer.release();

        Log.i("MyMusicService","==onDestroy==");

    }
}

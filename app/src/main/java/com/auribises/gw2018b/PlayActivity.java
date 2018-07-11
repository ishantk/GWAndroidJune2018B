package com.auribises.gw2018b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtSongName;
    Button btnPlay, btnStop;

    String songName;

    void initViews(){
        txtSongName = findViewById(R.id.textViewSongName);
        btnPlay = findViewById(R.id.buttonPlay);
        btnStop = findViewById(R.id.buttonStop); // IOC

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        Intent rcv = getIntent();
        songName = rcv.getStringExtra("keySong");

        txtSongName.setText(songName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initViews();
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        Intent intent = new Intent(PlayActivity.this,MyMusicService.class);
        intent.putExtra("keySong",songName);

        if(id == R.id.buttonPlay){
            startService(intent);
        }else{
            stopService(intent);
        }

    }
}

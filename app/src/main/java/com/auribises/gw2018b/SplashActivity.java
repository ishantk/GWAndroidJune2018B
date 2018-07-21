package com.auribises.gw2018b;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.auribises.gw2018b.ui.RegisterUserActivity;
import com.auribises.gw2018b.weekend.FragmentsActivity;
import com.auribises.gw2018b.weekend.MyNavActivity;
import com.auribises.gw2018b.weekend.VPActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        handler.sendEmptyMessageDelayed(101,3000);
    }

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 101){
                Intent intent = new Intent(SplashActivity.this,VPActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}

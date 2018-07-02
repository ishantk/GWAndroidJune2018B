package com.auribises.gw2018b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // super call is to ensure the state of an activity

        // Binding the Layout on Activity
        setContentView(R.layout.activity_main);

        Log.i("MainActivity","==onCreate==");
    }


    public void clickHandler(View vRef){

        StringBuilder builder = new StringBuilder();
        for(int i=1;i<=10;i++){
            builder.append("5 "+i+"'s are "+(5*i)+"\n");
        }

        Toast.makeText(this,builder.toString(),Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","==onStart==");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","==onResume==");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","==onPause==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","==onDestroy==");
    }
}

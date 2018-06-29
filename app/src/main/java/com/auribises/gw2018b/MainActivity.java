package com.auribises.gw2018b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding the Layout on Activity
        setContentView(R.layout.activity_main);
    }


    public void clickHandler(View vRef){

        StringBuilder builder = new StringBuilder();
        for(int i=1;i<=10;i++){
            builder.append("5 "+i+"'s are "+(5*i)+"\n");
        }

        Toast.makeText(this,builder.toString(),Toast.LENGTH_LONG).show();
    }

}

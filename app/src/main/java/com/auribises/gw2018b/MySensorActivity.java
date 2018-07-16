package com.auribises.gw2018b;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySensorActivity extends AppCompatActivity implements View.OnClickListener, SensorEventListener{

    @BindView(R.id.textViewSensor)
    TextView txtSensor;

    @BindView(R.id.buttonActivate)
    Button btnActivate;

    SensorManager sensorManager;
    Sensor sensor;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sensor);

        ButterKnife.bind(this);

        btnActivate.setOnClickListener(this);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                /*if(status == TextToSpeech.SUCCESS){
                    Toast.makeText(MySensorActivity.this,"TTS is available",Toast.LENGTH_LONG).show();
                }*/

                if(status == TextToSpeech.ERROR){
                    Toast.makeText(MySensorActivity.this,"TTS is NOT available",Toast.LENGTH_LONG).show();
                }
            }
        });
    }




    @Override
    public void onClick(View v) {
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] values = event.values;

        float x = values[0];
        float y = values[1];
        float z = values[2];

        float cal = ((x*x)+(y*y)+(z*z))/(SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH);

        if(cal>3){
            txtSensor.setText("Device Shaken at Coordinates: " + x + " : " + y + " : " + z);
            sensorManager.unregisterListener(this);

            /*SmsManager smsManager = SmsManager.getDefault();
            String message = "This is an Emergency !!";
            String phone = "+91 99155 71177";
            smsManager.sendTextMessage(phone,null,message,null,null);*/

            String message = "Device Shaken at Coordinates: " + x + " : " + y + " : " + z;

            tts.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
            //tts.speak(message,TextToSpeech.QUEUE_FLUSH,null);

        }else {
            txtSensor.setText("Coordinates: " + x + " : " + y + " : " + z);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
}

package com.auribises.gw2018b;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VoiceRecognitionActivity extends AppCompatActivity implements View.OnClickListener, RecognitionListener {

    @BindView(R.id.textViewData)
    TextView txtData;

    @BindView(R.id.buttonSpeak)
    Button btnSpeak;

    SpeechRecognizer speechRecognizer;

    ProgressDialog progressDialog;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Listening...");

        btnSpeak.setOnClickListener(this);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);
    }

    @Override
    public void onClick(View v) {
        speechRecognizer.startListening(RecognizerIntent.getVoiceDetailsIntent(this));
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {
        progressDialog.show();
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        progressDialog.dismiss();
    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {

        ArrayList<String> list = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        if (list != null && list.size() > 0) {
            String text = list.get(0);
            txtData.setText(text);

            if (text.toLowerCase().contains("help")) {
                // Request for Location updates and send message to family
            }

            if (text.toLowerCase().contains("call") && text.toLowerCase().contains("mom")) {
                String phone = "+91 99155 71177";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Please grant permissions in Settings",Toast.LENGTH_LONG).show();
                }else {
                    startActivity(intent);
                }
            }

            if (text.toLowerCase().contains("message") && text.toLowerCase().contains("dad")) {
               // SMS Manager Code
            }
        }

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}

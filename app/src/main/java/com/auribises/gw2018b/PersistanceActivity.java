package com.auribises.gw2018b;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class PersistanceActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtTitle;
    EditText eTxtName;
    Button btnSave;

    String name;

    SharedPreferences preferences; // To Create and Read XML File
    SharedPreferences.Editor editor; // To Write data in XML File


    void initViews(){
        txtTitle = findViewById(R.id.textViewTitle);
        eTxtName = findViewById(R.id.editTextName);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(this);

        //readDataFromInternalFile();
        //readDataFromExternalFile();

        preferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = preferences.edit();

        String name = preferences.getString("keyName","NA");
        txtTitle.setText(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistance);

        initViews();
    }

    void saveDataInInternalFile(){
        try{

            // Application's SandBox : /data/data/com.auribises.gw2018b
            FileOutputStream outputStream = openFileOutput("data.txt",MODE_PRIVATE);
            //FileOutputStream outputStream = openFileOutput("data.txt",MODE_APPEND);
            outputStream.write(name.getBytes());
            outputStream.close();

            Toast.makeText(this,"Name Written Successfully in File !!",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromInternalFile(){
        try{

            FileInputStream inputStream = openFileInput("data.txt");

            //InputStreamReader reader = new InputStreamReader(inputStream);
            //BufferedReader buffer = new BufferedReader(reader);

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

            String line = buffer.readLine();

            txtTitle.setText("Welcome, "+line);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    void saveDataInExternalFile(){
        try{

            // Path to SD Card
            String path = Environment.getExternalStorageDirectory().getPath();

            File file = new File(path,"data.txt");

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(name.getBytes());
            outputStream.close();

            Toast.makeText(this,"Name Written Successfully in File !!",Toast.LENGTH_LONG).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void readDataFromExternalFile(){
        try{

            String path = Environment.getExternalStorageDirectory().getPath();

            File file = new File(path,"data.txt");

            FileInputStream inputStream = new FileInputStream(file);

            //InputStreamReader reader = new InputStreamReader(inputStream);
            //BufferedReader buffer = new BufferedReader(reader);

            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));

            String line = buffer.readLine();

            txtTitle.setText("Welcome, "+line);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {

        name = eTxtName.getText().toString();
        //saveDataInInternalFile();
        //saveDataInExternalFile();

        editor.putString("keyName",name);
        //editor.commit(); // To Save data in XML File Sync
        editor.apply(); // To Save data in XML File ASync

        Toast.makeText(this,"Name Written Successfully in Shared Preferences !!",Toast.LENGTH_LONG).show();
    }
}

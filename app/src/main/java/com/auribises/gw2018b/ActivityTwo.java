package com.auribises.gw2018b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity {

    // Declare References to the Views which we wanna use
    EditText eTxtName;
    EditText eTxtEmail;

    // Initialize Ref Vars
    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);

        // Obtain reference of the Intent which was passed in A1
        //Intent rcv = getIntent();

        // Getting the data from Intent
        //String name = rcv.getStringExtra("keyName");
        //String email = rcv.getStringExtra("keyEmail");

        /*Bundle rcvBun = rcv.getBundleExtra("keyBundle");

        String name = rcvBun.getString("keyName");
        String email = rcvBun.getString("keyEmail");
        int age = rcvBun.getInt("keyAge");

        eTxtName.setText(name);
        eTxtEmail.setText(email+" | "+age);

        */

        // DE-SERIALIZATION
        //User user = (User)rcv.getSerializableExtra("keyUser");

        //eTxtName.setText(user.name);
        //eTxtEmail.setText(user.email);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initViews();
    }

    public void clickHandler(View view){

        // Extracting name and email
        String name = eTxtName.getText().toString();
        String email = eTxtEmail.getText().toString();

        // Empty Intent with no source nd no destination
        // It will only hold data for us
        Intent data = new Intent();
        data.putExtra("keyName",name);
        data.putExtra("keyEmail",email);

        setResult(201,data);

        finish(); // To Destroy the Activity. Eqv. of pressing the back key
    }
}

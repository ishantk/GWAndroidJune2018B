package com.auribises.gw2018b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity {

    // Declare References to the Views which we wanna use
    EditText eTxtName;
    EditText eTxtEmail;

    // Initialize Ref Vars
    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        initViews(); // initialize views after setContentView
    }

    // Executed on click of Submit Button
    public void clickHandler(View view){

        // Extracting the data from Views
        //String name = eTxtName.getText().toString();
        //String email = eTxtEmail.getText().toString();

        //Toast.makeText(this,"You Entered: "+name+" - "+email,Toast.LENGTH_LONG).show();

        // Forward Passing the Data i.e. from A1 into A2

        // Launch a New Activity on Click of Submit Button
        //Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);

        // 1. FP1
        // Add the data in Intent with Key Value Pair
        //intent.putExtra("keyName",name);
        //intent.putExtra("keyEmail",email);
        //intent.putExtra("keyAge",30);

        //2. FP2
        /*Bundle bundle = new Bundle();
        bundle.putString("keyName",name);
        bundle.putString("keyEmail",email);
        bundle.putInt("keyAge",30);

        intent.putExtra("keyBundle",bundle);*/

        //3. FP3
        // Data Available in User Defined Object
        //User user = new User(name,email);

        // SERIALIZATION
        //intent.putExtra("keyUser",user);

        //startActivity(intent);

        // We are expecting Some data from ActivityTwo
        // Backward Passing
        Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);
        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 && resultCode == 201){
            String name = data.getStringExtra("keyName");
            String email = data.getStringExtra("keyEmail");

            eTxtName.setText(name);
            eTxtEmail.setText(email);
        }
    }
}

package com.auribises.gw2018b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Explicit Menu Creation
        menu.add(1,101,1,"AllSongs");
        menu.add(1,201,1,"Favourites");
        menu.add(1,301,1,"Artists");
        menu.add(1,401,1,"Albums");
        menu.add(1,501,1,"Recently Played");

        // Implicit Menu Creation
        //getMenuInflater().inflate(R.menu.menu_activityone,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case 101:

                Intent intent = new Intent(ActivityOne.this,AllSongsActivity.class);
                startActivity(intent);

                Toast.makeText(this,"All Songs Selected",Toast.LENGTH_LONG).show();
                break;

            case 201:

                break;

            case 301:

                break;

            case 401:

                break;

            case 501:

                break;

            case R.id.bbc:

                break;

            case R.id.aaj:

                Intent intent1 = new Intent(ActivityOne.this,NewsActivity.class);
                startActivity(intent1);

                Toast.makeText(this,"Aaj Tak Selected",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

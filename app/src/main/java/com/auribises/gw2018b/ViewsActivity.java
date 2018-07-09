package com.auribises.gw2018b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{


    CheckBox cbCpp, cbJava, cbPython;
    RadioButton rbMale, rbFemale;

    Spinner spinner;
    ArrayAdapter<String> adapter;

    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> autoAdapter;

    void initSpinner(){

        spinner = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);

        adapter.add("--Select City--"); // 0th index
        adapter.add("Ludhiana");
        adapter.add("Chandigarh");
        adapter.add("Delhi");
        adapter.add("Bengaluru");
        adapter.add("Pune");
        adapter.add("Hyderabad");       // n-1 index
        //...

        spinner.setAdapter(adapter);

        // Event Handling using anonymous Class
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = adapter.getItem(position);
                Toast.makeText(ViewsActivity.this, "You Selected "+city, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void initAutoComplete(){

        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        autoAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        autoAdapter.add("HandBags");
        autoAdapter.add("Wallet");
        autoAdapter.add("Shoes");
        autoAdapter.add("Shirts");
        autoAdapter.add("WallClock");
        autoAdapter.add("Handkerchiefs");
        //..

        autoCompleteTextView.setAdapter(autoAdapter);

    }

    void initViews(){
        cbCpp = findViewById(R.id.checkBoxCpp);
        cbJava = findViewById(R.id.checkBoxJava);
        cbPython = findViewById(R.id.checkBoxPython);

        rbMale = findViewById(R.id.radioButtonMale);
        rbFemale = findViewById(R.id.radioButtonFemale);

        cbCpp.setOnCheckedChangeListener(this);
        cbJava.setOnCheckedChangeListener(this);
        cbPython.setOnCheckedChangeListener(this);

        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
        initViews();
        initSpinner();
        initAutoComplete();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int id = buttonView.getId();

        switch (id){
            case R.id.checkBoxCpp:
                if(isChecked) {
                    Toast.makeText(this, "You Selected C++", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "You UnSelected C++", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.checkBoxJava:

                break;

            case R.id.checkBoxPython:

                break;

            case R.id.radioButtonMale:
                if(isChecked) {
                    Toast.makeText(this, "You Selected Gender as Male", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.radioButtonFemale:

                break;
        }
    }
}

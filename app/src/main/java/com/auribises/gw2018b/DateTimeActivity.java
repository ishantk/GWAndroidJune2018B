package com.auribises.gw2018b;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateTimeActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.textViewDateTime)
    TextView txtDateTime;

    @BindView(R.id.buttonDateTime)
    Button btnDateTime;

    DatePickerDialog datePickerDialog;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    TimePickerDialog timePickerDialog;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        ButterKnife.bind(this);

        btnDateTime.setOnClickListener(this);

    }


    void showDatePickerDialog(){

        Calendar calendar = Calendar.getInstance();

        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        int mm = calendar.get(Calendar.MONTH);
        int yy = calendar.get(Calendar.YEAR);

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                txtDateTime.setText("Date: "+dayOfMonth+"/"+(month+1)+"/"+year);

            }
        };

        datePickerDialog = new DatePickerDialog(this,onDateSetListener,yy,mm,dd);

        datePickerDialog.show();
    }

    void showTimePickerDialog(){

        Calendar calendar = Calendar.getInstance();

        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);

        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txtDateTime.setText("Time is: "+hourOfDay+":"+minute);
            }
        };

        timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hh,mm,true);
        timePickerDialog.show();

    }


    @Override
    public void onClick(View v) {

        //showDatePickerDialog();
        showTimePickerDialog();

    }
}

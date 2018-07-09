package com.auribises.gw2018b;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DialogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
    }


    void showAlertDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("This is Title");
        builder.setMessage("This is Message");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setCancelable(false); // Dialog will not be destroyed on pressing the back key

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    void showAlertDialogWithOptions(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] items = {"View","Update","Delete","Cancel"};

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 3: // Cancel
                        finish();
                        break;
                }
            }
        });

        //AlertDialog dialog = builder.create();
        //dialog.show();

        builder.create().show();
    }

    void showProgressDialog(){
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait..");
        dialog.show();
    }

    //Button btn;

    void showCustomDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_table);

        //btn = dialog.findViewById(R.id.button);

        dialog.show();
    }

    public void clickHandler(View view){
        //showAlertDialog();
        //showAlertDialogWithOptions();
        //showProgressDialog();
        showCustomDialog();
    }
}

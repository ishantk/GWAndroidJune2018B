package com.auribises.gw2018b.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.gw2018b.R;
import com.auribises.gw2018b.Util;
import com.auribises.gw2018b.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
Ishants-Macbook-Air:~ ishantkumar$ cd /Users/ishantkumar/Library/Android/sdk
Ishants-Macbook-Air:sdk ishantkumar$ cd platform-tools/
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb shell
generic_x86:/ # cd data/data/com.auribises.gw2018b/databases
generic_x86:/data/data/com.auribises.gw2018b/databases # ls
Users.db Users.db-journal
generic_x86:/data/data/com.auribises.gw2018b/databases # sqlite3 Users.db
SQLite version 3.19.4 2017-08-18 19:28:12
Enter ".help" for usage hints.
sqlite> .tables
User              android_metadata
sqlite> select * from User;
1|John|9898989898|john@example.com
2|Jennie|9090909090|jennie@example.com
3|Sia|9090909090|sia@example.com
4|Fionna|7878787878|fionna@example.com
5|Jack|9879879870|jack@example.com
 */

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.editTextName)
    EditText eTxtName;

    @BindView(R.id.editTextPhone)
    EditText eTxtPhone;

    @BindView(R.id.editTextEmail)
    EditText eTxtEmail;

    @BindView(R.id.buttonRegister)
    Button btnRegister;

    User user;

    ContentResolver resolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        ButterKnife.bind(this);

        btnRegister.setOnClickListener(this);
        user = new User();

        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);

        eTxtName.startAnimation(animation1);
        btnRegister.startAnimation(animation2);


        resolver = getContentResolver();
    }

    void insertUserInDB(){

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,user.name);
        values.put(Util.COL_PHONE,user.phone);
        values.put(Util.COL_EMAIL,user.email);

        Uri uri = resolver.insert(Util.USER_URI,values);

        Toast.makeText(this,user.name+" Registered: "+uri.getLastPathSegment(),Toast.LENGTH_LONG).show();

        clearFields();
    }

    void clearFields(){
        eTxtName.setText("");
        eTxtPhone.setText("");
        eTxtEmail.setText("");
    }

    boolean validateFields(){
        boolean flag = true;

        if(user.name.isEmpty())
            flag = false;

        if(user.phone.isEmpty()) {
            flag = false;
        }else{
            if(user.phone.length() != 10){
                flag = false;
            }
        }

        if(user.email.isEmpty()) {
            flag = false;
        }else{
            if( !(user.email.contains("@") && user.email.contains("."))){
                flag = false;
            }
        }


        return flag;
    }

    @Override
    public void onClick(View v) {
        user.name = eTxtName.getText().toString().trim();
        user.phone = eTxtPhone.getText().toString().trim();
        user.email = eTxtEmail.getText().toString().trim();

        if(validateFields()) {
            insertUserInDB();
        }else{
            Toast.makeText(this,"Please Enter Correct Details First !!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(1,101,1,"All Users");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == 101){
            Intent intent = new Intent(RegisterUserActivity.this,AllUsersActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

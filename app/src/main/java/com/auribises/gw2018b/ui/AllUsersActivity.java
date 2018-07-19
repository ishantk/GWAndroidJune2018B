package com.auribises.gw2018b.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.auribises.gw2018b.R;
import com.auribises.gw2018b.Util;
import com.auribises.gw2018b.adapter.UsersAdapter;
import com.auribises.gw2018b.model.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllUsersActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;

    ArrayAdapter<String> adapter;

    ArrayList<User> users;
    ArrayList<String> users1;

    ContentResolver resolver;

    UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        ButterKnife.bind(this);

        users = new ArrayList<>();
        users1 = new ArrayList<>();

        resolver = getContentResolver();

        queryUsersFromDB();

    }

    void queryUsersFromDB(){

        String[] projection = {Util.COL_ID,Util.COL_NAME,Util.COL_PHONE,Util.COL_EMAIL};
        Cursor cursor = resolver.query(Util.USER_URI,projection,null,null,null);

        if(cursor != null){

            while (cursor.moveToNext()){

                User user = new User();
                user.id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                user.name = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                user.phone = cursor.getString(cursor.getColumnIndex(Util.COL_PHONE));
                user.email = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));

                users.add(user);
                users1.add(user.toString());

            }

            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,users1);

            //usersAdapter = new UsersAdapter(this,R.layout.list_item,users);

            listView.setAdapter(adapter);

        }

    }
}

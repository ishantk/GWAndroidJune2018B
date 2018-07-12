package com.auribises.gw2018b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllStudentsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //ListView listView;
    //GridView listView;
    RecyclerView recyclerView;
    ArrayList<Student> students;
    StudentsAdapter adapter;

    StudentsRecyclerAdapter recyclerAdapter;

    void initViews(){
        //listView = findViewById(R.id.listView);
        recyclerView = findViewById(R.id.recyclerView);

        Student s1 = new Student(R.drawable.contact,"John","+91 99999 88888");
        Student s2 = new Student(R.drawable.category,"Jennie","+91 77777 88888");
        Student s3 = new Student(R.drawable.pb,"Jim","+91 66666 88888");
        Student s4 = new Student(R.drawable.folder,"Jack","+91 99999 55555");
        Student s5 = new Student(R.drawable.music,"Joe","+91 77777 66666");
        Student s6 = new Student(R.drawable.pb,"Fionna","+91 66666 88888");
        Student s7 = new Student(R.drawable.folder,"George","+91 99999 55555");
        Student s8 = new Student(R.drawable.music,"Mike","+91 77777 66666");

        students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        students.add(s8);

        //adapter = new StudentsAdapter(this,R.layout.list_item,students);
        //listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);

        recyclerAdapter = new StudentsRecyclerAdapter(this,R.layout.list_item,students);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student sRef = students.get(position);
        Toast.makeText(this,"You Selected: "+sRef.name,Toast.LENGTH_LONG).show();
    }
}

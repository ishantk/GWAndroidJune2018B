package com.auribises.gw2018b;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*class Point{
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}*/

public class StudentsAdapter extends ArrayAdapter<Student> {

    Context context;
    int resource;
    ArrayList<Student> objects;

    public StudentsAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    // Execute n number of times from 0 to n-1, where n is the size of ArrayList -> objects
    // Value of position will change from 0 to n-1
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //1. View should point to resource
        View view = LayoutInflater.from(context).inflate(resource,parent,false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView txtName = view.findViewById(R.id.textViewName);
        TextView txtPhone = view.findViewById(R.id.textViewPhone);

        //2. Extract Object from ArrayList on position
        Student sRef = objects.get(position);

        //3. To Set Data on View from Object
        imageView.setBackgroundResource(sRef.image);
        txtName.setText(sRef.name);
        txtPhone.setText(sRef.phone);

        return view;
    }
}

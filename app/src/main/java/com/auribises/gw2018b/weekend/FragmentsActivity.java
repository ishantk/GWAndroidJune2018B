package com.auribises.gw2018b.weekend;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.auribises.gw2018b.R;

public class FragmentsActivity extends AppCompatActivity {

    MyUpperFragment upperFragment;
    MyLowerFragment lowerFragment;

    FragmentManager fragmentManager;

    void initViews(){
        upperFragment = new MyUpperFragment();
        lowerFragment = new MyLowerFragment();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.upperFrame,upperFragment).commit();
        fragmentManager.beginTransaction().add(R.id.lowerFrame,lowerFragment).commit();

        //fragmentManager.beginTransaction().replace(R.id.lowerFrame,lowerFragment).commit();
        //fragmentManager.beginTransaction().remove(lowerFragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        initViews();
    }
}

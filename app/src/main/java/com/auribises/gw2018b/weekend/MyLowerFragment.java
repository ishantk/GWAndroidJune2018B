package com.auribises.gw2018b.weekend;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.auribises.gw2018b.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyLowerFragment extends Fragment {

    ListView listView;
    ArrayAdapter<String> adapter;

    public MyLowerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_lower, container, false);

        listView = view.findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        adapter.add("Amazon");
        adapter.add("Flipkart");
        adapter.add("Myntra");
        adapter.add("Jabong");

        listView.setAdapter(adapter);

        return view;
    }

}

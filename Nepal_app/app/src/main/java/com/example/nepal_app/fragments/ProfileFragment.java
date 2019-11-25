package com.example.nepal_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.R;
import com.example.nepal_app.fragments.child.Fragment_addChild;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileFragment extends Fragment {
    ListView list;
    String[] child = {"Martha", "Marie"};
    FloatingActionButton add;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        add = view.findViewById(R.id.floatingActionButton4);

        list = view.findViewById(R.id.list);


        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.child_element, R.id.name, child);
        list.setAdapter(adapter);

        add.setOnClickListener( (test) -> {
            getFragmentManager().beginTransaction().replace(R.id.container, new Fragment_addChild()).addToBackStack(null).commit();

        });



        return view;
    }


}

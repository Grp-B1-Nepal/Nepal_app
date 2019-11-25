package com.example.nepal_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.Factory.GetAndSet;
import com.example.nepal_app.Factory.POJO;
import com.example.nepal_app.R;
import com.example.nepal_app.fragments.child.ChildObj;
import com.example.nepal_app.fragments.child.Fragment_addChild;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class ProfileFragment extends Fragment {
    private ListView list;
    private String[] child;
    private FloatingActionButton add;
    private POJO pojo;
    private ArrayList<ChildObj> childArr = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        pojo = POJO.getInstance();

        childArr = pojo.getChildArr(getContext());

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        add = view.findViewById(R.id.floatingActionButton4);

        list = view.findViewById(R.id.list);

        child = new String[childArr.size()];
        for (int i = 0; i<childArr.size();i++){
        child[i] = childArr.get(i).getName();
        }




        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.child_element, R.id.name, child);
        list.setAdapter(adapter);

        add.setOnClickListener( (test) -> {
            getFragmentManager().beginTransaction().replace(R.id.container, new Fragment_addChild()).addToBackStack(null).commit();

        });



        return view;
    }


}

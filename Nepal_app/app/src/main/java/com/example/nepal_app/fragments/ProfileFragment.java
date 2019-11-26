package com.example.nepal_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.Factory.GetAndSet;
import com.example.nepal_app.Factory.POJO;
import com.example.nepal_app.R;
import com.example.nepal_app.fragments.child.ChildObj;
import com.example.nepal_app.fragments.child.Fragment_addChild;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

public class ProfileFragment extends Fragment {
    private ListView list;
    private String[] child;
    private String[] birthday;
    private String[] gender;
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

        getChild();

        getBirthday();


        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.child_element, R.id.name, child);
        list.setAdapter(adapter);

        add.setOnClickListener( (test) -> {
            getFragmentManager().beginTransaction().replace(R.id.container, new Fragment_addChild()).addToBackStack(null).commit();

        });



        return view;
    }

    private void getChild(){
        child = new String[childArr.size()];
        for (int i = 0; i<childArr.size();i++){
            child[i] = childArr.get(i).getName();
        }
    }

    //TODO fix the apdapter
    private void getBirthday() {
        Calendar calendar = Calendar.getInstance();
        birthday = new String[childArr.size()];
        String date1;
        String date2;
        String date3;
        for (int i = 0; i < childArr.size(); i++) {
            calendar.setTimeInMillis(childArr.get(i).getBirthday());
            date1 = calendar.getTime().toString().substring(4,10);
            date2 = calendar.getTime().toString().substring(30,34);
            date3 =  "Birthday " + date1 + " " +  date2;
            birthday[i] = date3;
        }
    }

    private void getGender(){
        gender = new String[childArr.size()];
        for (int i = 0; i<childArr.size();i++){
            gender[i] = childArr.get(i).getGender();
        }
    }


}

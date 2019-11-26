package com.example.nepal_app.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.Adaptor.Adaptor_Listview;
import com.example.nepal_app.Factory.POJO;
import com.example.nepal_app.R;
import com.example.nepal_app.Fragments.child.ChildObj;
import com.example.nepal_app.Fragments.child.Fragment_addChild;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class ProfileFragment extends Fragment {
    private ListView list;
    private String[] child;
    private String[] birthday;
    private String[] gender;
    private ArrayList<Bitmap> images = new ArrayList<>();
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
        getGender();
        getImage();

        Adaptor_Listview adaptor = new Adaptor_Listview(getContext(), childArr,images);
        list.setAdapter(adaptor);

        //TODO create fragment for this
       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        */


        add.setOnClickListener((test) -> {
            getFragmentManager().beginTransaction().replace(R.id.container, new Fragment_addChild()).addToBackStack(null).commit();

        });


        return view;
    }

    private void getChild() {
        child = new String[childArr.size()];
        for (int i = 0; i < childArr.size(); i++) {
            child[i] = childArr.get(i).getName();
        }
    }

    //TODO fix the apdapter
    private void getBirthday() {
        Calendar calendar = Calendar.getInstance();
        birthday = new String[childArr.size()];
        String date1, date2, date3;
        for (int i = 0; i < childArr.size(); i++) {
            calendar.setTimeInMillis(childArr.get(i).getBirthday());
            date1 = calendar.getTime().toString().substring(4, 10);
            date2 = calendar.getTime().toString().substring(30, 34);
            date3 = "Birthday " + date1 + " " + date2;
            birthday[i] = date3;
        }
    }

    private void getGender() {
        gender = new String[childArr.size()];
        for (int i = 0; i < childArr.size(); i++) {
            gender[i] = childArr.get(i).getGender();
        }
    }

    private void getImage() {
        images.addAll(pojo.getBitmap(getContext()));
    }
}

package com.example.nepal_app.UI.Fragments.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.Logic.Adaptor.Adaptor_ListviewChild;
import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.R;
import com.example.nepal_app.Logic.ChildObj;
import com.example.nepal_app.UI.Fragments.Profile.Child.Fragment_addChild;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class ProfileFragment extends Fragment {
    private ListView list;
    private String[] birthday;
    private FloatingActionButton add;
    private ChildInfo childInfo;


    private ArrayList<ChildObj> childArr = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        childInfo = ChildInfo.getInstance();

        childArr = childInfo.getChildArr(getContext());

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        add = view.findViewById(R.id.floatingActionButton4);
        list = view.findViewById(R.id.list);
        getBirthday();

        if(birthday.length != 0 && childArr.size() != 0) {
            Adaptor_ListviewChild adaptor = new Adaptor_ListviewChild(getContext(), childArr, birthday);
            list.setAdapter(adaptor);
        }

        add.setOnClickListener((test) -> {
            getFragmentManager().beginTransaction().replace(R.id.container, new Fragment_addChild()).addToBackStack(null).commit();

        });

        return view;
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
            date3 = date1 + " " + date2;
            birthday[i] = date3;
        }
    }
}

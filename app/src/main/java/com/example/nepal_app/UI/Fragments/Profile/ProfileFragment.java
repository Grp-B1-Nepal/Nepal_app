package com.example.nepal_app.UI.Fragments.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.Logic.Adaptor.Adaptor_ListviewChild;
import com.example.nepal_app.Logic.Factory.ChildInfo;
import com.example.nepal_app.R;
import com.example.nepal_app.Logic.Objects.ChildObj;
import com.example.nepal_app.UI.Fragments.Profile.Child.Fragment_addChild;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    private ListView list;
    private FloatingActionButton addChildButton;
    private ChildInfo childInfo;
    private TextView activeChild;

    private ArrayList<ChildObj> childArr = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        childInfo = ChildInfo.getInstance();

        //Gets the childArr from the singleton class
        childArr = childInfo.getChildArr(getContext());

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        addChildButton = view.findViewById(R.id.floatingActionButton4);
        list = view.findViewById(R.id.list);
        activeChild = view.findViewById(R.id.active_child);
        if (childArr.size() == 0){
            activeChild.setVisibility(View.INVISIBLE);
        }

        //Checks if there is data in the list before setting the adaptor.
        if(childArr != null) {
            Adaptor_ListviewChild adaptor = new Adaptor_ListviewChild(getContext(), childArr, childInfo.getBirthdayString(), childInfo.getProgressAge());
            list.setAdapter(adaptor);
        }

        //OnClickListner for the editor button
        addChildButton.setOnClickListener((something) -> {
            getFragmentManager().beginTransaction().replace(R.id.container, new Fragment_addChild()).addToBackStack(null).commit();
        });

        return view;
    }
}

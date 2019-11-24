package com.example.nepal_app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.R;

public class ProfileFragment extends Fragment {
    ListView list;
    String[] child = {"Martha", "Marie"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        list = view.findViewById(R.id.list);


        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.child_element, R.id.name, child);
        list.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

}

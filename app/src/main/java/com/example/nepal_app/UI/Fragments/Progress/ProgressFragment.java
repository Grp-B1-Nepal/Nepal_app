package com.example.nepal_app.UI.Fragments.Progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.nepal_app.R;

public class ProgressFragment extends Fragment {
    ImageView image;
    TextView info, age, box_age, height, weight;
    ConstraintLayout box;
    Button b1, b2, b3, b4, b5, b6, b7, b8,b9,b10,b11,b12;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        b1 = view.findViewById(R.id.b1);



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false);

    }
}
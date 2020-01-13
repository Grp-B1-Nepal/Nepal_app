package com.example.nepal_app.UI.Fragments.Progress;

import android.graphics.PorterDuff;
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
    private Button b1, b2, b3, b4, b5, b6, b7, b8,b9,b10,b11,b12;
    private int agerange;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        b1 = view.findViewById(R.id.b1);
        b2 = view.findViewById(R.id.b2);
        b3 = view.findViewById(R.id.b3);
        b4 = view.findViewById(R.id.b4);
        b5 = view.findViewById(R.id.b5);
        b6 = view.findViewById(R.id.b6);
        b7 = view.findViewById(R.id.b7);
        b8 = view.findViewById(R.id.b8);
        b9 = view.findViewById(R.id.b9);
        b10 = view.findViewById(R.id.b10);
        b11 = view.findViewById(R.id.b11);
        b12 = view.findViewById(R.id.b12);



        if (agerange == 1) {
            b1.getBackground().setColorFilter(getResources().getColor(R.color.redMat), PorterDuff.Mode.MULTIPLY);
        } else if (agerange == 2) {
            b2.getBackground().setColorFilter(getResources().getColor(R.color.redMat), PorterDuff.Mode.MULTIPLY);
        } else if (agerange == 3) {
            b3.getBackground().setColorFilter(getResources().getColor(R.color.redMat), PorterDuff.Mode.MULTIPLY);
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false);

    }
}
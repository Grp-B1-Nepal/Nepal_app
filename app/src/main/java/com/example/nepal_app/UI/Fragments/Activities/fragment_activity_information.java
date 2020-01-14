package com.example.nepal_app.UI.Fragments.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.R;

public class fragment_activity_information extends Fragment {

    private View rod;
    private TextView headline, informationtext;
    private ImageView topimage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rod = inflater.inflate(R.layout.fragment_activity_informationfragment, container, false);

        headline = rod.findViewById(R.id.fragment_activity_informationfragment_headline);
        informationtext = rod.findViewById(R.id.fragment_activity_informationfragment_infotext);
        topimage = rod.findViewById(R.id.fragment_activity_informationfragment_picture);
        
        return rod;
    }
}

package com.example.nepal_app.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private ImageView recipesI, developI, activitiesI, recipSound, develoSound, activitySound;
    private Button recipeB, developB, activitesB;
    private View rod;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public HomeFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment com.example.navigationbarapp.NavigationBarFragments.HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            //settin up images
            recipesI = rod.findViewById(R.id.imageRecipe);
            developI = rod.findViewById(R.id.imageDevelopment);
            activitiesI = rod.findViewById(R.id.imageActivities);
            recipSound = rod.findViewById(R.id.speakerRecipe);
            develoSound = rod.findViewById(R.id.speakerDevelop);
            activitySound = rod.findViewById(R.id.speakerActivity);

            //setting up buttons
            recipeB = rod.findViewById(R.id.recipesButton);
            developB = rod.findViewById(R.id.developButton);
            activitesB = rod.findViewById(R.id.activitiesButton);

            //setting text on buttons
            recipeB.setText("Go to recipes");
            developB.setText("Go to development");
            activitesB.setText("Go to activities");

            //setting up listeners
            recipeB.setOnClickListener(this);
            developB.setOnClickListener(this);
            activitesB.setOnClickListener(this);
            recipSound.setOnClickListener(this);
            develoSound.setOnClickListener(this);
            activitySound.setOnClickListener(this);


        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}

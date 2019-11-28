package com.example.nepal_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nepal_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button Singing, Talking, Tummy_Time, Cuddling_Time, Play_Time, Reading;
    private View rod;




    public ActivitiesFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ActivitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivitiesFragment newInstance(String param1, String param2) {
        ActivitiesFragment fragment = new ActivitiesFragment();
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
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rod = inflater.inflate(R.layout.fragment_activities, container, false);

        Singing = rod.findViewById(R.id.fragment_activities_Singing);
        Talking = rod.findViewById(R.id.fragment_activities_Talking);
        Tummy_Time = rod.findViewById(R.id.fragment_activities_TummyTime);
        Cuddling_Time = rod.findViewById(R.id.fragment_activities_CuddlingTime);
        Play_Time = rod.findViewById(R.id.fragment_activities_PlayTime);
        Reading = rod.findViewById(R.id.fragment_activities_Reading);

        Singing.setOnClickListener(this);
        Talking.setOnClickListener(this);
        Tummy_Time.setOnClickListener(this);
        Cuddling_Time.setOnClickListener(this);
        Play_Time.setOnClickListener(this);
        Reading.setOnClickListener(this);

        return rod;
    }

    @Override
    public void onClick(View v) {
        Fragment information_fragment = new fragment_activity_information();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, information_fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

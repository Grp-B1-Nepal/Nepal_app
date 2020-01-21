package com.example.nepal_app.UI.Fragments.Activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Logic.Adaptor.ActivityAdapter;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class shows the information for the activity fragment after a category has been chosen.
 * @author s185031 Gustav Emil Nobert
 */
public class fragment_activity_information extends Fragment {

    private View rod;
    private ImageView topimage;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter rcAdapter;
    private TextView headlinetxt;

    private int imageView;
    private int listnum;
    private int imagenum;
    private String headlinestring;
    private ArrayList<Integer> soundfiles = new ArrayList<>();

//Recieves the information in a pojo object.
    public fragment_activity_information(ActivitiesFragment.ActivityPOJO activityPOJO)  {
        this.listnum = activityPOJO.getInformationnum();
        this.soundfiles = activityPOJO.getSoundnumberlist();
        this.imagenum = activityPOJO.getImagenum();
        this.headlinestring = activityPOJO.getHeadlinetext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (rod == null ){

        rod = inflater.inflate(R.layout.fragment_activity_informationfragment, container, false);

        recyclerView = rod.findViewById(R.id.fragment_acitivityinformation_recyclerview);
        topimage = rod.findViewById(R.id.fragment_activity_informationfragment_picture);
        headlinetxt = rod.findViewById(R.id.activity_information_headline);
        headlinetxt.setText(headlinestring);

        topimage.setImageResource(imagenum);

        initRecyclerView();
        recyclerView.setNestedScrollingEnabled(false);
        }
        return rod;
    }

    /**
     * Initializes the recycler view.
     */
    private void initRecyclerView() {
        //Here the picture is passed to the recycler view. It's just a reference to the speaker icon.
        imageView = R.drawable.speaker;

        ArrayList<String> stringlist = new ArrayList<>();
        //Returns the correct information from strings based on an int.
        Collections.addAll(stringlist, getActivity().getResources().getStringArray(listnum));
        rcAdapter = new ActivityAdapter(imageView, stringlist, soundfiles);
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rcAdapter);
    }
}

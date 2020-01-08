package com.example.nepal_app.Fragments.Activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nepal_app.Adaptor.ActivityAdapter;
import com.example.nepal_app.Fragments.Recipes.RecipeHome;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class fragment_activity_information extends Fragment {

    private View rod;
    private ImageView topimage;
    private TextView textView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter rcAdapter;

    private int imageView;
    private int listnum;
    private int imagenum;
    private ArrayList<Integer> soundfiles = new ArrayList<>();

    private RecipeHome.OnNoteListener onNoteListener;

    public fragment_activity_information(int listnum, ArrayList<Integer> soundfiles, int imagenum)  {
        this.listnum = listnum;
        this.soundfiles = soundfiles;
        this.imagenum = imagenum;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rod = inflater.inflate(R.layout.fragment_activity_informationfragment, container, false);

        recyclerView = rod.findViewById(R.id.fragment_acitivityinformation_recyclerview);
        topimage = rod.findViewById(R.id.fragment_activity_informationfragment_picture);

        initRecyclerView();

        return rod;
    }

    private void initRecyclerView() {

        //Here the picture is passed to the recycler view.
        imageView = R.drawable.speaker;

        ArrayList<String> stringlist = new ArrayList<>();
        Collections.addAll(stringlist, getActivity().getResources().getStringArray(listnum));
        rcAdapter = new ActivityAdapter(imageView, stringlist, (ActivityAdapter.OnNoteListener) onNoteListener, soundfiles);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rcAdapter);

    }
}

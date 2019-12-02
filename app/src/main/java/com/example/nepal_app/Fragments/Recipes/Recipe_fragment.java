package com.example.nepal_app.Fragments.Recipes;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nepal_app.Adaptor.RecyclerViewAdapterRecipe;
import com.example.nepal_app.R;

import java.util.ArrayList;

public class Recipe_fragment extends Fragment {
    private static final String TAG = "RecipeActivity";
    private ArrayList<String> recipeText = new ArrayList<>();
    private ArrayList<Integer> recipeImage = new ArrayList<>();
    private String mParam1;
    private String mParam2;
    RecyclerViewAdapterRecipe adapter;
    RecyclerView recyclerView;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View rod;

    public Recipe_fragment() {
        // Required empty public constructor
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
        rod = inflater.inflate(R.layout.activity_recipe, container, false);
        arrayFill();

        return rod;
    }

    private void arrayFill() {
        Log.d(TAG,"Filling arraylist.");

        recipeImage.add(R.drawable.egg_image);
        recipeText.add("2 x Eggs");

        recipeImage.add(R.drawable.example2);
        recipeText.add("mad mad1");

        recipeImage.add(R.drawable.example3);
        recipeText.add("mad mad2");

        recipeImage.add(R.drawable.example4);
        recipeText.add("mad mad3");

        recipeImage.add(R.drawable.example2);
        recipeText.add("mad mad4");

        recipeImage.add(R.drawable.example2);
        recipeText.add("mad mad5");

        recipeImage.add(R.drawable.egg_image);
        recipeText.add("Hej hej\nHej hej\nHej hej\nHej hej\n");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView initializing");

        recyclerView = rod.findViewById(R.id.recyclerv_view);
        adapter = new RecyclerViewAdapterRecipe(getActivity(), recipeImage, recipeText);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}

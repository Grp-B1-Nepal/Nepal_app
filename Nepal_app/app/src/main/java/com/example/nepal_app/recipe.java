package com.example.nepal_app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.nepal_app.Fragments.RecipesFragment;

import java.util.ArrayList;

public class recipe extends Fragment {
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

    public recipe() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipesFragment newInstance(String param1, String param2) {
        RecipesFragment fragment = new RecipesFragment();
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

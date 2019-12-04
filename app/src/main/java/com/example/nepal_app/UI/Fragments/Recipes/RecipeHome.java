package com.example.nepal_app.UI.Fragments.Recipes;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nepal_app.R;
import com.example.nepal_app.Logic.Adaptor.RecipeAdapter;

import java.util.ArrayList;

public class RecipeHome extends Fragment implements RecipeAdapter.OnNoteListener {
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View rod;

    //TODO searchbar functionality
    //TODO Top buttons (All, favorite, Today) functionality
    //TODO make into a fragment

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter rcAdapter;

    private ArrayList<Integer> imageViews = new ArrayList<>();
    private ArrayList<String> recipeNames = new ArrayList<>();

    private OnNoteListener onNoteListener;

    public RecipeHome() {
        //empty constructor
    }

    public void fillLists() {
        imageViews.add(R.drawable.egg_image);
        imageViews.add(R.drawable.egg_image);
        imageViews.add(R.drawable.egg_image);
        imageViews.add(R.drawable.egg_image);

        recipeNames.add("Dal");
        recipeNames.add("Cake");
        recipeNames.add("Banana");
        recipeNames.add("Mash");
        initRecyclerView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        rod = inflater.inflate(R.layout.recipe_home, container, false);

        fillLists();
        return rod;

    }

    public interface OnNoteListener extends RecipeAdapter.OnNoteListener {
        void onNoteClick(int position);
    }



    private void initRecyclerView() {

        recyclerView = rod.findViewById(R.id.rcvrecipes);
        rcAdapter = new RecipeAdapter(imageViews, recipeNames, onNoteListener);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rcAdapter);

    }
//TODO FIX NOT WORKING
    @Override
    public void onNoteClick(int position) {
        /*
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new Recipe_fragment());
        transaction.addToBackStack(null);
        transaction.commit();
         */
    }
}
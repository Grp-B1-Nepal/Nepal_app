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
import java.util.HashSet;

public class RecipeHome extends Fragment implements RecipeAdapter.OnNoteListener {
    View rod;


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
        imageViews.add(R.drawable.recipehome_bananas);
        imageViews.add(R.drawable.recipehome_cake);
        imageViews.add(R.drawable.recipehome_dal);
        imageViews.add(R.drawable.recipehome_chicken);

        recipeNames.add("Banana");
        recipeNames.add("Cake");
        recipeNames.add("Dal");
        recipeNames.add("Chicken");
        initRecyclerView();
    }

    HashSet<Integer> opened = new HashSet<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
package com.example.nepal_app.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.nepal_app.R;

import java.util.ArrayList;

public class RecipeHome_activity extends AppCompatActivity {

    //TODO remove search bar from recipemain and into activity_main
    //TODO searchbar functionality
    //TODO Top buttons (All, favorite, Today) functionality

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter rcAdapter;

    private ArrayList<Integer> imageViews = new ArrayList<>();
    private ArrayList<String> recipeNames = new ArrayList<>();

    private OnNoteListener onNoteListener;

    public void fillLists() {
        imageViews.add(R.drawable.egg_image);
        imageViews.add(R.drawable.egg_image);
        imageViews.add(R.drawable.egg_image);
        imageViews.add(R.drawable.egg_image);

        recipeNames.add("Dal");
        recipeNames.add("Cake");
        recipeNames.add("Banana");
        recipeNames.add("Mash");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillLists();

        recyclerView = findViewById(R.id.rcvrecipes);

        recyclerView.setHasFixedSize(true);

        rcAdapter = new RecipeAdapter(imageViews, recipeNames, onNoteListener);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rcAdapter);


    }


    public interface OnNoteListener extends RecipeAdapter.OnNoteListener {
        void onNoteClick(int position);

    }
}
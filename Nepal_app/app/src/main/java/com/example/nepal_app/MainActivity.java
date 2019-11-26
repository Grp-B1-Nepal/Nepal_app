package com.example.nepal_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //TODO Make arraylist of recipes
    //TODO remove search bar from recipemain and into activity_main
    //TODO mangler onclicklistener


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter rcAdapter;

    private ArrayList<Integer> imageViews = new ArrayList<>();
    private ArrayList<String> recipeNames = new ArrayList<>();

    public void fillLists() {
        imageViews.add(R.drawable.dal);
        imageViews.add(R.drawable.cake);
        imageViews.add(R.drawable.banana);

        recipeNames.add("Dal");
        recipeNames.add("Cake");
        recipeNames.add("Banana");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillLists();

        recyclerView = findViewById(R.id.rcvrecipes);

        recyclerView.setHasFixedSize(true);

        rcAdapter = new RecipeAdapter(imageViews, recipeNames);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(rcAdapter);


    }
}
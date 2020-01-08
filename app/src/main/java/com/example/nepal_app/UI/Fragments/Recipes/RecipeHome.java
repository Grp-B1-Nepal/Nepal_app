package com.example.nepal_app.UI.Fragments.Recipes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nepal_app.Logic.Adaptor.RecipeHomeAdapter;
import com.example.nepal_app.Logic.RecipeForHome;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeHome extends Fragment {

    List<RecipeForHome> recipeList;
    List<String> categoryList;
    Button btnViewRecipe, btnFavorite;

    public void fillLists() {
        recipeList = new ArrayList<>();
        categoryList = new ArrayList<>();
        recipeList.add(new RecipeForHome("Banana", R.drawable.recipehome_bananas, btnViewRecipe, btnFavorite));
        recipeList.add(new RecipeForHome("Cake", R.drawable.recipehome_cake, btnViewRecipe, btnFavorite));
        recipeList.add(new RecipeForHome("Dal", R.drawable.recipehome_dal, btnViewRecipe, btnFavorite));
        recipeList.add(new RecipeForHome("Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));
        categoryList.add("Recommended");
        categoryList.add("Favorites");
        categoryList.add("Snacks");
        categoryList.add("Common");
        categoryList.add("Search");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fillLists();
        final View v = inflater.inflate(R.layout.recipe_home, container, false);
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recipeRecView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final RecipeHomeAdapter adapter = new RecipeHomeAdapter(recipeList, categoryList);
                c.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();

        return v;

    }


}
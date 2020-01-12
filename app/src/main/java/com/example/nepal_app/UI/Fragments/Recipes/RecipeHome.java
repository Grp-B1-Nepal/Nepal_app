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

import com.example.nepal_app.Logic.Adaptor.CategoryAdapter;
import com.example.nepal_app.Logic.CategoryObject;
import com.example.nepal_app.Logic.Adaptor.RecipeHomeAdapter;
import com.example.nepal_app.Logic.RecipeObject;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeHome extends Fragment {

    //TODO Make a new arraylist, that contains all recipes for all category (essentially 4 arraylists) and make the adapter operate on that.

    List<CategoryObject> categoryList;
    List<RecipeObject> recipeList;
    Button btnViewRecipe, btnFavorite;

    public void fillLists() {
        recipeList = new ArrayList<>();
        categoryList = new ArrayList<>();

        categoryList.add(new CategoryObject("Recommended", recipeList));
        categoryList.add(new CategoryObject("Favorites", recipeList));
        categoryList.add(new CategoryObject("Snacks", recipeList));
        categoryList.add(new CategoryObject("Common", recipeList));
        categoryList.add(new CategoryObject("Search", recipeList));

        recipeList.add(new RecipeObject("Banana", R.drawable.recipehome_bananas, btnViewRecipe, btnFavorite));
        recipeList.add(new RecipeObject("Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));
        recipeList.add(new RecipeObject("Cake", R.drawable.recipehome_cake, btnViewRecipe, btnFavorite));
        recipeList.add(new RecipeObject("Dal", R.drawable.recipehome_dal, btnViewRecipe, btnFavorite));
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
                final CategoryAdapter adapter = new CategoryAdapter(categoryList);
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
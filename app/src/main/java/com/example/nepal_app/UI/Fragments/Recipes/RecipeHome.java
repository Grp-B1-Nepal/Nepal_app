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
import com.example.nepal_app.Logic.CategoriesWithRecipeListsObject;
import com.example.nepal_app.Logic.RecipeForHome;
import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeHome extends Fragment {

    //TODO Make a new arraylist, that contains all recipes for all category (essentially 4 arraylists) and make the adapter operate on that.

    List<CategoriesWithRecipeListsObject> recipeList;
    Button btnViewRecipe, btnFavorite;

    public void fillLists() {
        recipeList = new ArrayList<>();

        ArrayList<RecipeForHome> Recommended = new ArrayList<>();
        ArrayList<RecipeForHome> Favorites = new ArrayList<>();
        ArrayList<RecipeForHome> Snacks = new ArrayList<>();
        ArrayList<RecipeForHome> Common = new ArrayList<>();
        ArrayList<RecipeForHome> Search = new ArrayList<>();


        Recommended.add(new RecipeForHome("Banana", R.drawable.recipehome_bananas, btnViewRecipe, btnFavorite));
        Recommended.add(new RecipeForHome("Cake", R.drawable.recipehome_cake, btnViewRecipe, btnFavorite));
        Recommended.add(new RecipeForHome("Dal", R.drawable.recipehome_dal, btnViewRecipe, btnFavorite));
        Recommended.add(new RecipeForHome("Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));

        Favorites.add(new RecipeForHome("Banana", R.drawable.recipehome_bananas, btnViewRecipe, btnFavorite));
        Favorites.add(new RecipeForHome("Cake", R.drawable.recipehome_cake, btnViewRecipe, btnFavorite));
        Favorites.add(new RecipeForHome("Dal", R.drawable.recipehome_dal, btnViewRecipe, btnFavorite));
        Favorites.add(new RecipeForHome("Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));

        Snacks.add(new RecipeForHome("Banana", R.drawable.recipehome_bananas, btnViewRecipe, btnFavorite));
        Snacks.add(new RecipeForHome("Cake", R.drawable.recipehome_cake, btnViewRecipe, btnFavorite));
        Snacks.add(new RecipeForHome("Dal", R.drawable.recipehome_dal, btnViewRecipe, btnFavorite));
        Snacks.add(new RecipeForHome("Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));

        Common.add(new RecipeForHome("Banana", R.drawable.recipehome_bananas, btnViewRecipe, btnFavorite));
        Common.add(new RecipeForHome("Cake", R.drawable.recipehome_cake, btnViewRecipe, btnFavorite));
        Common.add(new RecipeForHome("Dal", R.drawable.recipehome_dal, btnViewRecipe, btnFavorite));
        Common.add(new RecipeForHome("Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));

        Search.add(new RecipeForHome("Banana", R.drawable.recipehome_bananas, btnViewRecipe, btnFavorite));
        Search.add(new RecipeForHome("Cake", R.drawable.recipehome_cake, btnViewRecipe, btnFavorite));
        Search.add(new RecipeForHome("Dal", R.drawable.recipehome_dal, btnViewRecipe, btnFavorite));
        Search.add(new RecipeForHome("Chicken", R.drawable.recipehome_chicken, btnViewRecipe, btnFavorite));

        recipeList.add(new CategoriesWithRecipeListsObject("Recommended", Recommended));
        recipeList.add(new CategoriesWithRecipeListsObject("Favorites", Favorites));
        recipeList.add(new CategoriesWithRecipeListsObject("Snacks", Snacks));
        recipeList.add(new CategoriesWithRecipeListsObject("Common", Common));
        recipeList.add(new CategoriesWithRecipeListsObject("Search", Search));
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
                final RecipeHomeAdapter adapter = new RecipeHomeAdapter(recipeList);
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
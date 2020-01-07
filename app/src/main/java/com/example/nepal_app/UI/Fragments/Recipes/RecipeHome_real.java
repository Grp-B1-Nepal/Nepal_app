package com.example.nepal_app.UI.Fragments.Recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nepal_app.R;

import java.util.ArrayList;

public class RecipeHome_real extends Fragment {

    static class allRecipes {

        ArrayList<Integer> recipeImagesRecommended = new ArrayList<>();
        ArrayList<String> recipeNamesRecommended = new ArrayList<>();

        ArrayList<Integer> recipeImagesSnacks = new ArrayList<>();
        ArrayList<String> recipeNamesSnacks = new ArrayList<>();

        ArrayList<Integer> recipeImagesCommon = new ArrayList<>();
        ArrayList<String> recipeNamesCommon = new ArrayList<>();

        public void fillLists() {

            recipeImagesRecommended.add(R.drawable.recipehome_bananas);
            recipeImagesRecommended.add(R.drawable.recipehome_cake);
            recipeImagesRecommended.add(R.drawable.recipehome_dal);
            recipeImagesRecommended.add(R.drawable.recipehome_chicken);

            recipeNamesRecommended.add("Banana");
            recipeNamesRecommended.add("Cake");
            recipeNamesRecommended.add("Dal");
            recipeNamesRecommended.add("Chicken");

            recipeImagesSnacks.add(R.drawable.recipehome_bananas);
            recipeImagesSnacks.add(R.drawable.recipehome_cake);
            recipeImagesSnacks.add(R.drawable.recipehome_dal);
            recipeImagesSnacks.add(R.drawable.recipehome_chicken);

            recipeNamesSnacks.add("Banana");
            recipeNamesSnacks.add("Cake");
            recipeNamesSnacks.add("Dal");
            recipeNamesSnacks.add("Chicken");

            recipeImagesCommon.add(R.drawable.recipehome_bananas);
            recipeImagesCommon.add(R.drawable.recipehome_cake);
            recipeImagesCommon.add(R.drawable.recipehome_dal);
            recipeImagesCommon.add(R.drawable.recipehome_chicken);

            recipeNamesCommon.add("Banana");
            recipeNamesCommon.add("Cake");
            recipeNamesCommon.add("Dal");
            recipeNamesCommon.add("Chicken");
        }
        
    }

    

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View hej = null;

        return hej;

    }

}

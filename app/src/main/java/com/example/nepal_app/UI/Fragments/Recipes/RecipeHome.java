package com.example.nepal_app.UI.Fragments.Recipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nepal_app.Logic.Adaptor.CategoryAdapter;
import com.example.nepal_app.Logic.Objects.CategoryObject;
import com.example.nepal_app.Logic.Factory.RecipeInfo;

import com.example.nepal_app.Logic.Objects.RecipeHomeObject;

import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the fragment for recipes.
 */

public class RecipeHome extends Fragment {
    private RecipeInfo recipeInfo;


    List<CategoryObject> categoryList; //A CategoryObject contains a name as a string and an Arraylist of RecipeHomeObjects
    List<Integer> btnIcons; //This is a list if images to put on the buttons
    public List<RecipeHomeObject> recipeRecommendedList, recipeSnacksList, recipeCommonList; //This is List of recipes we want in the different categories
    EditText searchField; //This is the search bar in the top of the layout


    /**
     * This initialises all the data we use. We make use of the JSON file.
     */
    public void fillLists() {
        //We get the recipeInfo instance and set all lists to be arraylists
        recipeInfo = recipeInfo.getInstance();
        recipeRecommendedList = new ArrayList<>();
        recipeSnacksList = new ArrayList<>();
        recipeCommonList = new ArrayList<>();
        categoryList = new ArrayList<>();
        btnIcons = new ArrayList<>();

        //Loads all recipes with tag recommended from JSON file
        recipeRecommendedList = recipeInfo.getRecipeListByTag(getContext(),"recommended");
        categoryList.add(new CategoryObject("सिफारिश गरिएको", recipeRecommendedList));

        //Loads all recipes with tag snack from JSON file
        recipeSnacksList = recipeInfo.getRecipeListByTag(getContext(),"snack");
        categoryList.add(new CategoryObject("स्नैक्स", recipeSnacksList));


        //Loads all recipes with tag common from JSON file
        recipeCommonList = recipeInfo.getRecipeListByTag(getContext(),"common");
        categoryList.add(new CategoryObject("सामान्य", recipeCommonList));

        //Loads all images from drawables
        btnIcons.add(R.drawable.ic_reho_recommended);
        btnIcons.add(R.drawable.ic_reho_snack);
        btnIcons.add(R.drawable.ic_reho_common);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //We fill in our data
        fillLists();
        //We set up our recyclerview
        final View v = inflater.inflate(R.layout.recipe_home, container, false);
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recipeRecView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        final CategoryAdapter adapter = new CategoryAdapter(categoryList, btnIcons, getContext());
        recyclerView.setAdapter(adapter);

        return v;

    }

    //This method makes sure search works
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //We need the adapter, as it contains the Filter
        CategoryAdapter adapter = new CategoryAdapter(categoryList, btnIcons, getContext());

        //The reason we have created this in onViewCreate, is that you cant findviewbyid in a fragment in onCreateView
        searchField = getView().findViewById(R.id.searchField);
        //We add a textchangelistener to the searchbar
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //We filter on the text we type
                if (adapter != null) {
                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
package com.example.nepal_app.UI.Fragments.Recipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.nepal_app.Logic.Adaptor.CategoryAdapter;
import com.example.nepal_app.Logic.Objects.CategoryObject;
import com.example.nepal_app.Logic.Factory.RecipeInfo;

import com.example.nepal_app.Logic.FavoriteRecipes;
import com.example.nepal_app.Logic.Objects.RecipeHomeObject;

import com.example.nepal_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeHome extends Fragment {
    private RecipeInfo recipeInfo;

    List<CategoryObject> categoryList;
    List<Integer> btnIcons;
    public List<RecipeHomeObject> recipeRecommendedList, recipeSnacksList, recipeCommonList, recipeFavoritesList, favoriteList, mainList;
    EditText searchField;

    public void fillLists() {
        recipeInfo = recipeInfo.getInstance();
        recipeRecommendedList = new ArrayList<>();
        recipeSnacksList = new ArrayList<>();
        recipeCommonList = new ArrayList<>();
        categoryList = new ArrayList<>();
        favoriteList = FavoriteRecipes.getInstance().favoriteList;
        btnIcons = new ArrayList<>();

        //Loads all recipes with tag recommended
        recipeRecommendedList = recipeInfo.getRecipeListByTag(getContext(),"recommended");
        categoryList.add(new CategoryObject("Recommended", recipeRecommendedList));

        // Loads all recipes with returns true on favorite
        recipeFavoritesList = recipeInfo.getRecipeListByTag(getContext(),"favorite");
        categoryList.add(new CategoryObject("Favorites", recipeFavoritesList));

        //Loads all recipes with tag snack
        recipeSnacksList = recipeInfo.getRecipeListByTag(getContext(),"snack");
        categoryList.add(new CategoryObject("Snacks", recipeSnacksList));


        // Loads all recipes with tag common
        recipeCommonList = recipeInfo.getRecipeListByTag(getContext(),"common");
        categoryList.add(new CategoryObject("Common", recipeCommonList));


        btnIcons.add(R.drawable.ic_reho_recommended);
        btnIcons.add(R.drawable.ic_reho_heart);
        btnIcons.add(R.drawable.ic_reho_snack);
        btnIcons.add(R.drawable.ic_reho_common);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        fillLists();
        final View v = inflater.inflate(R.layout.recipe_home, container, false);
        final FragmentActivity c = getActivity();
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recipeRecView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);
        final CategoryAdapter adapter = new CategoryAdapter(categoryList, btnIcons, getContext());
        recyclerView.setAdapter(adapter);

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CategoryAdapter adapter = new CategoryAdapter(categoryList, btnIcons, getContext());

        searchField = getView().findViewById(R.id.searchField);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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
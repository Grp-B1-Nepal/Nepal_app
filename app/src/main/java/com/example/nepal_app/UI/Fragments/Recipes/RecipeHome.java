package com.example.nepal_app.UI.Fragments.Recipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.os.Bundle;
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
    public List<RecipeHomeObject> recipeList, favoriteList;
    EditText searchField;

    public void fillLists() {
        recipeInfo = recipeInfo.getInstance();

        recipeList = recipeInfo.getRecipeList(getContext());
        categoryList = new ArrayList<>();
        favoriteList = FavoriteRecipes.getInstance().favoriteList;
        btnIcons = new ArrayList<>();

        categoryList.add(new CategoryObject("Recommended", recipeList));
        categoryList.add(new CategoryObject("Favorites", favoriteList));
        categoryList.add(new CategoryObject("Snacks", recipeList));
        categoryList.add(new CategoryObject("Common", recipeList));

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
        searchField = getView().findViewById(R.id.searchField);
    }
}
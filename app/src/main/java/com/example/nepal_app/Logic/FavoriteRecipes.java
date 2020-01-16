package com.example.nepal_app.Logic;

import java.util.ArrayList;

public class FavoriteRecipes {
    public ArrayList<RecipeHomeObject> favoriteList = new ArrayList<>();

    private static final FavoriteRecipes ourInstance = new FavoriteRecipes();

    public static FavoriteRecipes getInstance() {
        return ourInstance;
    }

    private FavoriteRecipes() {
    }
}

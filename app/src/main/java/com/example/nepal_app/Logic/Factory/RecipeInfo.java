package com.example.nepal_app.Logic.Factory;

import android.content.Context;

import com.example.nepal_app.Datalayer.RecipeJSONParsing;
import com.example.nepal_app.Logic.Objects.RecipeHomeObject;
import com.example.nepal_app.Logic.Objects.RecipeObj;

import java.util.ArrayList;

public class RecipeInfo {
    private static final RecipeInfo Recipeinstans = new RecipeInfo();
    private int recipePosition;
    private RecipeObj recipe;
    private ArrayList<RecipeHomeObject> recipeHomeObjects;
    private String image;
    private String name;

    private RecipeJSONParsing recipeJSONParsing = RecipeJSONParsing.getInstance();

    private RecipeInfo(){}

    public RecipeObj getRecipe(int position, Context context) {
        recipe = recipeJSONParsing.loadRecipe(position,context);
        return recipe;
    }

    public ArrayList<RecipeHomeObject> getRecipeListByTag(Context context, String tag) {
        recipeHomeObjects = recipeJSONParsing.loadRecipeListByTag(context, tag);
        return recipeHomeObjects;
    }

    public RecipeHomeObject getSingleHomeRecipe (int pos, Context context) {
        RecipeHomeObject recipe = recipeJSONParsing.loadSingleHomeRecipe(pos, context);

        return recipe;
    }

    public String getRecipeImage(int positon,Context context) {
        image = recipeJSONParsing.loadImage(positon,context);
        return image;
    }

    public int getRecipePosition(Context context, String recipeName) {
        return recipeJSONParsing.getPositionStringMatch(context,recipeName);
    }

    public static RecipeInfo getInstance(){return Recipeinstans;}

    public int getPostionRecipe(){
        return recipePosition;
    }
    public void setPostionRecipe(int postionRecipe){
        this.recipePosition = postionRecipe;
    }

    public String getRecipeName() {
        return name;
    }

    public void setRecipeName(String name) {
        this.name = name;
    }
}

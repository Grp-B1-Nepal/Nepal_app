package com.example.nepal_app.Logic.Factory;

import android.content.Context;

import com.example.nepal_app.Datalayer.RecipeJSONParsing;
import com.example.nepal_app.Logic.Objects.RecipeHomeObject;
import com.example.nepal_app.Logic.Objects.RecipeObj;

import java.util.ArrayList;

public class RecipeInfo {
    private static final RecipeInfo Recipeinstans = new RecipeInfo();
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

    public String getRecipeName() {
        return name;
    }

    public int getPosFromName(String name, Context context) {
       ArrayList<RecipeHomeObject> recList = recipeJSONParsing.loadRecipeListByTag(context, "loadAll");
       for (int i = 0; i < recList.size(); i++) {
           if (recList.get(i).getRecipeName().equals(name)) {
               return i;
           }
       }
       return 0;
    }

    public void setRecipeName(String name) {
        this.name = name;
    }

    public static RecipeInfo getInstance(){return Recipeinstans;}
}

package com.example.nepal_app.Logic.Factory;

import android.content.Context;

import com.example.nepal_app.Datalayer.RecipeJSONParsing;
import com.example.nepal_app.Logic.RecipeObj;

import java.io.IOException;

public class RecipeInfo {
    private static final RecipeInfo Recipeinstans = new RecipeInfo();
    private int recipePostion;
    private RecipeObj recipe;

    /* ONLY useful if we need to obtain/save all recipes at once.
    private ArrayList<RecipeObj> recipeArr = new ArrayList<>();*/

    private RecipeJSONParsing recipeJSONParsing = RecipeJSONParsing.getInstance();

    private RecipeInfo(){}

    public RecipeObj getRecipe(int position, Context context) {
        try {
            recipe = recipeJSONParsing.loadRecipe(position,context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public void setRecipe(RecipeObj recipe) {
        this.recipe = recipe;
    }

    public static RecipeInfo getInstance(){return Recipeinstans;}

    public int getPostionRecipe(){

        return recipePostion;
    }
    public void setPostionRecipe(int postionRecipe){
        this.recipePostion = postionRecipe;
    }
}

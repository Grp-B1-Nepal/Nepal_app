package com.example.nepal_app.Logic;

import java.util.ArrayList;

public class CategoriesWithRecipeListsObject {

    String category;
    ArrayList<RecipeForHome> recipeList;
    private boolean expanded;


    public CategoriesWithRecipeListsObject(String category, ArrayList<RecipeForHome> recipeList) {
        this.category = category;
        this.recipeList = recipeList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ArrayList<RecipeForHome> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(ArrayList<RecipeForHome> recipeList) {
        this.recipeList = recipeList;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "CategoriesWithRecipeListsObject{" +
                "category='" + category + '\'' +
                ", recipeList=" + recipeList +
                ", expanded=" + expanded +
                '}';
    }


}

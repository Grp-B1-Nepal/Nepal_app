package com.example.nepal_app.Logic.Objects;

public class RecipeHomeObject {
    public RecipeHomeObject(String recipeName, String recipeImg) {
        this.recipeName = recipeName;
        this.recipeImg = recipeImg;
    }

    public String getRecipeName() {
        return recipeName;
    }

    private String recipeName;

    public String getRecipeImg() {
        return recipeImg;
    }

    private String recipeImg;

    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "RecipeHomeObject{" +
                "recipeName=" + recipeName +
                ", recipeImg=" + recipeImg +
                '}';
    }

}

package com.example.nepal_app.Logic;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeObject {

    public RecipeObject(String recipeName, int recipeImg, Button btnViewRecipe, Button btnFavorite) {

        this.recipeName = recipeName;
        this.recipeImg = recipeImg;
        this.btnViewRecipe = btnViewRecipe;
        this.btnFavorite = btnFavorite;

    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    private String recipeName;

    public int getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(int recipeImg) {
        this.recipeImg = recipeImg;
    }

    private int recipeImg;

    public Button getBtnViewRecipe() {
        return btnViewRecipe;
    }

    public void setBtnViewRecipe(Button btnViewRecipe) {
        this.btnViewRecipe = btnViewRecipe;
    }

    private Button btnViewRecipe;

    public Button getBtnFavorite() {
        return btnFavorite;
    }

    public void setBtnFavorite(Button btnFavorite) {
        this.btnFavorite = btnFavorite;
    }

    private Button btnFavorite;

    private boolean expanded;


    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "RecipeObject{" +
                "recipeName=" + recipeName +
                ", recipeImg=" + recipeImg +
                ", btnViewRecipe=" + btnViewRecipe +
                ", btnFavorite=" + btnFavorite +
                '}';
    }

}

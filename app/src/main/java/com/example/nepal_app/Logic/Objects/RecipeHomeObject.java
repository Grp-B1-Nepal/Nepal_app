package com.example.nepal_app.Logic.Objects;

public class RecipeHomeObject {

    private String recipeName;
    private String recipeImg;
    private boolean expanded;


    public RecipeHomeObject(String recipeName, String recipeImg) {
        this.recipeName = recipeName;
        this.recipeImg = recipeImg;
    }



    /**
     * gets recipename
     * @return string containing recipe name
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * gets an image of a recipe
     * @return string containing the image of a recipe
     */
    public String getRecipeImg() {
        return recipeImg;
    }

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

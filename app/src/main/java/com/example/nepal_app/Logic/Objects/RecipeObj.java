package com.example.nepal_app.Logic.Objects;

import java.util.ArrayList;

public class RecipeObj {
    private String recipeName;

    private ArrayList<String> images, ingrediens, directions;

    public RecipeObj(String recipeName, ArrayList<String> images, ArrayList<String> ingrediens, ArrayList<String> directions) {
        this.recipeName = recipeName;
        this.images = images;
        this.ingrediens = ingrediens;
        this.directions = directions;
    }

    public String getRecipeName() {
        return recipeName;
    }
    public void setRecipeName(String name) {
        this.recipeName = name;
    }
    public String getIngrediens(int position) {
        return ingrediens.get(position);
    }
    public String getDirections(int position) {
        return directions.get(position);
    }
    public String getImages(int position) {
        return images.get(position);
    }
    public int getRecipeArrayListSizeIngrediens() {
        return ingrediens.size();
    }

    public int getRecipeArrayListSizeDirections() {
        return directions.size();
    }
}

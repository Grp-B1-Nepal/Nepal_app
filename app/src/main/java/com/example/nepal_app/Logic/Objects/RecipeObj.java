package com.example.nepal_app.Logic.Objects;

import java.util.ArrayList;

public class RecipeObj {
    private String name;

    private ArrayList<String> images, ingrediens, directions;

    public RecipeObj(String name, ArrayList<String> images, ArrayList<String> ingrediens, ArrayList<String> directions) {
        this.name = name;
        this.images = images;
        this.ingrediens = ingrediens;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

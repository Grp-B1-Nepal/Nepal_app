package com.example.nepal_app.Logic;

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
    public ArrayList<String> getDirections() {
        return directions;
    }
    public int getImages(int position) {
        return Integer.valueOf(images.get(position));
    }
    public int getRecipeArrayListSize() {
        return ingrediens.size();
    }
}

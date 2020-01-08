package com.example.nepal_app.Logic;

import java.util.ArrayList;

public class RecipeObj {
    private String name;
    private ArrayList<String> ingrediens, directions;

    public RecipeObj(String name, ArrayList<String> ingrediens, ArrayList<String> directions) {
        this.name = name;
        this.ingrediens = ingrediens;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<String> getIngrediens() {
        return ingrediens;
    }
    public void setIngrediens(ArrayList<String> ingrediens) {
        this.ingrediens = ingrediens;
    }
    public ArrayList<String> getDirections() {
        return directions;
    }
    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
    }
}

package com.example.nepal_app.Logic;

import java.util.List;

public class CategoryObject {
    private String categoryName;
    private boolean expanded;
    private List<RecipeHomeObject> recipeList;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<RecipeHomeObject> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<RecipeHomeObject> recipeList) {
        this.recipeList = recipeList;
    }

    public CategoryObject(String categoryName, List<RecipeHomeObject> recipeList) {
        this.recipeList = recipeList;
        this.categoryName = categoryName;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    @Override
    public String toString() {
        return "CategoryObject{" +
                "categoryName='" + categoryName + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}

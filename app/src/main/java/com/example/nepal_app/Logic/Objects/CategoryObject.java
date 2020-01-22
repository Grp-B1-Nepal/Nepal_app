package com.example.nepal_app.Logic.Objects;

import java.util.List;

public class CategoryObject {
    private String categoryName;
    private boolean expanded;
    private List<RecipeHomeObject> recipeList;

    /**
     * constructor for category object
     * @param categoryName name of category
     * @param recipeList list of recipehomeobjects
     */
    public CategoryObject(String categoryName, List<RecipeHomeObject> recipeList) {
        this.recipeList = recipeList;
        this.categoryName = categoryName;
    }

    /**
     * gets category name
     * @return category name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * sets a category name
     * @param categoryName the name you wish to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * gets a list of recipe objects
     * @return a list of recipe objects
     */
    public List<RecipeHomeObject> getRecipeList() {
        return recipeList;
    }

    /**
     * sets a list of recipes objects
     * @param recipeList the list of recipes objects
     */
    public void setRecipeList(List<RecipeHomeObject> recipeList) {
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
        return "CategoryObject{" +
                "categoryName='" + categoryName + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}

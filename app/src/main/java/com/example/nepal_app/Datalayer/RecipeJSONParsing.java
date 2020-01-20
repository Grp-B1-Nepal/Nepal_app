package com.example.nepal_app.Datalayer;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nepal_app.Logic.Objects.RecipeHomeObject;
import com.example.nepal_app.Logic.Objects.RecipeObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecipeJSONParsing extends AppCompatActivity {
    private static final RecipeJSONParsing RecipeJSONParsinInstans = new RecipeJSONParsing();
    public static RecipeJSONParsing getInstance(){return RecipeJSONParsinInstans;}

    private RecipeJSONParsing() { }

    public JSONArray readJSON(Context context) {
        JSONArray jsonArray = null;
        try {
            //opens and read the JSON file located in our assets folder.
            InputStream is = context.getAssets().open("recipes.json");
            byte b[] = new byte[is.available()];
            is.read(b);
            String str = new String(b, "UTF-8");
            jsonArray = new JSONArray(str);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //returns the JSON as array
        return jsonArray;
    }

    public ArrayList<RecipeHomeObject> loadRecipeList(Context context) {
        ArrayList<RecipeHomeObject> recipeHomeObjects = new ArrayList<>();
        JSONArray jsonArray = readJSON(context);
        String name, image;

        try {
            // Retrieves the name and images from each recipe in our JSON file.
            for (int i = 0; i < jsonArray.length(); i++) {
                name = jsonArray.getJSONObject(i).getString("name");
                image = jsonArray.getJSONObject(i).getString("image");
                recipeHomeObjects.add(new RecipeHomeObject(name,image));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return recipeHomeObjects;
    }

    public RecipeHomeObject loadSingleHomeRecipe(int pos, Context context) {
        RecipeHomeObject recipe;
        String name, image;
        name = "";
        image = "";
        JSONArray jsonArr = readJSON(context);
        try {
            name = jsonArr.getJSONObject(pos).getString("name");
            image = jsonArr.getJSONObject(pos).getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recipe = new RecipeHomeObject(name, image);
        return recipe;
    }

    public String loadImage(int position, Context context) {
        String picture = "";

        try {
            JSONArray jsonArray = readJSON(context);
            picture = jsonArray.getJSONObject(position).getString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return picture;
    }

    public RecipeObj loadRecipe(int position, Context context) {
        String recipeName = "";
        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> ingrediens = new ArrayList<>();
        ArrayList<String> directions = new ArrayList<>();
        try {
            JSONArray jsonArray = readJSON(context);
            recipeName = jsonArray.getJSONObject(position).getString("name");

            //Finds right recipe, then goes into the recipe itself and then save it from the start.
            JSONObject mainRecipeObject = jsonArray.getJSONObject(position).getJSONArray("recipe").getJSONObject(0);

            for (int i = 0; i < mainRecipeObject.length(); i++) {
                String item;

                if(i==0) {
                    JSONArray picturesArr = mainRecipeObject.getJSONArray("images");
                    for (int j = 0; j < picturesArr.length(); j++) {
                        item = picturesArr.getString(j);
                        images.add(item);
                    }
                } else if(i==1) {
                    JSONArray ingrediensArr = mainRecipeObject.getJSONArray("ingrediens");
                    for (int j = 0; j < ingrediensArr.length(); j++) {
                        item = ingrediensArr.getString(j);
                        ingrediens.add(item);
                    }
                } else if(i==2) {
                    JSONArray dirArr = mainRecipeObject.getJSONArray("directions");
                    for (int j = 0; j < dirArr.length(); j++) {
                        item = dirArr.getString(j);
                        directions.add(item);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecipeObj recipe = new RecipeObj(recipeName,images,ingrediens,directions);
        return recipe;
    }
}
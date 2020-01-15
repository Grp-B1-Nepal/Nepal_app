package com.example.nepal_app.Datalayer;

import android.content.Context;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nepal_app.Logic.RecipeHomeObject;
import com.example.nepal_app.Logic.RecipeObj;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecipeJSONParsing extends AppCompatActivity {
    private static final RecipeJSONParsing RecipeJSONParsinInstans = new RecipeJSONParsing();
    public static RecipeJSONParsing getInstance(){return RecipeJSONParsinInstans;}

    /* ONLY useful if we need to obtain/save all recipes at once.
    private ArrayList<RecipeObj> recipes = new ArrayList<>();*/

    private RecipeJSONParsing() { }

    public JSONArray readJSON(Context context) {
        JSONArray jsonArray = null;
        try {
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
        return jsonArray;
    }

    public ArrayList<RecipeHomeObject> loadRecipeList(Context context) {
        ArrayList<RecipeHomeObject> recipeHomeObjects = new ArrayList<>();
        JSONArray jsonArray = readJSON(context);
        String name, image;

        try {
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

    public RecipeObj loadRecipe(int position, Context context) {
        String name = "";
        ArrayList<String> images = new ArrayList<>();
        ArrayList<String> ingrediens = new ArrayList<>();
        ArrayList<String> directions = new ArrayList<>();
        try {
            JSONArray jsonArray = readJSON(context);

            JSONObject json = jsonArray.getJSONObject(position);
            name = json.getString("name");

            JSONArray mainRecipeTxt = json.getJSONArray("recipe");
            JSONObject mainRecipeObject = mainRecipeTxt.getJSONObject(0);

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

        RecipeObj recipe = new RecipeObj(name,images,ingrediens,directions);
        return recipe;
    }
}
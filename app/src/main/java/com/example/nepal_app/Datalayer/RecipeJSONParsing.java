package com.example.nepal_app.Datalayer;

import androidx.appcompat.app.AppCompatActivity;
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

    public RecipeObj loadRecipe(int position) throws IOException {
        String name = "";
        ArrayList<String> ingrediens = new ArrayList<>();
        ArrayList<String> directions = new ArrayList<>();
        try {
            // Reading from JSON file
            InputStream is = getApplicationContext().getAssets().open("recipes.json");
            byte b[] = new byte[is.available()];
            is.read(b);
            String str = new String(b, "UTF-8");

            JSONArray jsonArray = new JSONArray(str);
            JSONObject json = jsonArray.getJSONObject(position);
            name = json.getString("name");

            JSONArray mainRecipeTxt = json.getJSONArray("recipe");

            for (int i = 0; i < json.length(); i++) {
                JSONObject mainRecipeObject = mainRecipeTxt.getJSONObject(0);
                String item;

                if(i==0) {
                    JSONArray ingrediensArr = mainRecipeObject.getJSONArray("ingrediens");
                    for (int j = 0; j < ingrediensArr.length(); j++) {
                        item = ingrediensArr.getString(j);
                        ingrediens.add(item);
                    }
                } else if(i==1) {
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

        RecipeObj recipe = new RecipeObj(name,ingrediens,directions);
        return recipe;
    }
}